package com.bubbaboogs.modad;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.player.Player;

public class ModAttributes {
    public static Holder<Attribute> REGENERATION;

    public static void initialize() {
        REGENERATION = Registry.registerForHolder(
                BuiltInRegistries.ATTRIBUTE,
                ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "regen"),
                new RangedAttribute("attribute.modad.regen", 0.0, 0.0, 30.0).setSyncable(true)
        );

        FabricDefaultAttributeRegistry.register(
                EntityType.PLAYER,
                Player.createAttributes().add(REGENERATION)
        );
    }
}
