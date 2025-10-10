package com.bubbaboogs.modad.items;

import com.bubbaboogs.modad.entities.ModEntities;
import com.bubbaboogs.modad.entities.living.MenderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class MendingStaffItem extends Item {
    public MendingStaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        MenderEntity menderEntity = new MenderEntity(ModEntities.MENDER, world);
        menderEntity.owner = user;
        menderEntity.noClip = true;
        menderEntity.setPosition(user.getEntityPos());
        world.spawnEntity(menderEntity);
        user.getItemCooldownManager().set(user.getStackInHand(hand), 800);
        ItemStack stack = user.getStackInHand(hand);
        if (!stack.isEmpty() && stack.isDamageable()) {
            stack.damage(1, user);
        }
        return ActionResult.SUCCESS;
    }
}
