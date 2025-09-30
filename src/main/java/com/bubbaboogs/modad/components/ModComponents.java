package com.bubbaboogs.modad.components;

import com.bubbaboogs.modad.ModOfDoomAndDespair;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModComponents {
    public static final ComponentType<Integer> EVIL_SMASHER_BOOST = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(ModOfDoomAndDespair.MOD_ID, "evil_smasher_boost"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );

    /*public static final ComponentType<String> BOBBER = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(ModOfDoomAndDespair.MOD_ID, "bobber"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );*/

    public static void initialize(){
    }
}
