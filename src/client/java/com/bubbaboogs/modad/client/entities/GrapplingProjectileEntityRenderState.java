package com.bubbaboogs.modad.client.entities;

import com.bubbaboogs.modad.entities.projectile.GrapplingProjectile;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.util.math.Vec3d;

public class GrapplingProjectileEntityRenderState extends EntityRenderState {
    public Vec3d position;
    public GrapplingProjectile entity;
    public Vec3d handPos;
}
