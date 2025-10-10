package com.bubbaboogs.modad;

import com.bubbaboogs.modad.effects.MushyEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> MUSHY;

    static {
        MUSHY = Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(ModOfDoomAndDespair.MOD_ID, "mushy"), new MushyEffect());
    }

    public static void initialize(){}
}
