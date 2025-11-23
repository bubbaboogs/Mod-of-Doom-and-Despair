package com.bubbaboogs.modad.client.entities;

import com.bubbaboogs.modad.entities.projectile.GrapplingProjectile;
import com.bubbaboogs.modad.items.GrapplingHookItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.awt.*;

@Environment(EnvType.CLIENT)
public class GrapplingProjectileRenderer extends EntityRenderer<GrapplingProjectile, GrapplingProjectileEntityRenderState> {
    public GrapplingProjectileRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    EntityRenderDispatcher entityRenderDispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
    private static final ResourceLocation TEXTURE = ResourceLocation.withDefaultNamespace("textures/item/chain.png");

    public boolean shouldRender(GrapplingProjectile grapplingProjectile, Frustum frustum, double d, double e, double f) {
        return super.shouldRender(grapplingProjectile, frustum, d, e, f) && grapplingProjectile.getPlayerOwner() != null;
    }

    @Override
    public void submit(GrapplingProjectileEntityRenderState state, PoseStack matrices, SubmitNodeCollector queue, CameraRenderState cameraState) {
        spawnChainParticles(state);
    }
    @Override
    public void extractRenderState(GrapplingProjectile entity,
                                  GrapplingProjectileEntityRenderState state,
                                  float tickDelta) {
        super.extractRenderState(entity, state, tickDelta);
        state.entity = entity;
        Player player = entity.getPlayerOwner();
        if (player != null) {
            Vec3 hand = getHandPos(player, 0.0F);
            if (hand != null) {
                state.handPos = hand;
            }
        }
    }
    private Vec3 getHandPos(Player player, float tickDelta) {
        HumanoidArm arm = player.getMainHandItem().getItem() instanceof GrapplingHookItem ? player.getMainArm() : player.getMainArm().getOpposite();
        boolean right = arm == HumanoidArm.RIGHT;

        double xOffset = (right ? 0.35 : -0.35) * player.getScale();
        double yOffset = (player.isCrouching() ? -0.2 : 0.0) * player.getScale();
        double zOffset = 0.1 * player.getScale();

        float yawRad = (float) Math.toRadians(Mth.lerp(tickDelta, player.yBodyRotO, player.yBodyRot));
        double sin = Mth.sin(-yawRad);
        double cos = Mth.cos(-yawRad);

        double handX = player.getX() + xOffset * cos - zOffset * sin;
        double handY = player.getY() + player.getEyeHeight() * 0.45 + yOffset;
        double handZ = player.getZ() + zOffset * cos + xOffset * sin;

        return new Vec3(handX, handY, handZ);
    }

    private static float percentage(int value, int max) {
        return (float)value / (float)max;
    }

    private static void vertex(VertexConsumer buffer, PoseStack.Pose matrix, int light, float x, int y, int u, int v) {
        buffer.addVertex(matrix, x - 0.5F, (float)y - 0.5F, 0.0F)
                .setColor(-1)
                .setUv((float)u, (float)v)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(matrix, 0.0F, 1.0F, 0.0F);
    }



    public static HumanoidArm getArmHoldingGrapple(Player player) {
        return player.getMainHandItem().getItem() instanceof GrapplingHookItem ? player.getMainArm() : player.getMainArm().getOpposite();
    }

    public GrapplingProjectileEntityRenderState createRenderState() {
        return new GrapplingProjectileEntityRenderState();
    }

    private void spawnChainParticles(GrapplingProjectileEntityRenderState state) {
        if (state.handPos == null || state.entity == null) return;

        Vec3 start = state.handPos;
        Vec3 end = state.entity.position();
        Vec3 diff = end.subtract(start);

        int count = Math.max(2, (int) diff.length() * 4); // particle density
        Vec3 axis = diff.normalize();

        // Pick a perpendicular vector for the oscillation
        Vec3 up = Math.abs(axis.y) < 0.99 ? new Vec3(0,1,0) : new Vec3(1,0,0);
        Vec3 side = axis.cross(up).normalize().scale(0.1); // chain thickness

        for (int i = 0; i <= count; i++) {
            double t = (double) i / count;
            Vec3 pos = start.add(diff.scale(t));

            // oscillate perpendicular to the line
            double angle = t * Math.PI * 4; // 2 full waves along the chain
            Vec3 offset = side.scale(Math.sin(angle));

            Vec3 particlePos = pos.add(offset);

            Minecraft.getInstance().level.addParticle(
                    new DustColorTransitionOptions(Color.DARK_GRAY.getRGB(), Color.DARK_GRAY.getRGB(), 1), // or ParticleTypes.SMOKE, SPARK, etc
                    particlePos.x, particlePos.y, particlePos.z,
                    0, 0, 0
            );
        }
    }

}
