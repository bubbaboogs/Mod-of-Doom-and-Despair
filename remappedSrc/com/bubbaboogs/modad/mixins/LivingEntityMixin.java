package com.bubbaboogs.modad.mixins;

import com.bubbaboogs.modad.attributes.Regeneration;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo ci){
        Regeneration.tick((LivingEntity) (Object) this);
    }
}
