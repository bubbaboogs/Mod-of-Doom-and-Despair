package com.bubbaboogs.modad.entities.projectile;

import com.bubbaboogs.modad.ModItems;
import com.bubbaboogs.modad.entities.ModEntities;
import java.util.Objects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class GildedDaggerEntity extends AbstractArrow {

    public GildedDaggerEntity(Level world, LivingEntity owner, ItemStack stack) {
        super(ModEntities.CINQUEDEA, owner, world, stack, null);
    }

    public GildedDaggerEntity(Level world, Player player) {
        this(world, player, new ItemStack(ModItems.GILDED_DAGGER));
    }

    public GildedDaggerEntity(Level world, double x, double y, double z, ItemStack stack) {
        super(ModEntities.GILDED_DAGGER, world);
        this.setPos(x, y, z);
        this.setPickupItemStack(stack);
    }

    public GildedDaggerEntity(EntityType<? extends GildedDaggerEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.GILDED_DAGGER);
    }

    public boolean isGrounded() {
        return isInGround();
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();

        if (!this.level().isClientSide()) {
            ServerLevel serverWorld = (ServerLevel) this.level();
            entity.hurtServer(serverWorld,
                    this.damageSources().thrown(this, this.getOwner()),
                    6.0F);
            serverWorld.broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        this.discard();
    }

    @Override
    protected void tickDespawn() {
        ++super.tickCount;
        if (this.tickCount >= 200) {
            this.discard();
        }
    }
}
