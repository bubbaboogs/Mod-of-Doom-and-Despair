package com.bubbaboogs.modad;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModAttributes {
    public static RegistryEntry<EntityAttribute> REGENERATION;

    public static void initialize() {
        REGENERATION = Registry.registerReference(
                Registries.ATTRIBUTE,
                Identifier.of(ModOfDoomAndDespair.MOD_ID, "regen"),
                new ClampedEntityAttribute("attribute.modad.regen", 0.0, 0.0, 30.0).setTracked(true)
        );

        FabricDefaultAttributeRegistry.register(
                EntityType.PLAYER,
                PlayerEntity.createPlayerAttributes().add(REGENERATION)
        );
    }
}
