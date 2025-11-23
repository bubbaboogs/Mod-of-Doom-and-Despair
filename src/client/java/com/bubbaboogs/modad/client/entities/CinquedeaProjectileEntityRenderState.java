package com.bubbaboogs.modad.client.entities;


import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class CinquedeaProjectileEntityRenderState extends EntityRenderState {
    public float yaw;
    public float pitch;
    public float lastYaw;
    public float lastPitch;
    public float interpolatedYaw;
    public float interpolatedPitch;
    public net.minecraft.world.item.ItemStack item;
    public int light;
    @Nullable
    public Level world;
    public ItemStackRenderState itemStackRenderState;

    public float getLastPitch() {
        return lastPitch;
    }

    public float getLastYaw() {
        return lastYaw;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }
}
