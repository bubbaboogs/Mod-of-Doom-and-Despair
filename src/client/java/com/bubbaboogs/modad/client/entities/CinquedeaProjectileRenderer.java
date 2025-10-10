package com.bubbaboogs.modad.client.entities;

import com.bubbaboogs.modad.ModOfDoomAndDespair;
import com.bubbaboogs.modad.entities.projectile.CinquedeaEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;

public class CinquedeaProjectileRenderer extends EntityRenderer<CinquedeaEntity, CinquedeaProjectileEntityRenderState> {
    private final ItemRenderer model;
    private final EntityRendererFactory.Context context;

    public CinquedeaProjectileRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.context = ctx;
        this.model = MinecraftClient.getInstance().getItemRenderer();
    }

    @Override
    public CinquedeaProjectileEntityRenderState createRenderState(){
        return new CinquedeaProjectileEntityRenderState();
    }


    @Override
    public void updateRenderState(CinquedeaEntity entity, CinquedeaProjectileEntityRenderState state, float tickProgress) {
        super.updateRenderState(entity, state, tickProgress);

        float yawDiff = MathHelper.wrapDegrees(entity.getYaw() - entity.lastYaw);
        state.interpolatedYaw = entity.lastYaw + yawDiff * tickProgress;

        float pitchDiff = MathHelper.wrapDegrees(entity.getPitch() - entity.lastPitch);
        state.interpolatedPitch = entity.lastPitch + pitchDiff * tickProgress;

        state.item = entity.getDefaultItemStack();
        ItemRenderState itemStackRenderState = new ItemRenderState();
        this.context.getItemModelManager().clearAndUpdate(
                itemStackRenderState,
                entity.getDefaultItemStack(),
                ItemDisplayContext.GROUND,
                entity.getEntityWorld(),
                null,
                entity.getId()
        );
        state.itemStackRenderState = itemStackRenderState;
        state.light = WorldRenderer.getLightmapCoordinates(entity.getEntityWorld(), entity.getBlockPos());

        // store anything else you need during render
        state.world = entity.getEntityWorld();
    }

    @Override
    public void render(CinquedeaProjectileEntityRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
        ItemStack stack = state.item;
        matrices.push();
        matrices.translate(0.0, 0.25, 0.0);
        matrices.scale(0.5f, 0.5f, 0.5f);

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(state.interpolatedYaw - 90.0f));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(state.interpolatedPitch + 90.0f));
        state.itemStackRenderState.render(matrices, queue, state.light, OverlayTexture.DEFAULT_UV, 0);
        matrices.pop();

        super.render(state, matrices, queue, cameraState);
    }
}
