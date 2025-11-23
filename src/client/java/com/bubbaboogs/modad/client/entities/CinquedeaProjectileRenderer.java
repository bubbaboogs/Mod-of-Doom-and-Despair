package com.bubbaboogs.modad.client.entities;

import com.bubbaboogs.modad.entities.projectile.CinquedeaEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class CinquedeaProjectileRenderer extends EntityRenderer<CinquedeaEntity, CinquedeaProjectileEntityRenderState> {
    private final ItemRenderer model;
    private final EntityRendererProvider.Context context;

    public CinquedeaProjectileRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.context = ctx;
        this.model = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public CinquedeaProjectileEntityRenderState createRenderState(){
        return new CinquedeaProjectileEntityRenderState();
    }


    @Override
    public void extractRenderState(CinquedeaEntity entity, CinquedeaProjectileEntityRenderState state, float tickProgress) {
        super.extractRenderState(entity, state, tickProgress);

        float yawDiff = Mth.wrapDegrees(entity.getYRot() - entity.yRotO);
        state.interpolatedYaw = entity.yRotO + yawDiff * tickProgress;

        float pitchDiff = Mth.wrapDegrees(entity.getXRot() - entity.xRotO);
        state.interpolatedPitch = entity.xRotO + pitchDiff * tickProgress;

        state.item = entity.getDefaultPickupItem();
        ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
        this.context.getItemModelResolver().updateForTopItem(
                itemStackRenderState,
                entity.getDefaultPickupItem(),
                ItemDisplayContext.GROUND,
                entity.level(),
                null,
                entity.getId()
        );
        state.itemStackRenderState = itemStackRenderState;
        state.light = LevelRenderer.getLightColor(entity.level(), entity.blockPosition());

        // store anything else you need during render
        state.world = entity.level();
    }

    @Override
    public void submit(CinquedeaProjectileEntityRenderState state, PoseStack matrices, SubmitNodeCollector queue, CameraRenderState cameraState) {
        ItemStack stack = state.item;
        matrices.pushPose();
        matrices.translate(0.0, 0.25, 0.0);
        matrices.scale(0.5f, 0.5f, 0.5f);

        matrices.mulPose(Axis.YP.rotationDegrees(state.interpolatedYaw - 90.0f));
        matrices.mulPose(Axis.ZP.rotationDegrees(state.interpolatedPitch + 90.0f));
        state.itemStackRenderState.submit(matrices, queue, state.light, OverlayTexture.NO_OVERLAY, 0);
        matrices.popPose();

        super.submit(state, matrices, queue, cameraState);
    }
}
