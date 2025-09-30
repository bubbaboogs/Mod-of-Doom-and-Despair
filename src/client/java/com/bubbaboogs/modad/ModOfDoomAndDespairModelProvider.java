package com.bubbaboogs.modad;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class ModOfDoomAndDespairModelProvider extends FabricModelProvider {
    public ModOfDoomAndDespairModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.JADEITE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.JADEITE_BRICKS).slab(ModBlocks.JADEITE_BRICK_SLAB).wall(ModBlocks.JADEITE_BRICK_WALL).stairs(ModBlocks.JADEITE_BRICK_STAIRS);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.JADEITE_TILES).slab(ModBlocks.JADEITE_TILE_SLAB).wall(ModBlocks.JADEITE_TILE_WALL).stairs(ModBlocks.JADEITE_TILE_STAIRS);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GABBRO);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POLISHED_GABBRO);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.GABBRO_BRICKS).slab(ModBlocks.GABBRO_BRICK_SLAB).wall(ModBlocks.GABBRO_BRICK_WALL).stairs(ModBlocks.GABBRO_BRICK_STAIRS);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.GABBRO_TILES).slab(ModBlocks.GABBRO_TILE_SLAB).wall(ModBlocks.GABBRO_TILE_WALL).stairs(ModBlocks.GABBRO_TILE_STAIRS);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GYPSUM);
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
    }
}

