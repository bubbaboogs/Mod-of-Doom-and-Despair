package com.bubbaboogs.modad.weapons;

import com.bubbaboogs.modad.ModOfDoomAndDespair;
import com.bubbaboogs.modad.components.ModComponents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.LivingEntity;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class EvilSmasherItem extends Item {

    public static final Identifier EVIL_SMASHER_ATTACK_MODIFIER = Identifier.of(ModOfDoomAndDespair.MOD_ID, "evil_smasher_attack_damage");
    public static final Identifier EVIL_SMASHER_KNOCKBACK_MODIFIER = Identifier.of(ModOfDoomAndDespair.MOD_ID, "evil_smasher_knockback");
    public static final Identifier EVIL_SMASHER_SPEED_MODIFIER = Identifier.of(ModOfDoomAndDespair.MOD_ID, "evil_smasher_attack_speed");

    public static final float ATTACK_DAMAGE = 19.0f;
    public static final float ATTACK_SPEED = -3.4f;

    public EvilSmasherItem(Item.Settings settings) {
        super(settings);
    }

    /*@Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postDamageEntity(stack, target, attacker);

        int boost = getBoost(stack);

        if (boost < 10 && target.isDead()) {
            boost++;
            setBoost(stack, boost);
            updateBoosts(stack);
        }
    }*/

    public void updateBoosts (ItemStack stack){
        var modifiers = stack.get(DataComponentTypes.ATTRIBUTE_MODIFIERS);

        EntityAttributeModifier damageModifier = new EntityAttributeModifier(EVIL_SMASHER_ATTACK_MODIFIER, getBoost(stack) * 2, EntityAttributeModifier.Operation.ADD_VALUE);
        EntityAttributeModifier knockbackModifier = new EntityAttributeModifier(EVIL_SMASHER_KNOCKBACK_MODIFIER, getBoost(stack) * 0.5, EntityAttributeModifier.Operation.ADD_VALUE);
        EntityAttributeModifier attackSpeedModifier = new EntityAttributeModifier(EVIL_SMASHER_SPEED_MODIFIER, getBoost(stack) * 0.1f, EntityAttributeModifier.Operation.ADD_VALUE);

        modifiers = modifiers.with(EntityAttributes.ATTACK_DAMAGE, damageModifier, AttributeModifierSlot.MAINHAND);
        modifiers = modifiers.with(EntityAttributes.ATTACK_KNOCKBACK, knockbackModifier, AttributeModifierSlot.MAINHAND);
        modifiers = modifiers.with(EntityAttributes.ATTACK_SPEED, attackSpeedModifier, AttributeModifierSlot.MAINHAND);

        stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, modifiers);

        stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, modifiers);
    }

    public int getBoost(ItemStack stack) {
        return stack.getOrDefault(ModComponents.EVIL_SMASHER_BOOST, 0);
    }

    public void setBoost(ItemStack stack, int boost) {
        stack.set(ModComponents.EVIL_SMASHER_BOOST, boost);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        int boost = stack.getOrDefault(ModComponents.EVIL_SMASHER_BOOST, 0); // Use getOrDefault
        textConsumer.accept(Text.literal("For every enemy you kill this hammer gains stat bonuses").formatted(Formatting.ITALIC));
        textConsumer.accept(Text.literal("These bonuses stack until a cap is reached").formatted(Formatting.ITALIC));
        textConsumer.accept(Text.literal("The bonus stacks will reset if you attack with a different weapon").formatted(Formatting.ITALIC));
        textConsumer.accept(Text.literal("The bonus stacks will be reduced by 1 every time you get hit").formatted(Formatting.ITALIC));
        textConsumer.accept(Text.literal("EViL! sMaSH eVIl! SmAsh... ER!'").formatted(Formatting.BLUE, Formatting.ITALIC));
        textConsumer.accept(Text.translatable("item.modad.evil_smasher.boost", (boost * 10)).formatted(Formatting.GOLD));
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(EntityAttributes.ATTACK_SPEED, new EntityAttributeModifier(Item.BASE_ATTACK_SPEED_MODIFIER_ID, ATTACK_SPEED, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                .add(EntityAttributes.ATTACK_DAMAGE, new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, ATTACK_DAMAGE, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                .build();
    }
}
