package com.bubbaboogs.modad.client.entities;

import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class CinquedeaProjectileEntityRenderState extends EntityRenderState {
    public float yaw;
    public float pitch;
    public float lastYaw;
    public float lastPitch;
    public float interpolatedYaw;
    public float interpolatedPitch;
    public ItemStack item;
    @Nullable
    public net.minecraft.world.World world;

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
