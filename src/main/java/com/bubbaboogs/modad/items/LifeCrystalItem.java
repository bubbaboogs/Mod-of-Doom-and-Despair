package com.bubbaboogs.modad.items;

import com.bubbaboogs.modad.ModBlocks;
import com.bubbaboogs.modad.ModOfDoomAndDespair;
import com.bubbaboogs.modad.components.LifeCrystalsUsedComponent;
import com.bubbaboogs.modad.components.ModCardinalComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LifeCrystalItem extends BlockItem {
    public LifeCrystalItem(Properties settings) {
        super(ModBlocks.LIFE_CRYSTAL, settings);
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        if(!world.isClientSide()){
            AttributeInstance health = user.getAttribute(Attributes.MAX_HEALTH);
            LifeCrystalsUsedComponent lifeCrystalsUsedComponent = ModCardinalComponents.LIFE_CRYSTALS_USED.get(user);
            ModOfDoomAndDespair.LOGGER.info(Integer.toString(lifeCrystalsUsedComponent.getValue()));
            if(health != null){
                if(!(lifeCrystalsUsedComponent.getValue() >= 10)) {
                    health.setBaseValue(health.getBaseValue() + 2);
                    lifeCrystalsUsedComponent.increment();
                    ModCardinalComponents.LIFE_CRYSTALS_USED.sync(user);
                }
                else{
                    return InteractionResult.PASS;
                }
            }
            itemStack.shrink(1);
        }

        return InteractionResult.SUCCESS;
    }
}
