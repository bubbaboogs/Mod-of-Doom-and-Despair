package com.bubbaboogs.modad.entities.projectile;

import com.bubbaboogs.modad.ModItems;
import com.bubbaboogs.modad.entities.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

import java.util.Objects;

public class GildedDaggerEntity extends PersistentProjectileEntity {

    public GildedDaggerEntity(World world, LivingEntity owner, ItemStack stack) {
        super(ModEntities.CINQUEDEA, owner, world, stack, null);
    }

    public GildedDaggerEntity(World world, PlayerEntity player) {
        this(world, player, new ItemStack(ModItems.GILDED_DAGGER));
    }

    public GildedDaggerEntity(World world, double x, double y, double z, ItemStack stack) {
        super(ModEntities.GILDED_DAGGER, world);
        this.setPosition(x, y, z);
        this.setStack(stack);
    }

    public GildedDaggerEntity(EntityType<? extends GildedDaggerEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.GILDED_DAGGER);
    }

    public boolean isGrounded() {
        return isInGround();
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();

        if (!this.getWorld().isClient()) {
            ServerWorld serverWorld = (ServerWorld) this.getWorld();
            entity.damage(serverWorld,
                    this.getDamageSources().thrown(this, this.getOwner()),
                    6.0F);
            serverWorld.sendEntityStatus(this, (byte) 3);
            this.discard();
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult result) {
        super.onBlockHit(result);
        this.discard();
    }

    @Override
    protected void age() {
        ++super.age;
        if (this.age >= 200) {
            this.discard();
        }
    }
}
