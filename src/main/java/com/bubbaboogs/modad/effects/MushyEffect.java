package com.bubbaboogs.modad.effects;

import com.bubbaboogs.modad.ModAttributes;
import com.bubbaboogs.modad.ModOfDoomAndDespair;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;


public class MushyEffect extends StatusEffect {

    public MushyEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x85FFED);

        this.addAttributeModifier(
                ModAttributes.REGENERATION, Identifier.of(ModOfDoomAndDespair.MOD_ID, "regen"),
                1,
                EntityAttributeModifier.Operation.ADD_VALUE
        );
        this.addAttributeModifier(
                EntityAttributes.ARMOR, Identifier.ofVanilla("armor"),
                3,
                EntityAttributeModifier.Operation.ADD_VALUE);
    }
}
