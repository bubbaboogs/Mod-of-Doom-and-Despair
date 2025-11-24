package com.bubbaboogs.modad;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class ModOfDoomAndDespairBlockLootTableProvider extends FabricBlockLootTableProvider {
    protected ModOfDoomAndDespairBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        for(Block block : ModBlockFamilies.GABBRO.getVariants().values()){
            dropSelf(block);
        }
        for(Block block : ModBlockFamilies.GABBRO_BRICK.getVariants().values()){
            dropSelf(block);
        }
        for(Block block : ModBlockFamilies.GABBRO_TILE.getVariants().values()){
            dropSelf(block);
        }
        for(Block block : ModBlockFamilies.JADEITE.getVariants().values()){
            dropSelf(block);
        }
        for(Block block : ModBlockFamilies.JADEITE_BRICK.getVariants().values()){
            dropSelf(block);
        }
        for(Block block : ModBlockFamilies.JADEITE_TILE.getVariants().values()){
            dropSelf(block);
        }
        for(Block block : ModBlockFamilies.GYPSUM.getVariants().values()){
            dropSelf(block);
        }
        for(Block block : ModBlockFamilies.GYPSUM_BRICK.getVariants().values()){
            dropSelf(block);
        }
        for(Block block : ModBlockFamilies.GYPSUM_TILE.getVariants().values()){
            dropSelf(block);
        }
        for(Block block : ModBlockFamilies.SHEET_METAL.getVariants().values()){
            dropSelf(block);
        }
        dropSelf(ModBlocks.GABBRO);
        dropSelf(ModBlocks.GABBRO_BRICKS);
        dropSelf(ModBlocks.GABBRO_TILES);
        dropSelf(ModBlocks.JADEITE);
        dropSelf(ModBlocks.JADEITE_BRICKS);
        dropSelf(ModBlocks.JADEITE_TILES);
        dropSelf(ModBlocks.GYPSUM);
        dropSelf(ModBlocks.GYPSUM_BRICKS);
        dropSelf(ModBlocks.GYPSUM_TILES);
        dropSelf(ModBlocks.LIFE_CRYSTAL);
        dropSelf(ModBlocks.SHEET_METAL_BLOCK);
        dropSelf(ModBlocks.SHEET_METAL_GRATE);
        dropSelf(ModBlocks.PLATFORM);
        dropSelf(ModBlocks.ENDERITE_ORE);
        dropSelf(ModBlocks.HARVESTER);
    }
}