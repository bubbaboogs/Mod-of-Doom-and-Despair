package com.bubbaboogs.modad.mixins;

import com.bubbaboogs.modad.attributes.Regeneration;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// TODO(Ravel): can not resolve target class LivingEntity
// TODO(Ravel): can not resolve target class LivingEntity
// TODO(Ravel): can not resolve target class LivingEntity
@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    // TODO(Ravel): no target class
// TODO(Ravel): no target class
// TODO(Ravel): no target class
    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo ci){
        Regeneration.tick((LivingEntity) (Object) this);
    }
}
