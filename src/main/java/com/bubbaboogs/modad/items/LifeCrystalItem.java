package com.bubbaboogs.modad.items;

import com.bubbaboogs.modad.ModBlocks;
import com.bubbaboogs.modad.ModOfDoomAndDespair;
import com.bubbaboogs.modad.components.LifeCrystalsUsedComponent;
import com.bubbaboogs.modad.components.ModCardinalComponents;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class LifeCrystalItem extends BlockItem {
    public LifeCrystalItem(Settings settings) {
        super(ModBlocks.LIFE_CRYSTAL, settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if(!world.isClient()){
            EntityAttributeInstance health = user.getAttributeInstance(EntityAttributes.MAX_HEALTH);
            LifeCrystalsUsedComponent lifeCrystalsUsedComponent = ModCardinalComponents.LIFE_CRYSTALS_USED.get(user);
            ModOfDoomAndDespair.LOGGER.info(Integer.toString(lifeCrystalsUsedComponent.getValue()));
            if(health != null){
                if(!(lifeCrystalsUsedComponent.getValue() >= 10)) {
                    health.setBaseValue(health.getBaseValue() + 2);
                    lifeCrystalsUsedComponent.increment();
                    ModCardinalComponents.LIFE_CRYSTALS_USED.sync(user);
                }
                else{
                    return ActionResult.PASS;
                }
            }
            itemStack.decrement(1);
        }

        return ActionResult.SUCCESS;
    }
}
