package com.bubbaboogs.modad;
import com.bubbaboogs.modad.blocks.HarvesterBlock;
import com.bubbaboogs.modad.blocks.PlatformBlock;
import com.bubbaboogs.modad.items.LifeCrystalItem;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {

    //JADEITE
    public static final Block JADEITE = registerStoneBlock("jadeite");
    public static final Block JADEITE_SLAB = register("jadeite_slab", SlabBlock::new, BlockBehaviour.Properties.ofLegacyCopy(JADEITE), true);
    public static final Block JADEITE_STAIRS = registerStairsBlock("jadeite_stairs", JADEITE, true);
    public static final Block JADEITE_WALL = register("jadeite_wall", WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(JADEITE).forceSolidOn(), true);
    public static final Block JADEITE_BRICKS = register("jadeite_bricks", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS),
            true);
    public static final Block JADEITE_TILES = register("jadeite_tiles", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_TILES),
            true);
    public static final Block JADEITE_BRICK_SLAB = register("jadeite_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofLegacyCopy(JADEITE_BRICKS), true);
    public static final Block JADEITE_BRICK_STAIRS = registerStairsBlock("jadeite_brick_stairs", JADEITE_BRICKS, true);
    public static final Block JADEITE_BRICK_WALL = register("jadeite_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(JADEITE_BRICKS).forceSolidOn(), true);
    public static final Block JADEITE_TILE_SLAB = register("jadeite_tile_slab", SlabBlock::new, BlockBehaviour.Properties.ofLegacyCopy(JADEITE_TILES), true);
    public static final Block JADEITE_TILE_STAIRS = registerStairsBlock("jadeite_tile_stairs", JADEITE_TILES, true);
    public static final Block JADEITE_TILE_WALL = register("jadeite_tile_wall", WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(JADEITE_TILES).forceSolidOn(), true);

    //GABBRO
    public static final Block GABBRO = registerStoneBlock("gabbro");
    public static final Block GABBRO_SLAB = register("gabbro_slab", SlabBlock::new, BlockBehaviour.Properties.ofLegacyCopy(GABBRO), true);
    public static final Block GABBRO_STAIRS = registerStairsBlock("gabbro_stairs", GABBRO, true);
    public static final Block GABBRO_WALL = register("gabbro_wall", WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(GABBRO).forceSolidOn(), true);
    public static final Block GABBRO_BRICKS = register("gabbro_bricks", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS),
            true);
    public static final Block GABBRO_TILES = register("gabbro_tiles", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_TILES),
            true);
    public static final Block POLISHED_GABBRO = register("polished_gabbro", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DEEPSLATE),
            true);
    public static final Block GABBRO_BRICK_SLAB = register("gabbro_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofLegacyCopy(GABBRO_BRICKS), true);
    public static final Block GABBRO_BRICK_STAIRS = registerStairsBlock("gabbro_brick_stairs", GABBRO_BRICKS, true);
    public static final Block GABBRO_BRICK_WALL = register("gabbro_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(GABBRO_BRICKS).forceSolidOn(), true);
    public static final Block GABBRO_TILE_SLAB = register("gabbro_tile_slab", SlabBlock::new, BlockBehaviour.Properties.ofLegacyCopy(GABBRO_TILES), true);
    public static final Block GABBRO_TILE_STAIRS = registerStairsBlock("gabbro_tile_stairs", GABBRO_TILES, true);
    public static final Block GABBRO_TILE_WALL = register("gabbro_tile_wall", WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(GABBRO_TILES).forceSolidOn(), true);


    //GYPSUM
    public static final Block GYPSUM = registerStoneBlock("gypsum");
    public static final Block GYPSUM_SLAB = register("gypsum_slab", SlabBlock::new, BlockBehaviour.Properties.ofLegacyCopy(GYPSUM), true);
    public static final Block GYPSUM_STAIRS = registerStairsBlock("gypsum_stairs", GYPSUM, true);
    public static final Block GYPSUM_WALL = register("gypsum_wall", WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(GYPSUM).forceSolidOn(), true);
    public static final Block GYPSUM_BRICKS = register("gypsum_bricks", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS),
            true);
    public static final Block GYPSUM_TILES = register("gypsum_tiles", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_TILES),
            true);
    public static final Block GYPSUM_BRICK_SLAB = register("gypsum_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofLegacyCopy(GYPSUM_BRICKS), true);
    public static final Block GYPSUM_BRICK_STAIRS = registerStairsBlock("gypsum_brick_stairs", GYPSUM_BRICKS, true);
    public static final Block GYPSUM_BRICK_WALL = register("gypsum_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(GYPSUM_BRICKS).forceSolidOn(), true);
    public static final Block GYPSUM_TILE_SLAB = register("gypsum_tile_slab", SlabBlock::new, BlockBehaviour.Properties.ofLegacyCopy(GYPSUM_TILES), true);
    public static final Block GYPSUM_TILE_STAIRS = registerStairsBlock("gypsum_tile_stairs", GYPSUM_TILES, true);
    public static final Block GYPSUM_TILE_WALL = register("gypsum_tile_wall", WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(GYPSUM_TILES).forceSolidOn(), true);


    //SHEET METAL
    public static final Block SHEET_METAL_BLOCK = register("sheet_metal_block", Block::new, BlockBehaviour.Properties.of()
                    .sound(SoundType.COPPER).mapColor(MapColor.COLOR_GRAY), true);
    public static final Block SHEET_METAL_SLAB = register("sheet_metal_slab", SlabBlock::new, BlockBehaviour.Properties.ofLegacyCopy(SHEET_METAL_BLOCK), true);
    public static final Block SHEET_METAL_STAIRS = registerStairsBlock("sheet_metal_stairs", SHEET_METAL_BLOCK, true);
    public static final Block SHEET_METAL_WALL = register("sheet_metal_wall", WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(SHEET_METAL_BLOCK).forceSolidOn(), true);
    public static final Block SHEET_METAL_GRATE = register("sheet_metal_grate", WaterloggedTransparentBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_GRATE).mapColor(MapColor.COLOR_GRAY), true);


    public static final Block LIFE_CRYSTAL =
            register("life_crystal", Block::new,
                    BlockBehaviour.Properties.of().sound(SoundType.GLASS).noOcclusion(),
                    false);

    //SPECIAL BLOCKS
    public static final Block PLATFORM = register("platform", PlatformBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SCAFFOLDING), true);
    public static final Block HARVESTER = register("harvester", HarvesterBlock::new, BlockBehaviour.Properties.of().strength(10, 900.0f), true);

    //ORES
    public static final Block ENDERITE_ORE = register("enderite_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).strength(30.0F, 1200.0F), true);

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
        ResourceKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.setId(blockKey));
        if (shouldRegisterItem) {
            ResourceKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
            ModItems.ModItemsList.add(blockItem);
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static Block registerStoneBlock(String id){
        return register(id, Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE), true);
    }

    private static Block registerStairsBlock(String id, Block base, boolean shouldRegisterItem) {
        return register(id, (settings) -> new StairBlock(base.defaultBlockState(), settings), BlockBehaviour.Properties.ofFullCopy(base), shouldRegisterItem);
    }


    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, name));
    }


    public static void initialize() {
    }


    private static ResourceKey<Block> keyOf(String id) {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.withDefaultNamespace(id));
    }

}