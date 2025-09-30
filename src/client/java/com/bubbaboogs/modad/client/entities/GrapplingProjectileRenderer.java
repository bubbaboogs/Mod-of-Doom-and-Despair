package com.bubbaboogs.modad.client.entities;

import com.bubbaboogs.modad.entities.projectile.GrapplingProjectile;
import com.bubbaboogs.modad.items.GrapplingHookItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustColorTransitionParticleEffect;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;

import java.awt.*;

@Environment(EnvType.CLIENT)
public class GrapplingProjectileRenderer extends EntityRenderer<GrapplingProjectile, GrapplingProjectileEntityRenderState> {
    public GrapplingProjectileRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    EntityRenderDispatcher entityRenderDispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();
    private static final Identifier TEXTURE = Identifier.ofVanilla("textures/item/chain.png");

    public boolean shouldRender(GrapplingProjectile grapplingProjectile, Frustum frustum, double d, double e, double f) {
        //return super.shouldRender(grapplingProjectile, frustum, d, e, f) && grapplingProjectile.getPlayerOwner() != null;
        return true;
    }

    @Override
    public void render(GrapplingProjectileEntityRenderState state, MatrixStack matrixStack, VertexConsumerProvider vertexConsumers, int light){
        spawnChainParticles(state);
    }
    @Override
    public void updateRenderState(GrapplingProjectile entity,
                                  GrapplingProjectileEntityRenderState state,
                                  float tickDelta) {
        super.updateRenderState(entity, state, tickDelta);
        state.entity = entity;
        PlayerEntity player = entity.getPlayerOwner();
        if (player != null) {
            Vec3d hand = getHandPos(player, 0.0F);
            if (hand != null) {
                state.handPos = hand;
            }
        }
    }
    private Vec3d getHandPos(PlayerEntity player, float tickDelta) {
        Arm arm = player.getMainHandStack().getItem() instanceof GrapplingHookItem ? player.getMainArm() : player.getMainArm().getOpposite();
        boolean right = arm == Arm.RIGHT;

        double xOffset = (right ? 0.35 : -0.35) * player.getScale();
        double yOffset = (player.isInSneakingPose() ? -0.2 : 0.0) * player.getScale();
        double zOffset = 0.1 * player.getScale();

        float yawRad = (float) Math.toRadians(MathHelper.lerp(tickDelta, player.lastBodyYaw, player.bodyYaw));
        double sin = MathHelper.sin(-yawRad);
        double cos = MathHelper.cos(-yawRad);

        double handX = player.getX() + xOffset * cos - zOffset * sin;
        double handY = player.getY() + player.getStandingEyeHeight() * 0.45 + yOffset;
        double handZ = player.getZ() + zOffset * cos + xOffset * sin;

        return new Vec3d(handX, handY, handZ);
    }

    private static float percentage(int value, int max) {
        return (float)value / (float)max;
    }

    private static void vertex(VertexConsumer buffer, MatrixStack.Entry matrix, int light, float x, int y, int u, int v) {
        buffer.vertex(matrix, x - 0.5F, (float)y - 0.5F, 0.0F)
                .color(-1)
                .texture((float)u, (float)v)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(light)
                .normal(matrix, 0.0F, 1.0F, 0.0F);
    }



    public static Arm getArmHoldingGrapple(PlayerEntity player) {
        return player.getMainHandStack().getItem() instanceof GrapplingHookItem ? player.getMainArm() : player.getMainArm().getOpposite();
    }

    public GrapplingProjectileEntityRenderState createRenderState() {
        return new GrapplingProjectileEntityRenderState();
    }

    private void spawnChainParticles(GrapplingProjectileEntityRenderState state) {
        if (state.handPos == null || state.entity == null) return;

        Vec3d start = state.handPos;
        Vec3d end = state.entity.getPos();
        Vec3d diff = end.subtract(start);

        int count = Math.max(2, (int) diff.length() * 4); // particle density
        Vec3d axis = diff.normalize();

        // Pick a perpendicular vector for the oscillation
        Vec3d up = Math.abs(axis.y) < 0.99 ? new Vec3d(0,1,0) : new Vec3d(1,0,0);
        Vec3d side = axis.crossProduct(up).normalize().multiply(0.1); // chain thickness

        for (int i = 0; i <= count; i++) {
            double t = (double) i / count;
            Vec3d pos = start.add(diff.multiply(t));

            // oscillate perpendicular to the line
            double angle = t * Math.PI * 4; // 2 full waves along the chain
            Vec3d offset = side.multiply(Math.sin(angle));

            Vec3d particlePos = pos.add(offset);

            MinecraftClient.getInstance().world.addParticleClient(
                    new DustColorTransitionParticleEffect(Color.DARK_GRAY.getRGB(), Color.DARK_GRAY.getRGB(), 1), // or ParticleTypes.SMOKE, SPARK, etc
                    particlePos.x, particlePos.y, particlePos.z,
                    0, 0, 0
            );
        }
    }

}
