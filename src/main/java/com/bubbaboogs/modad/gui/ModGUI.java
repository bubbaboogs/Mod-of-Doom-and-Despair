package com.bubbaboogs.modad.gui;

import com.bubbaboogs.modad.ModOfDoomAndDespair;
import com.bubbaboogs.modad.entities.block.HarvesterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.MenuType;

public class ModGUI {

    public static final MenuType<HarvesterMenu> HARVESTER_MENU =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "harvester"),
                    new MenuType<>(HarvesterMenu::new, FeatureFlagSet.of()));

    public static void initialize() {
        // Nothing else needed — registration done by registerExtended
    }
}
