package com.bubbaboogs.modad.mixins;

import com.bubbaboogs.modad.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
abstract public class PlayerMixin extends LivingEntity {
    @Shadow @Final private PlayerInventory inventory;

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "handleFallDamage", at = @At("HEAD"), cancellable = true)
    private void botansickleCancelFallDamage(double fallDistance, float damagePerDistance, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if ((Boolean.TRUE.equals(this.getStackInHand(Hand.MAIN_HAND) == ModItems.BOTANSICKLE.getDefaultStack())) && this.fallDistance >= 10)
        {
            cir.cancel();
        }
    }
}
