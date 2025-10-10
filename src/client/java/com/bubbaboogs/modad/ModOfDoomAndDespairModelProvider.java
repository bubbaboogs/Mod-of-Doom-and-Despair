package com.bubbaboogs.modad;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

public class ModOfDoomAndDespairModelProvider extends FabricModelProvider {
    public FabricDataOutput dataOutput;

    public ModOfDoomAndDespairModelProvider(FabricDataOutput fabricDataOutput) {
        super(fabricDataOutput);
        dataOutput = fabricDataOutput;
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.JADEITE).slab(ModBlocks.JADEITE_SLAB).stairs(ModBlocks.JADEITE_STAIRS).wall(ModBlocks.JADEITE_WALL);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.JADEITE_BRICKS).slab(ModBlocks.JADEITE_BRICK_SLAB).wall(ModBlocks.JADEITE_BRICK_WALL).stairs(ModBlocks.JADEITE_BRICK_STAIRS);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.JADEITE_TILES).slab(ModBlocks.JADEITE_TILE_SLAB).wall(ModBlocks.JADEITE_TILE_WALL).stairs(ModBlocks.JADEITE_TILE_STAIRS);

        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.GABBRO).slab(ModBlocks.GABBRO_SLAB).stairs(ModBlocks.GABBRO_STAIRS).wall(ModBlocks.GABBRO_WALL);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_GABBRO);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.GABBRO_BRICKS).slab(ModBlocks.GABBRO_BRICK_SLAB).wall(ModBlocks.GABBRO_BRICK_WALL).stairs(ModBlocks.GABBRO_BRICK_STAIRS);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.GABBRO_TILES).slab(ModBlocks.GABBRO_TILE_SLAB).wall(ModBlocks.GABBRO_TILE_WALL).stairs(ModBlocks.GABBRO_TILE_STAIRS);

        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.GYPSUM).slab(ModBlocks.GYPSUM_SLAB).stairs(ModBlocks.GYPSUM_STAIRS).wall(ModBlocks.GYPSUM_WALL);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.GYPSUM_BRICKS).slab(ModBlocks.GYPSUM_BRICK_SLAB).wall(ModBlocks.GYPSUM_BRICK_WALL).stairs(ModBlocks.GYPSUM_BRICK_STAIRS);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.GYPSUM_TILES).slab(ModBlocks.GYPSUM_TILE_SLAB).wall(ModBlocks.GYPSUM_TILE_WALL).stairs(ModBlocks.GYPSUM_TILE_STAIRS);

        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SHEET_METAL_BLOCK).slab(ModBlocks.SHEET_METAL_SLAB).wall(ModBlocks.SHEET_METAL_WALL).stairs(ModBlocks.SHEET_METAL_STAIRS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SHEET_METAL_GRATE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.CRYING_OBSIDIAN_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TAINTED_BLADE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CINQUEDEA, Models.HANDHELD);
        itemModelGenerator.register(ModItems.EVIL_SMASHER, Models.HANDHELD_MACE);
        itemModelGenerator.register(ModItems.MYCELIAL_CLAWS, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GRAPPLING_HOOK, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MENDING_STAFF, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BOTANSICKLE, Models.HANDHELD);
        injectDisplayScaling(ModItems.BOTANSICKLE, 2.0f);
    }

    private void injectDisplayScaling(Item item, float scale) {
        try {
            Identifier itemId = Registries.ITEM.getId(item);
            Path modelJsonPath = this.dataOutput.getPath()
                    .resolve("assets")
                    .resolve(ModOfDoomAndDespair.MOD_ID)
                    .resolve("models")
                    .resolve("item")
                    .resolve(itemId.getPath() + ".json");

            if (!Files.exists(modelJsonPath)) return;

            JsonObject root = JsonParser.parseReader(Files.newBufferedReader(modelJsonPath)).getAsJsonObject();
            JsonObject display = new JsonObject();

            java.util.function.BiConsumer<String, float[]> addTransform = (name, rotation) -> {
                JsonObject obj = new JsonObject();
                JsonArray rot = new JsonArray();
                for (float r : rotation) rot.add(r);
                obj.add("rotation", rot);

                JsonArray trans = new JsonArray();
                if (name.contains("thirdperson")) {
                    trans.add(0.0f);
                    trans.add(2.0f);  // slightly higher
                    trans.add(0.5f);  // closer to player
                } else {
                    trans.add(1.0f);
                    trans.add(4.5f);  // raise a bit
                    trans.add(0.5f);  // pull closer
                }
                obj.add("translation", trans);

                JsonArray sc = new JsonArray();
                float s = name.contains("thirdperson") ? scale * 0.8f : scale;
                sc.add(s);
                sc.add(s);
                sc.add(s);
                obj.add("scale", sc);

                display.add(name, obj);
            };

            addTransform.accept("firstperson_righthand", new float[]{0, -90, 55});
            addTransform.accept("firstperson_lefthand", new float[]{0, 90, -55});
            addTransform.accept("thirdperson_righthand", new float[]{0, -90, 55});
            addTransform.accept("thirdperson_lefthand", new float[]{0, 90, -55});

            root.add("display", display);
            DataProvider.writeToPath(DataWriter.UNCACHED, root, modelJsonPath);

        } catch (IOException e) {
            throw new RuntimeException("Failed to inject display transform for " + item, e);
        }
    }
}

