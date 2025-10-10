package com.bubbaboogs.modad.items;

import com.bubbaboogs.modad.ModBlocks;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class LifeCrystalItem extends BlockItem {
    public LifeCrystalItem(net.minecraft.item.Item.Settings settings) {
        super(ModBlocks.LIFE_CRYSTAL, settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if(!world.isClient()){
            EntityAttributeInstance health = user.getAttributeInstance(EntityAttributes.MAX_HEALTH);
            if(health != null){
                if(!(health.getBaseValue() >= 80)) {
                    health.setBaseValue(health.getBaseValue() + 2);
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
