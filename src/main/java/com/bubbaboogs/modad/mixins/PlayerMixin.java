package com.bubbaboogs.modad.mixins;

import com.bubbaboogs.modad.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// TODO(Ravel): can not resolve target class PlayerEntity
// TODO(Ravel): can not resolve target class PlayerEntity
// TODO(Ravel): can not resolve target class PlayerEntity
@Mixin(Player.class)
abstract public class PlayerMixin extends LivingEntity {
    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow @Final private Inventory inventory;

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
// TODO(Ravel): no target class
    @Inject(method = "causeFallDamage", at = @At("HEAD"), cancellable = true)
    private void botansickleCancelFallDamage(double fallDistance, float damagePerDistance, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if ((Boolean.TRUE.equals(this.getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.BOTANSICKLE))) && this.fallDistance >= 10)
        {
            cir.cancel();
        }
    }
}
