package com.bubbaboogs.modad;

import com.bubbaboogs.modad.weapons.EvilSmasherItem;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ModEvents {

    public static void initialize(){
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
            if (damageSource.getEntity() instanceof Player player) {
                ItemStack stack = player.getMainHandItem();
                if (stack.getItem() instanceof EvilSmasherItem smasher) {
                    int boost = smasher.getBoost(stack);
                    if (boost < 10) {
                        smasher.setBoost(stack, boost + 1);
                        smasher.updateBoosts(stack);
                    }
                }
            }
        });
    }
}
