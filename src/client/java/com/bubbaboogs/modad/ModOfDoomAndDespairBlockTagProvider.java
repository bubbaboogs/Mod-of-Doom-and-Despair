package com.bubbaboogs.modad;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModOfDoomAndDespairBlockTagProvider extends FabricTagProvider.FabricValueLookupTagProvider<Block> {

    public ModOfDoomAndDespairBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BLOCK, registriesFuture, (value) -> {
            return value.getRegistryEntry().registryKey();
        });
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(BlockTags.WALLS)
                .add(ModBlocks.JADEITE_BRICK_WALL)
                .add(ModBlocks.JADEITE_TILE_WALL)

                .add(ModBlocks.GABBRO_BRICK_WALL)
                .add(ModBlocks.GABBRO_TILE_WALL)

                .add(ModBlocks.GYPSUM_BRICK_WALL)
                .add(ModBlocks.GYPSUM_TILE_WALL)

                .add(ModBlocks.SHEET_METAL_WALL)
                .setReplace(false);
        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.JADEITE)
                .add(ModBlocks.JADEITE_TILES)
                .add(ModBlocks.JADEITE_BRICKS)
                .add(ModBlockFamilies.JADEITE_TILE.getVariants().values())
                .add(ModBlockFamilies.JADEITE_BRICK.getVariants().values())

                .add(ModBlocks.GABBRO)
                .add(ModBlocks.GABBRO_TILES)
                .add(ModBlocks.GABBRO_BRICKS)
                .add(ModBlockFamilies.GABBRO.getVariants().values())
                .add(ModBlockFamilies.GABBRO_TILE.getVariants().values())
                .add(ModBlockFamilies.GABBRO_BRICK.getVariants().values())

                .add(ModBlocks.GYPSUM)
                .add(ModBlocks.GYPSUM_TILES)
                .add(ModBlocks.GYPSUM_BRICKS)
                .add(ModBlockFamilies.GYPSUM_TILE.getVariants().values())
                .add(ModBlockFamilies.GYPSUM_BRICK.getVariants().values())

                .add(ModBlocks.SHEET_METAL_BLOCK)
                .add(ModBlocks.SHEET_METAL_GRATE)
                .add(ModBlockFamilies.SHEET_METAL.getVariants().values())
                .setReplace(false);
    }
}
