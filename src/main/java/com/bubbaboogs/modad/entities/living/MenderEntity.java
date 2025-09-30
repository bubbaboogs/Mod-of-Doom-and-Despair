package com.bubbaboogs.modad.entities.living;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Arm;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MenderEntity extends MobEntity {
    public LivingEntity owner;
    private float orbitAngle = 0f;
    public int timeToLive = 400;
    public int healSeconds = 5;
    public MenderEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public Arm getMainArm() {
        return Arm.RIGHT;
    }

    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(EntityAttributes.FOLLOW_RANGE, 16.0)
                .add(EntityAttributes.MAX_HEALTH, 10)
                .add(EntityAttributes.GRAVITY, 0);
    }

    @Override
    public boolean isCollidable(@Nullable Entity entity) {
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        if (owner != null && this.age % (healSeconds * 20) == 0) {
            float healAmount = 4.0F;
            owner.heal(healAmount);

            if (this.getWorld() instanceof ServerWorld serverWorld) {
                for (int i = 0; i < 10; i++) {
                    double vx = (random.nextDouble() - 0.5) * 0.5;
                    double vy = (random.nextDouble() - 0.5) * 0.5;
                    double vz = (random.nextDouble() - 0.5) * 0.5;
                    serverWorld.spawnParticles(
                            ParticleTypes.COMPOSTER,
                            this.getX(), this.getY(), this.getZ(),
                            1,
                            vx, vy, vz,
                            0.0D
                    );
                }
            }

            getWorld().playSound(
                    null,
                    getX(), getY(), getZ(),
                    SoundEvents.BLOCK_BEACON_POWER_SELECT,
                    SoundCategory.NEUTRAL,
                    0.5f, 2.0f
            );
        }

        if (!this.getWorld().isClient) {
            if (this.age >= timeToLive) {
                addDeathParticles();
                this.discard();
                return;
            }
        }

        if (owner != null) {
            orbitAngle += 0.1f;

            double radius = 2.0;
            double xOffset = Math.cos(orbitAngle) * radius;
            double zOffset = Math.sin(orbitAngle) * radius;

            double targetX = owner.getX() + xOffset;
            double targetY = owner.getY() + 1.0;
            double targetZ = owner.getZ() + zOffset;

            this.getNavigation().stop();
            this.setVelocity(
                    (targetX - this.getX()) * 0.3,
                    (targetY - this.getY()) * 0.3,
                    (targetZ - this.getZ()) * 0.3
            );
            this.lookAtEntity(owner, 360.0F, 360.0F);
            this.velocityModified = true;
        }
    }
}
