package com.bubbaboogs.modad;

import com.bubbaboogs.modad.components.ModComponents;
import com.bubbaboogs.modad.items.GrapplingHookItem;
import com.bubbaboogs.modad.items.LifeCrystalItem;
import com.bubbaboogs.modad.items.MendingStaffItem;
import com.bubbaboogs.modad.weapons.EvilSmasherItem;
import com.bubbaboogs.modad.weapons.MycelialClawsItem;
import com.bubbaboogs.modad.weapons.TaintedBladeItem;
import com.bubbaboogs.modad.weapons.rogue.CinquedeaItem;
import com.bubbaboogs.modad.weapons.rogue.GildedDaggerItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ModItems {
    public static final List<Item> ModItemsList = new ArrayList<>();
    public static final RegistryKey<ItemGroup> MODAD_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(ModOfDoomAndDespair.MOD_ID, "item_group"));
    public static final ItemGroup MODAD_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.JADEITE.asItem()))
            .displayName(Text.translatable("itemGroup.modad"))
            .build();

    public static final ToolMaterial OBSIDIAN_TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 1000, 0f, 10, 22, ItemTags.DIAMOND_TOOL_MATERIALS);
    public static final Item CRYING_OBSIDIAN_HAMMER = register("crying_obsidian_hammer", Item::new, new Item.Settings().sword(OBSIDIAN_TOOL_MATERIAL, 0, -3));

    public static final Item GRAPPLING_HOOK = register("grappling_hook", GrapplingHookItem::new, new Item.Settings());


    public static final Item EVIL_SMASHER = register("evil_smasher", EvilSmasherItem::new, new Item.Settings().maxCount(1).attributeModifiers(EvilSmasherItem.createAttributeModifiers()).rarity(Rarity.EPIC).component(ModComponents.EVIL_SMASHER_BOOST, 0).maxDamage(500));
    public static final Item CINQUEDEA = register("cinquedea", CinquedeaItem::new, new Item.Settings().attributeModifiers(CinquedeaItem.createAttributeModifiers()).sword(new ToolMaterial(null, 340, -2.9f, 4.0f, 15, null), 1f, 2f));
    public static final Item GILDED_DAGGER = register("gilded_dagger", GildedDaggerItem::new, new Item.Settings().attributeModifiers(GildedDaggerItem.createAttributeModifiers()).sword(new ToolMaterial(null, 350, 2.0f, 9.0f, 15, null), 1f, 2f));
    public static final Item TAINTED_BLADE = register("tainted_blade", TaintedBladeItem::new, new Item.Settings().sword(new ToolMaterial(null, 350, 2.0f, 9.0f, 15, null), 0, -2.4f).maxDamage(350).rarity(Rarity.UNCOMMON));
    public static final Item MYCELIAL_CLAWS = register("mycelial_claws", MycelialClawsItem::new, new Item.Settings().attributeModifiers(MycelialClawsItem.createAttributeModifiers()).maxDamage(350));
    public static final Item MENDING_STAFF = register("staff_of_mending", MendingStaffItem::new, new Item.Settings().maxDamage(20));

    public static Item LIFE_CRYSTAL_ITEM;


    public static void initialize() {
        LIFE_CRYSTAL_ITEM = register("life_crystal", LifeCrystalItem::new, new Item.Settings());

        Registry.register(Registries.ITEM_GROUP, MODAD_ITEM_GROUP_KEY, MODAD_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(MODAD_ITEM_GROUP_KEY)
                .register(entries -> {
                    for (Item item : ModItemsList) {
                        entries.add(item);
                    }
                });

    }


    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(ModOfDoomAndDespair.MOD_ID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);
        ModItemsList.add(item);
        return item;
    }
}