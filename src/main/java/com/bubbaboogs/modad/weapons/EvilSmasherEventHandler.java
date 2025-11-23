package com.bubbaboogs.modad.weapons;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class EvilSmasherEventHandler {
    public static void register() {
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (entity instanceof LivingEntity) {
                checkWeaponChange(player, hand);
            }
            return InteractionResult.PASS;
        });

        ServerLivingEntityEvents.AFTER_DAMAGE.register((livingEntity, damageSource, amount, attackedEntity, context) -> {
            if (livingEntity instanceof Player player) {
                for (ItemStack stack : player.getInventory()) {
                    if (stack.getItem() instanceof EvilSmasherItem evilSmasherItem) {
                        evilSmasherItem.setBoost(stack, evilSmasherItem.getBoost(stack) - 1);
                        evilSmasherItem.updateBoosts(stack);
                    }
                }
            }
        });
    }

    private static void checkWeaponChange(Player player, InteractionHand hand) {
        ItemStack heldItem = player.getItemInHand(hand);

        // If the player attacks with a different weapon, reset all Evil Smasher boosts
        if (!(heldItem.getItem() instanceof EvilSmasherItem)) {
            resetEvilSmasherBoosts(player);
        }
    }

    private static void resetEvilSmasherBoosts(Player player) {
        for (ItemStack stack : player.getInventory()) {
            if (stack.getItem() instanceof EvilSmasherItem) {
                ((EvilSmasherItem) stack.getItem()).setBoost(stack, 0);
                ((EvilSmasherItem) stack.getItem()).updateBoosts(stack);
            }
        }
    }
}
