package com.bubbaboogs.modad;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.stream.Stream;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

public class ModBlockFamilies {
    private static final Map<Block, BlockFamily> BASE_BLOCKS_TO_FAMILIES = Maps.newHashMap();
    public static final BlockFamily JADEITE = register(ModBlocks.JADEITE).slab(ModBlocks.JADEITE_SLAB).stairs(ModBlocks.JADEITE_STAIRS).wall(ModBlocks.JADEITE_WALL).getFamily();
    public static final BlockFamily JADEITE_BRICK = register(ModBlocks.JADEITE_BRICKS).slab(ModBlocks.JADEITE_BRICK_SLAB).stairs(ModBlocks.JADEITE_BRICK_STAIRS).wall(ModBlocks.JADEITE_BRICK_WALL).getFamily();
    public static final BlockFamily JADEITE_TILE = register(ModBlocks.JADEITE_TILES).slab(ModBlocks.JADEITE_TILE_SLAB).stairs(ModBlocks.JADEITE_TILE_STAIRS).wall(ModBlocks.JADEITE_TILE_WALL).getFamily();

    public static final BlockFamily GABBRO = register(ModBlocks.GABBRO).polished(ModBlocks.POLISHED_GABBRO).slab(ModBlocks.GABBRO_SLAB).stairs(ModBlocks.GABBRO_STAIRS).wall(ModBlocks.GABBRO_WALL).getFamily();
    public static final BlockFamily GABBRO_BRICK = register(ModBlocks.GABBRO_BRICKS).slab(ModBlocks.GABBRO_BRICK_SLAB).stairs(ModBlocks.GABBRO_BRICK_STAIRS).wall(ModBlocks.GABBRO_BRICK_WALL).getFamily();
    public static final BlockFamily GABBRO_TILE = register(ModBlocks.GABBRO_TILES).slab(ModBlocks.GABBRO_TILE_SLAB).stairs(ModBlocks.GABBRO_TILE_STAIRS).wall(ModBlocks.GABBRO_TILE_WALL).getFamily();

    public static final BlockFamily GYPSUM = register(ModBlocks.GYPSUM).slab(ModBlocks.GYPSUM_SLAB).stairs(ModBlocks.GYPSUM_STAIRS).wall(ModBlocks.GYPSUM_WALL).getFamily();
    public static final BlockFamily GYPSUM_BRICK = register(ModBlocks.GYPSUM_BRICKS).slab(ModBlocks.GYPSUM_BRICK_SLAB).stairs(ModBlocks.GYPSUM_BRICK_STAIRS).wall(ModBlocks.GYPSUM_BRICK_WALL).getFamily();
    public static final BlockFamily GYPSUM_TILE = register(ModBlocks.GYPSUM_TILES).slab(ModBlocks.GYPSUM_TILE_SLAB).stairs(ModBlocks.GYPSUM_TILE_STAIRS).wall(ModBlocks.GYPSUM_TILE_WALL).getFamily();

    public static final BlockFamily SHEET_METAL = register(ModBlocks.SHEET_METAL_BLOCK).slab(ModBlocks.SHEET_METAL_SLAB).wall(ModBlocks.SHEET_METAL_WALL).stairs(ModBlocks.SHEET_METAL_STAIRS).getFamily();

    public static BlockFamily.Builder register(Block baseBlock) {
        BlockFamily.Builder builder = new BlockFamily.Builder(baseBlock);
        BlockFamily blockFamily = (BlockFamily)BASE_BLOCKS_TO_FAMILIES.put(baseBlock, builder.getFamily());
        if (blockFamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + String.valueOf(BuiltInRegistries.BLOCK.getKey(baseBlock)));
        } else {
            return builder;
        }
    }

    public static Stream<BlockFamily> getFamilies() {
        return BASE_BLOCKS_TO_FAMILIES.values().stream();
    }

    public static void initialize(){}
}
