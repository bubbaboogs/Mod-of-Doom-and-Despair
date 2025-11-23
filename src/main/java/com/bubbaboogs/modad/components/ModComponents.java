package com.bubbaboogs.modad.components;

import com.bubbaboogs.modad.ModOfDoomAndDespair;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class ModComponents{
    public static final DataComponentType<Integer> EVIL_SMASHER_BOOST = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "evil_smasher_boost"),
            DataComponentType.<Integer>builder().persistent(Codec.INT).build()
    );

    /*public static final ComponentType<String> BOBBER = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(ModOfDoomAndDespair.MOD_ID, "bobber"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );*/

    public static void initialize(){
    }
}
