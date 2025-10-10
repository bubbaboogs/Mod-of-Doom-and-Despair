package com.bubbaboogs.modad.items;

import com.bubbaboogs.modad.entities.ModEntities;
import com.bubbaboogs.modad.entities.projectile.GrapplingProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class GrapplingHookItem extends Item {
    public GrapplingHookItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        user.swingHand(hand);
        if (!world.isClient()) {
            GrapplingProjectile projectile = new GrapplingProjectile(ModEntities.GRAPPLING_PROJECTILE, world);
            projectile.setOwner(user);
            projectile.refreshPositionAndAngles(
                    user.getX(), user.getEyeY() - 0.1, user.getZ(),
                    user.getYaw(), user.getPitch()
            );
            projectile.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.5F, 1.0F);
            world.spawnEntity(projectile);
        }
        return ActionResult.SUCCESS;
    }

}
