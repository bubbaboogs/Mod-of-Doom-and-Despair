package com.bubbaboogs.modad;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class ModOfDoomAndDespairBlockTagProvider extends FabricTagProvider.FabricValueLookupTagProvider<Block> {

    public ModOfDoomAndDespairBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.BLOCK, registriesFuture, (value) -> {
            return value.builtInRegistryHolder().key();
        });
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(BlockTags.WALLS)
                .add(ModBlocks.JADEITE_BRICK_WALL)
                .add(ModBlocks.JADEITE_TILE_WALL)
                .add(ModBlocks.JADEITE_WALL)

                .add(ModBlocks.GABBRO_BRICK_WALL)
                .add(ModBlocks.GABBRO_TILE_WALL)
                .add(ModBlocks.GABBRO_WALL)

                .add(ModBlocks.GYPSUM_BRICK_WALL)
                .add(ModBlocks.GYPSUM_TILE_WALL)
                .add(ModBlocks.GYPSUM_WALL)

                .add(ModBlocks.SHEET_METAL_WALL)
                .setReplace(false);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.JADEITE)
                .add(ModBlocks.JADEITE_TILES)
                .add(ModBlocks.JADEITE_BRICKS)
                .addAll(ModBlockFamilies.JADEITE.getVariants().values())
                .addAll(ModBlockFamilies.JADEITE_TILE.getVariants().values())
                .addAll(ModBlockFamilies.JADEITE_BRICK.getVariants().values())

                .add(ModBlocks.GABBRO)
                .add(ModBlocks.GABBRO_TILES)
                .add(ModBlocks.GABBRO_BRICKS)
                .addAll(ModBlockFamilies.GABBRO.getVariants().values())
                .addAll(ModBlockFamilies.GABBRO_TILE.getVariants().values())
                .addAll(ModBlockFamilies.GABBRO_BRICK.getVariants().values())

                .add(ModBlocks.GYPSUM)
                .add(ModBlocks.GYPSUM_TILES)
                .add(ModBlocks.GYPSUM_BRICKS)
                .addAll(ModBlockFamilies.GYPSUM.getVariants().values())
                .addAll(ModBlockFamilies.GYPSUM_TILE.getVariants().values())
                .addAll(ModBlockFamilies.GYPSUM_BRICK.getVariants().values())

                .add(ModBlocks.SHEET_METAL_BLOCK)
                .add(ModBlocks.SHEET_METAL_GRATE)
                .addAll(ModBlockFamilies.SHEET_METAL.getVariants().values())
                .setReplace(false);
    }
}
