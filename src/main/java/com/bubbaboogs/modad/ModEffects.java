package com.bubbaboogs.modad;

import com.bubbaboogs.modad.effects.MushyEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

public class ModEffects {
    public static final Holder<MobEffect> MUSHY;

    static {
        MUSHY = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "mushy"), new MushyEffect());
    }

    public static void initialize(){}
}
