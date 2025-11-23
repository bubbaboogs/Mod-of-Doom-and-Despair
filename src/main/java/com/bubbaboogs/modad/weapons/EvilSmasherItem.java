package com.bubbaboogs.modad.weapons;

import com.bubbaboogs.modad.ModOfDoomAndDespair;
import com.bubbaboogs.modad.components.ModComponents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import java.util.List;
import java.util.function.Consumer;

public class EvilSmasherItem extends Item {

    public static final ResourceLocation EVIL_SMASHER_ATTACK_MODIFIER = ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "evil_smasher_attack_damage");
    public static final ResourceLocation EVIL_SMASHER_KNOCKBACK_MODIFIER = ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "evil_smasher_knockback");
    public static final ResourceLocation EVIL_SMASHER_SPEED_MODIFIER = ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "evil_smasher_attack_speed");

    public static final float ATTACK_DAMAGE = 19.0f;
    public static final float ATTACK_SPEED = -3.4f;

    public EvilSmasherItem(Item.Properties settings) {
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
        var modifiers = stack.get(DataComponents.ATTRIBUTE_MODIFIERS);

        AttributeModifier damageModifier = new AttributeModifier(EVIL_SMASHER_ATTACK_MODIFIER, getBoost(stack) * 2, AttributeModifier.Operation.ADD_VALUE);
        AttributeModifier knockbackModifier = new AttributeModifier(EVIL_SMASHER_KNOCKBACK_MODIFIER, getBoost(stack) * 0.5, AttributeModifier.Operation.ADD_VALUE);
        AttributeModifier attackSpeedModifier = new AttributeModifier(EVIL_SMASHER_SPEED_MODIFIER, getBoost(stack) * 0.1f, AttributeModifier.Operation.ADD_VALUE);

        modifiers = modifiers.withModifierAdded(Attributes.ATTACK_DAMAGE, damageModifier, EquipmentSlotGroup.MAINHAND);
        modifiers = modifiers.withModifierAdded(Attributes.ATTACK_KNOCKBACK, knockbackModifier, EquipmentSlotGroup.MAINHAND);
        modifiers = modifiers.withModifierAdded(Attributes.ATTACK_SPEED, attackSpeedModifier, EquipmentSlotGroup.MAINHAND);

        stack.set(DataComponents.ATTRIBUTE_MODIFIERS, modifiers);

        stack.set(DataComponents.ATTRIBUTE_MODIFIERS, modifiers);
    }

    public int getBoost(ItemStack stack) {
        return stack.getOrDefault(ModComponents.EVIL_SMASHER_BOOST, 0);
    }

    public void setBoost(ItemStack stack, int boost) {
        stack.set(ModComponents.EVIL_SMASHER_BOOST, boost);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        int boost = stack.getOrDefault(ModComponents.EVIL_SMASHER_BOOST, 0); // Use getOrDefault
        textConsumer.accept(Component.literal("For every enemy you kill this hammer gains stat bonuses").withStyle(ChatFormatting.ITALIC));
        textConsumer.accept(Component.literal("These bonuses stack until a cap is reached").withStyle(ChatFormatting.ITALIC));
        textConsumer.accept(Component.literal("The bonus stacks will reset if you attack with a different weapon").withStyle(ChatFormatting.ITALIC));
        textConsumer.accept(Component.literal("The bonus stacks will be reduced by 1 every time you get hit").withStyle(ChatFormatting.ITALIC));
        textConsumer.accept(Component.literal("EViL! sMaSH eVIl! SmAsh... ER!'").withStyle(ChatFormatting.BLUE, ChatFormatting.ITALIC));
        textConsumer.accept(Component.translatable("item.modad.evil_smasher.boost", (boost * 10)).withStyle(ChatFormatting.GOLD));
    }

    public static ItemAttributeModifiers createAttributeModifiers() {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, ATTACK_SPEED, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, ATTACK_DAMAGE, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .build();
    }
}
