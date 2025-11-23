package com.bubbaboogs.modad.entities.living;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class MenderEntity extends Mob {
    public LivingEntity owner;
    private float orbitAngle = 0f;
    public int timeToLive = 400;
    public int healSeconds = 5;
    public MenderEntity(EntityType<? extends Mob> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.FOLLOW_RANGE, 16.0)
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.GRAVITY, 0);
    }

    @Override
    public boolean canBeCollidedWith(@Nullable Entity entity) {
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        if (owner != null && this.tickCount % (healSeconds * 20) == 0) {
            float healAmount = 4.0F;
            owner.heal(healAmount);

            if (this.level() instanceof ServerLevel serverWorld) {
                for (int i = 0; i < 10; i++) {
                    double vx = (random.nextDouble() - 0.5) * 0.5;
                    double vy = (random.nextDouble() - 0.5) * 0.5;
                    double vz = (random.nextDouble() - 0.5) * 0.5;
                    serverWorld.sendParticles(
                            ParticleTypes.COMPOSTER,
                            this.getX(), this.getY(), this.getZ(),
                            1,
                            vx, vy, vz,
                            0.0D
                    );
                }
            }

            level().playSound(
                    null,
                    getX(), getY(), getZ(),
                    SoundEvents.BEACON_POWER_SELECT,
                    SoundSource.NEUTRAL,
                    0.5f, 2.0f
            );
        }

        if (!this.level().isClientSide()) {
            if (this.tickCount >= timeToLive) {
                makePoofParticles();
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
            this.setDeltaMovement(
                    (targetX - this.getX()) * 0.3,
                    (targetY - this.getY()) * 0.3,
                    (targetZ - this.getZ()) * 0.3
            );
            this.lookAt(owner, 360.0F, 360.0F);
            this.hurtMarked = true;
        }
    }
}
