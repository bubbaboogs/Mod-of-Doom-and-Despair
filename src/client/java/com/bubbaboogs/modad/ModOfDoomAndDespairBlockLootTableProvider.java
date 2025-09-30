package com.bubbaboogs.modad;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModOfDoomAndDespairBlockLootTableProvider extends FabricBlockLootTableProvider {
    protected ModOfDoomAndDespairBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        for(Block block : ModBlockFamilies.GABBRO.getVariants().values()){
            addDrop(block);
        }
        for(Block block : ModBlockFamilies.GABBRO_BRICK.getVariants().values()){
            addDrop(block);
        }
        for(Block block : ModBlockFamilies.GABBRO_TILE.getVariants().values()){
            addDrop(block);
        }
        for(Block block : ModBlockFamilies.JADEITE.getVariants().values()){
            addDrop(block);
        }
        for(Block block : ModBlockFamilies.JADEITE_BRICK.getVariants().values()){
            addDrop(block);
        }
        for(Block block : ModBlockFamilies.JADEITE_TILE.getVariants().values()){
            addDrop(block);
        }
        for(Block block : ModBlockFamilies.GYPSUM.getVariants().values()){
            addDrop(block);
        }
        for(Block block : ModBlockFamilies.GYPSUM_BRICK.getVariants().values()){
            addDrop(block);
        }
        for(Block block : ModBlockFamilies.GYPSUM_TILE.getVariants().values()){
            addDrop(block);
        }
        for(Block block : ModBlockFamilies.SHEET_METAL.getVariants().values()){
            addDrop(block);
        }
        addDrop(ModBlocks.GABBRO);
        addDrop(ModBlocks.GABBRO_BRICKS);
        addDrop(ModBlocks.GABBRO_TILES);
        addDrop(ModBlocks.JADEITE);
        addDrop(ModBlocks.JADEITE_BRICKS);
        addDrop(ModBlocks.JADEITE_TILES);
        addDrop(ModBlocks.GYPSUM);
        addDrop(ModBlocks.GYPSUM_BRICKS);
        addDrop(ModBlocks.GYPSUM_TILES);
        addDrop(ModBlocks.LIFE_CRYSTAL);
        addDrop(ModBlocks.SHEET_METAL_BLOCK);
        addDrop(ModBlocks.SHEET_METAL_GRATE);
        addDrop(ModBlocks.PLATFORM);
    }
}