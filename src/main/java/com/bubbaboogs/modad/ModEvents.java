package com.bubbaboogs.modad;

import com.bubbaboogs.modad.components.ModComponents;
import com.bubbaboogs.modad.weapons.EvilSmasherItem;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

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



        DefaultItemComponentEvents.MODIFY.register(context -> {

            // === 5 minutes for 64 → 94 ticks ===
            int FIVE_MIN = 94;
            context.modify(Items.COBBLED_DEEPSLATE,      c -> c.set(ModComponents.HARVEST_TIME, FIVE_MIN));
            context.modify(Items.COBBLESTONE,    c -> c.set(ModComponents.HARVEST_TIME, FIVE_MIN));
            context.modify(Items.STONE,          c -> c.set(ModComponents.HARVEST_TIME, FIVE_MIN));
            context.modify(ModBlocks.JADEITE.asItem(),     c -> c.set(ModComponents.HARVEST_TIME, FIVE_MIN));
            context.modify(ModBlocks.GABBRO.asItem(),      c -> c.set(ModComponents.HARVEST_TIME, FIVE_MIN));
            context.modify(ModBlocks.GYPSUM.asItem(),      c -> c.set(ModComponents.HARVEST_TIME, FIVE_MIN));
            context.modify(Items.END_STONE,      c -> c.set(ModComponents.HARVEST_TIME, FIVE_MIN));
            context.modify(Items.NETHERRACK,     c -> c.set(ModComponents.HARVEST_TIME, FIVE_MIN));

            // === 20 minutes for 64 → 375 ticks ===
            int TWENTY_MIN = 375;
            context.modify(Items.REDSTONE_BLOCK, c -> c.set(ModComponents.HARVEST_TIME, TWENTY_MIN));
            context.modify(Items.QUARTZ_BLOCK,   c -> c.set(ModComponents.HARVEST_TIME, TWENTY_MIN));

            // === 30 minutes for 1 → 36000 ticks ===
            int THIRTY_MIN = 36000;
            context.modify(ModItems.ECHO_PASTE,   c -> c.set(ModComponents.HARVEST_TIME, THIRTY_MIN));
            context.modify(Items.GLOW_INK_SAC,   c -> c.set(ModComponents.HARVEST_TIME, THIRTY_MIN));

            // === 15 minutes for 64 → 281 ticks ===
            int FIFTEEN_MIN = 281;
            context.modify(Items.INK_SAC,         c -> c.set(ModComponents.HARVEST_TIME, FIFTEEN_MIN));
            context.modify(Items.RESIN_CLUMP,       c -> c.set(ModComponents.HARVEST_TIME, FIFTEEN_MIN));
            context.modify(Items.BONE_BLOCK,     c -> c.set(ModComponents.HARVEST_TIME, FIFTEEN_MIN));

            ItemStack stack = new ItemStack(Items.DEEPSLATE);
            Integer ticks = stack.getComponents().get(ModComponents.HARVEST_TIME);
            ModOfDoomAndDespair.LOGGER.info("DEEPSLATE harvest time: " + ticks); // should print 94

        });
    }
}
