package com.bubbaboogs.modad.client.entities;

import com.bubbaboogs.modad.entities.projectile.CinquedeaEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class CinquedeaProjectileRenderer extends EntityRenderer<CinquedeaEntity, CinquedeaProjectileEntityRenderState> {
    private final ItemRenderer model;

    public CinquedeaProjectileRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = MinecraftClient.getInstance().getItemRenderer();
    }

    @Override
    public CinquedeaProjectileEntityRenderState createRenderState(){
        return new CinquedeaProjectileEntityRenderState();
    }


    @Override
    public void updateRenderState(CinquedeaEntity entity, CinquedeaProjectileEntityRenderState state, float tickProgress) {
        super.updateRenderState(entity, state, tickProgress); // keep base behaviour

        // compute interpolated yaw/pitch here and stash them in the state
        float yawDiff = MathHelper.wrapDegrees(entity.getYaw() - entity.lastYaw);
        state.interpolatedYaw = entity.lastYaw + yawDiff * tickProgress;

        float pitchDiff = MathHelper.wrapDegrees(entity.getPitch() - entity.lastPitch);
        state.interpolatedPitch = entity.lastPitch + pitchDiff * tickProgress;

        state.item = entity.getDefaultItemStack();

        // store anything else you need during render
        state.world = entity.getWorld();
    }

    @Override
    public void render(CinquedeaProjectileEntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        ItemStack stack = state.item;

        matrices.push();
        matrices.translate(0.0, 0.25, 0.0);
        matrices.scale(0.5f, 0.5f, 0.5f);

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(state.interpolatedYaw - 90.0f));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(state.interpolatedPitch + 90.0f));

        // Pass world from state (or null if your model accepts it)
        model.renderItem(stack, ItemDisplayContext.GROUND, light, OverlayTexture.DEFAULT_UV,
                matrices, vertexConsumers, state.world, 0);

        matrices.pop();

        // call super.render(state, ...) so base class can render leash, nameplate, etc.
        super.render(state, matrices, vertexConsumers, light);
    }
}
