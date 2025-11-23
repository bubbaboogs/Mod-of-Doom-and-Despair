package com.bubbaboogs.modad;

import com.bubbaboogs.modad.components.ModComponents;
import com.bubbaboogs.modad.items.GrapplingHookItem;
import com.bubbaboogs.modad.items.LifeCrystalItem;
import com.bubbaboogs.modad.items.MendingStaffItem;
import com.bubbaboogs.modad.weapons.BotansickleItem;
import com.bubbaboogs.modad.weapons.EvilSmasherItem;
import com.bubbaboogs.modad.weapons.MycelialClawsItem;
import com.bubbaboogs.modad.weapons.TaintedBladeItem;
import com.bubbaboogs.modad.weapons.rogue.CinquedeaItem;
import com.bubbaboogs.modad.weapons.rogue.GildedDaggerItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ToolMaterial;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ModItems {
    public static final List<Item> ModItemsList = new ArrayList<>();
    public static final ResourceKey<CreativeModeTab> MODAD_ITEM_GROUP_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "item_group"));
    public static final CreativeModeTab MODAD_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.JADEITE.asItem()))
            .title(Component.translatable("itemGroup.modad"))
            .build();

    public static final ToolMaterial OBSIDIAN_TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 1000, 0f, 10, 22, ItemTags.DIAMOND_TOOL_MATERIALS);
    public static final Item CRYING_OBSIDIAN_HAMMER = register("crying_obsidian_hammer", Item::new, new Item.Properties().sword(OBSIDIAN_TOOL_MATERIAL, 0, -3));

    public static final Item GRAPPLING_HOOK = register("grappling_hook", GrapplingHookItem::new, new Item.Properties());


    public static final Item EVIL_SMASHER = register("evil_smasher", EvilSmasherItem::new, new Item.Properties().stacksTo(1).attributes(EvilSmasherItem.createAttributeModifiers()).rarity(Rarity.EPIC).component(ModComponents.EVIL_SMASHER_BOOST, 0).durability(500));
    public static final Item CINQUEDEA = register("cinquedea", CinquedeaItem::new, new Item.Properties().attributes(CinquedeaItem.createAttributeModifiers()).sword(new ToolMaterial(null, 340, -2.9f, 4.0f, 15, null), 1f, 2f));
    public static final Item GILDED_DAGGER = register("gilded_dagger", GildedDaggerItem::new, new Item.Properties().attributes(GildedDaggerItem.createAttributeModifiers()).sword(new ToolMaterial(null, 350, 2.0f, 9.0f, 15, null), 1f, 2f));
    public static final Item TAINTED_BLADE = register("tainted_blade", TaintedBladeItem::new, new Item.Properties().sword(new ToolMaterial(null, 350, 2.0f, 9.0f, 15, null), 0, -2.4f).durability(350).rarity(Rarity.UNCOMMON));
    public static final Item MYCELIAL_CLAWS = register("mycelial_claws", MycelialClawsItem::new, new Item.Properties().attributes(MycelialClawsItem.createAttributeModifiers()).durability(350));
    public static final Item MENDING_STAFF = register("staff_of_mending", MendingStaffItem::new, new Item.Properties().durability(20));
    public static final Item BOTANSICKLE = register("botansickle", BotansickleItem::new, new Item.Properties().sword(BotansickleItem.material, 0, 0).attributes(BotansickleItem.createAttributeModifiers()));

    public static final Item ECHO_PASTE = register("echo_paste", Item::new, new Item.Properties());

    public static Item LIFE_CRYSTAL_ITEM;


    public static void initialize() {
        LIFE_CRYSTAL_ITEM = register("life_crystal", LifeCrystalItem::new, new Item.Properties());

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MODAD_ITEM_GROUP_KEY, MODAD_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(MODAD_ITEM_GROUP_KEY)
                .register(entries -> {
                    for (Item item : ModItemsList) {
                        entries.accept(item);
                    }
                });

    }


    public static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, name));
        Item item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        ModItemsList.add(item);
        return item;
    }
}