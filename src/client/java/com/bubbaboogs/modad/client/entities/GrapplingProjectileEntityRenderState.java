package com.bubbaboogs.modad.client.entities;

import com.bubbaboogs.modad.entities.projectile.GrapplingProjectile;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.phys.Vec3;

public class GrapplingProjectileEntityRenderState extends EntityRenderState {
    public Vec3 position;
    public GrapplingProjectile entity;
    public Vec3 handPos;
}
