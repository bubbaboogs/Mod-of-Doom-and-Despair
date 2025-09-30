package com.bubbaboogs.modad;

import com.bubbaboogs.modad.client.entities.CinquedeaProjectileRenderer;
import com.bubbaboogs.modad.client.entities.GrapplingProjectileRenderer;
import com.bubbaboogs.modad.client.entities.MenderEntityModel;
import com.bubbaboogs.modad.client.entities.MenderEntityRenderer;
import com.bubbaboogs.modad.entities.ModEntities;
import com.bubbaboogs.modad.weapons.ClawItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

public class ModOfDoomAndDespairClient implements ClientModInitializer {

	public static final EntityModelLayer MODEL_MENDER_LAYER = new EntityModelLayer(Identifier.of(ModOfDoomAndDespair.MOD_ID, "mender_entity"), "main");
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.putBlock(ModBlocks.PLATFORM, BlockRenderLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(ModBlocks.SHEET_METAL_GRATE, BlockRenderLayer.CUTOUT);
		EntityRendererRegistry.register(ModEntities.CINQUEDEA, CinquedeaProjectileRenderer::new);
		EntityRendererRegistry.register(ModEntities.GRAPPLING_PROJECTILE, GrapplingProjectileRenderer::new);
		EntityRendererRegistry.register(ModEntities.MENDER, MenderEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(MODEL_MENDER_LAYER, MenderEntityModel::getTexturedModelData);

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player == null || client.world == null) return;

			if (client.options.attackKey.isPressed()) {
				if (client.player.getMainHandStack().getItem() instanceof ClawItem) {
					Entity target = getEntityInCrosshair(client);
					if (target != null && client.player.getAttackCooldownProgress(0) >= 1.0f) {
						client.interactionManager.attackEntity(client.player, target);
						client.player.swingHand(Hand.MAIN_HAND);
					}
                    /*else if (client.player.getAttackCooldownProgress(0) >= 1.0f) {
                        client.player.swingHand(Hand.MAIN_HAND);
                    }*/
				}
			}
		});
	}

	private Entity getEntityInCrosshair(MinecraftClient client) {
		if (client.crosshairTarget != null && client.crosshairTarget.getType() == HitResult.Type.ENTITY) {
			return ((EntityHitResult) client.crosshairTarget).getEntity();
		}
		return null;
	}
}