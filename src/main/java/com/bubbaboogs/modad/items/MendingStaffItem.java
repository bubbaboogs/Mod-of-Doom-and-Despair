package com.bubbaboogs.modad.items;

import com.bubbaboogs.modad.entities.ModEntities;
import com.bubbaboogs.modad.entities.living.MenderEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MendingStaffItem extends Item {
    public MendingStaffItem(Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        MenderEntity menderEntity = new MenderEntity(ModEntities.MENDER, world);
        menderEntity.owner = user;
        menderEntity.noPhysics = true;
        menderEntity.setPos(user.position());
        world.addFreshEntity(menderEntity);
        user.getCooldowns().addCooldown(user.getItemInHand(hand), 800);
        ItemStack stack = user.getItemInHand(hand);
        if (!stack.isEmpty() && stack.isDamageableItem()) {
            stack.hurtWithoutBreaking(1, user);
        }
        return InteractionResult.SUCCESS;
    }
}
