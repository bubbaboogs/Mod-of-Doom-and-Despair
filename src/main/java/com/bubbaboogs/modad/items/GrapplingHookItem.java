package com.bubbaboogs.modad.items;

import com.bubbaboogs.modad.entities.ModEntities;
import com.bubbaboogs.modad.entities.projectile.GrapplingProjectile;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class GrapplingHookItem extends Item {
    public GrapplingHookItem(Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        user.swing(hand);
        if (!world.isClientSide()) {
            GrapplingProjectile projectile = new GrapplingProjectile(ModEntities.GRAPPLING_PROJECTILE, world);
            projectile.setOwner(user);
            projectile.snapTo(
                    user.getX(), user.getEyeY() - 0.1, user.getZ(),
                    user.getYRot(), user.getXRot()
            );
            projectile.shootFromRotation(user, user.getXRot(), user.getYRot(), 0.0F, 2.5F, 1.0F);
            world.addFreshEntity(projectile);
        }
        return InteractionResult.SUCCESS;
    }

}
