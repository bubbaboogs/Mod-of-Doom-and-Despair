package com.bubbaboogs.modad;

import com.bubbaboogs.modad.client.entities.CinquedeaProjectileRenderer;
import com.bubbaboogs.modad.client.entities.GrapplingProjectileRenderer;
import com.bubbaboogs.modad.client.entities.MenderEntityModel;
import com.bubbaboogs.modad.client.entities.MenderEntityRenderer;
import com.bubbaboogs.modad.client.gui.HarvesterScreen;
import com.bubbaboogs.modad.entities.ModEntities;
import com.bubbaboogs.modad.gui.ModGUI;
import com.bubbaboogs.modad.weapons.ClawItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ModOfDoomAndDespairClient implements ClientModInitializer {

	public static final ModelLayerLocation MODEL_MENDER_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "mender_entity"), "main");
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.putBlock(ModBlocks.PLATFORM, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(ModBlocks.SHEET_METAL_GRATE, ChunkSectionLayer.CUTOUT);
		EntityRendererRegistry.register(ModEntities.CINQUEDEA, CinquedeaProjectileRenderer::new);
		EntityRendererRegistry.register(ModEntities.GRAPPLING_PROJECTILE, GrapplingProjectileRenderer::new);
		EntityRendererRegistry.register(ModEntities.MENDER, MenderEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(MODEL_MENDER_LAYER, MenderEntityModel::getModelData);

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player == null || client.level == null) return;

			if (client.options.keyAttack.isDown()) {
				if (client.player.getMainHandItem().getItem() instanceof ClawItem) {
					Entity target = getEntityInCrosshair(client);
					if (target != null && client.player.getAttackStrengthScale(0) >= 1.0f) {
						client.gameMode.attack(client.player, target);
						client.player.swing(InteractionHand.MAIN_HAND);
					}
                    /*else if (client.player.getAttackCooldownProgress(0) >= 1.0f) {
                        client.player.swingHand(Hand.MAIN_HAND);
                    }*/
				}
			}
		});

        MenuScreens.register(ModGUI.HARVESTER_MENU, HarvesterScreen::new);
	}

	private Entity getEntityInCrosshair(Minecraft client) {
		if (client.hitResult != null && client.hitResult.getType() == HitResult.Type.ENTITY) {
			return ((EntityHitResult) client.hitResult).getEntity();
		}
		return null;
	}
}