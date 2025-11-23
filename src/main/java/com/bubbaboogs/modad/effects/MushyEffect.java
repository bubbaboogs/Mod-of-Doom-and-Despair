package com.bubbaboogs.modad.effects;

import com.bubbaboogs.modad.ModAttributes;
import com.bubbaboogs.modad.ModOfDoomAndDespair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;


public class MushyEffect extends MobEffect {

    public MushyEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x85FFED);

        this.addAttributeModifier(
                ModAttributes.REGENERATION, ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "regen"),
                1,
                AttributeModifier.Operation.ADD_VALUE
        );
        this.addAttributeModifier(
                Attributes.ARMOR, ResourceLocation.withDefaultNamespace("armor"),
                3,
                AttributeModifier.Operation.ADD_VALUE);
    }
}
