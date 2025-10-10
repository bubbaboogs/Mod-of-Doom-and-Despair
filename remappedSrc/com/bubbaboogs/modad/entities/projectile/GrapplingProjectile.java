package com.bubbaboogs.modad.entities.projectile;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ProjectileDeflection;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class GrapplingProjectile extends ProjectileEntity {
    public GrapplingProjectile(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }
    boolean inGround = false;

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    protected void pullHookedEntity(Entity entity) {
        Entity entity2 = this.getOwner();
        if (entity2 != null) {
            Vec3d vec3d = (new Vec3d(entity2.getX() - this.getX(), entity2.getY() - this.getY(), entity2.getZ() - this.getZ())).multiply(0.1);
            entity.setVelocity(entity.getVelocity().add(vec3d));
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(!inGround){
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.99));
        }
        else{
            this.setVelocity(Vec3d.ZERO);
        }
        if (this.age > 60) {
            this.discard();
        }
    }


    public void setOwner(@Nullable Entity owner) {
        super.setOwner(owner);
    }

    @Nullable
    public PlayerEntity getPlayerOwner() {
        Entity entity = this.getOwner();
        PlayerEntity var10000;
        if (entity instanceof PlayerEntity playerEntity) {
            var10000 = playerEntity;
        } else {
            var10000 = null;
        }
        return var10000;
    }
}
