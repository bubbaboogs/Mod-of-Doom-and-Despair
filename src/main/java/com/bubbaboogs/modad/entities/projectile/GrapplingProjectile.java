package com.bubbaboogs.modad.entities.projectile;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class GrapplingProjectile extends Projectile {
    public GrapplingProjectile(EntityType<? extends Projectile> entityType, Level world) {
        super(entityType, world);
    }
    boolean inGround = false;

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    protected void pullHookedEntity(Entity entity) {
        Entity entity2 = this.getOwner();
        if (entity2 != null) {
            Vec3 vec3d = (new Vec3(entity2.getX() - this.getX(), entity2.getY() - this.getY(), entity2.getZ() - this.getZ())).scale(0.1);
            entity.setDeltaMovement(entity.getDeltaMovement().add(vec3d));
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(!inGround){
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.99));
        }
        else{
            this.setDeltaMovement(Vec3.ZERO);
        }
        if (this.tickCount > 60) {
            this.discard();
        }
    }


    public void setOwner(@Nullable Entity owner) {
        super.setOwner(owner);
    }

    @Nullable
    public Player getPlayerOwner() {
        Entity entity = this.getOwner();
        Player var10000;
        if (entity instanceof Player playerEntity) {
            var10000 = playerEntity;
        } else {
            var10000 = null;
        }
        return var10000;
    }
}
