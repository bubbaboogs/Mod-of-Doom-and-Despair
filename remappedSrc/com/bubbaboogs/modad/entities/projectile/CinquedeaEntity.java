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

public class CinquedeaEntity extends PersistentProjectileEntity {

    public CinquedeaEntity(World world, LivingEntity owner, ItemStack stack) {
        super(ModEntities.CINQUEDEA, owner, world, stack, null);
    }

    public CinquedeaEntity(World world, PlayerEntity player) {
        this(world, player, new ItemStack(ModItems.CINQUEDEA));
    }

    public CinquedeaEntity(World world, double x, double y, double z, ItemStack stack) {
        super(ModEntities.CINQUEDEA, world);
        this.setPosition(x, y, z);
        this.setStack(stack);
    }

    public CinquedeaEntity(EntityType<? extends CinquedeaEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.CINQUEDEA);
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
