package com.bubbaboogs.modad;
import com.bubbaboogs.modad.blocks.PlatformBlock;
import com.bubbaboogs.modad.items.LifeCrystalItem;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {

    //JADEITE
    public static final Block JADEITE = registerStoneBlock("jadeite");
    public static final Block JADEITE_SLAB = register("jadeite_slab", SlabBlock::new, AbstractBlock.Settings.copyShallow(JADEITE), true);
    public static final Block JADEITE_STAIRS = registerStairsBlock("jadeite_stairs", JADEITE, true);
    public static final Block JADEITE_WALL = register("jadeite_wall", WallBlock::new, AbstractBlock.Settings.copyShallow(JADEITE).solid(), true);
    public static final Block JADEITE_BRICKS = register("jadeite_bricks", Block::new, AbstractBlock.Settings.copy(Blocks.STONE_BRICKS),
            true);
    public static final Block JADEITE_TILES = register("jadeite_tiles", Block::new, AbstractBlock.Settings.copy(Blocks.DEEPSLATE_TILES),
            true);
    public static final Block JADEITE_BRICK_SLAB = register("jadeite_brick_slab", SlabBlock::new, AbstractBlock.Settings.copyShallow(JADEITE_BRICKS), true);
    public static final Block JADEITE_BRICK_STAIRS = registerStairsBlock("jadeite_brick_stairs", JADEITE_BRICKS, true);
    public static final Block JADEITE_BRICK_WALL = register("jadeite_brick_wall", WallBlock::new, AbstractBlock.Settings.copyShallow(JADEITE_BRICKS).solid(), true);
    public static final Block JADEITE_TILE_SLAB = register("jadeite_tile_slab", SlabBlock::new, AbstractBlock.Settings.copyShallow(JADEITE_TILES), true);
    public static final Block JADEITE_TILE_STAIRS = registerStairsBlock("jadeite_tile_stairs", JADEITE_TILES, true);
    public static final Block JADEITE_TILE_WALL = register("jadeite_tile_wall", WallBlock::new, AbstractBlock.Settings.copyShallow(JADEITE_TILES).solid(), true);

    //GABBRO
    public static final Block GABBRO = registerStoneBlock("gabbro");
    public static final Block GABBRO_SLAB = register("gabbro_slab", SlabBlock::new, AbstractBlock.Settings.copyShallow(GABBRO), true);
    public static final Block GABBRO_STAIRS = registerStairsBlock("gabbro_stairs", GABBRO, true);
    public static final Block GABBRO_WALL = register("gabbro_wall", WallBlock::new, AbstractBlock.Settings.copyShallow(GABBRO).solid(), true);
    public static final Block GABBRO_BRICKS = register("gabbro_bricks", Block::new, AbstractBlock.Settings.copy(Blocks.STONE_BRICKS),
            true);
    public static final Block GABBRO_TILES = register("gabbro_tiles", Block::new, AbstractBlock.Settings.copy(Blocks.DEEPSLATE_TILES),
            true);
    public static final Block POLISHED_GABBRO = register("polished_gabbro", Block::new, AbstractBlock.Settings.copy(Blocks.POLISHED_DEEPSLATE),
            true);
    public static final Block GABBRO_BRICK_SLAB = register("gabbro_brick_slab", SlabBlock::new, AbstractBlock.Settings.copyShallow(GABBRO_BRICKS), true);
    public static final Block GABBRO_BRICK_STAIRS = registerStairsBlock("gabbro_brick_stairs", GABBRO_BRICKS, true);
    public static final Block GABBRO_BRICK_WALL = register("gabbro_brick_wall", WallBlock::new, AbstractBlock.Settings.copyShallow(GABBRO_BRICKS).solid(), true);
    public static final Block GABBRO_TILE_SLAB = register("gabbro_tile_slab", SlabBlock::new, AbstractBlock.Settings.copyShallow(GABBRO_TILES), true);
    public static final Block GABBRO_TILE_STAIRS = registerStairsBlock("gabbro_tile_stairs", GABBRO_TILES, true);
    public static final Block GABBRO_TILE_WALL = register("gabbro_tile_wall", WallBlock::new, AbstractBlock.Settings.copyShallow(GABBRO_TILES).solid(), true);


    //GYPSUM
    public static final Block GYPSUM = registerStoneBlock("gypsum");
    public static final Block GYPSUM_SLAB = register("gypsum_slab", SlabBlock::new, AbstractBlock.Settings.copyShallow(GYPSUM), true);
    public static final Block GYPSUM_STAIRS = registerStairsBlock("gypsum_stairs", GYPSUM, true);
    public static final Block GYPSUM_WALL = register("gypsum_wall", WallBlock::new, AbstractBlock.Settings.copyShallow(GYPSUM).solid(), true);
    public static final Block GYPSUM_BRICKS = register("gypsum_bricks", Block::new, AbstractBlock.Settings.copy(Blocks.STONE_BRICKS),
            true);
    public static final Block GYPSUM_TILES = register("gypsum_tiles", Block::new, AbstractBlock.Settings.copy(Blocks.DEEPSLATE_TILES),
            true);
    public static final Block GYPSUM_BRICK_SLAB = register("gypsum_brick_slab", SlabBlock::new, AbstractBlock.Settings.copyShallow(GYPSUM_BRICKS), true);
    public static final Block GYPSUM_BRICK_STAIRS = registerStairsBlock("gypsum_brick_stairs", GYPSUM_BRICKS, true);
    public static final Block GYPSUM_BRICK_WALL = register("gypsum_brick_wall", WallBlock::new, AbstractBlock.Settings.copyShallow(GYPSUM_BRICKS).solid(), true);
    public static final Block GYPSUM_TILE_SLAB = register("gypsum_tile_slab", SlabBlock::new, AbstractBlock.Settings.copyShallow(GYPSUM_TILES), true);
    public static final Block GYPSUM_TILE_STAIRS = registerStairsBlock("gypsum_tile_stairs", GYPSUM_TILES, true);
    public static final Block GYPSUM_TILE_WALL = register("gypsum_tile_wall", WallBlock::new, AbstractBlock.Settings.copyShallow(GYPSUM_TILES).solid(), true);


    //SHEET METAL
    public static final Block SHEET_METAL_BLOCK = register("sheet_metal_block", Block::new, AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.COPPER).mapColor(MapColor.GRAY), true);
    public static final Block SHEET_METAL_SLAB = register("sheet_metal_slab", SlabBlock::new, AbstractBlock.Settings.copyShallow(SHEET_METAL_BLOCK), true);
    public static final Block SHEET_METAL_STAIRS = registerStairsBlock("sheet_metal_stairs", SHEET_METAL_BLOCK, true);
    public static final Block SHEET_METAL_WALL = register("sheet_metal_wall", WallBlock::new, AbstractBlock.Settings.copyShallow(SHEET_METAL_BLOCK).solid(), true);
    public static final Block SHEET_METAL_GRATE = register("sheet_metal_grate", GrateBlock::new, AbstractBlock.Settings.copy(Blocks.COPPER_GRATE).mapColor(MapColor.GRAY), true);


    public static final Block LIFE_CRYSTAL =
            register("life_crystal", Block::new,
                    AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS).nonOpaque(),
                    false);

    public static final Block PLATFORM = register("platform", PlatformBlock::new, AbstractBlock.Settings.copy(Blocks.SCAFFOLDING), true);

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = Registry.register(Registries.BLOCK, blockKey,
                blockFactory.apply(settings.registryKey(blockKey)));

        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = keyOfItem(name);
            BlockItem blockItem = Registry.register(Registries.ITEM, itemKey,
                    new BlockItem(block, new Item.Settings().registryKey(itemKey)));
            ModItems.ModItemsList.add(blockItem);
        }

        return block;
    }

    private static Block registerStoneBlock(String id){
        return register(id, Block::new, AbstractBlock.Settings.copy(Blocks.STONE), true);
    }

    private static Block registerStairsBlock(String id, Block base, boolean shouldRegisterItem) {
        return register(id, (settings) -> new StairsBlock(base.getDefaultState(), settings), AbstractBlock.Settings.copy(base), shouldRegisterItem);
    }


    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(ModOfDoomAndDespair.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(ModOfDoomAndDespair.MOD_ID, name));
    }


    public static void initialize() {
    }


    private static RegistryKey<Block> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.ofVanilla(id));
    }

}