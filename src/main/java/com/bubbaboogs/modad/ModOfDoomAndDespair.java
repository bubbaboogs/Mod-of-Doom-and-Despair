package com.bubbaboogs.modad;

import com.bubbaboogs.modad.components.ModComponents;
import com.bubbaboogs.modad.entities.ModEntities;
import com.bubbaboogs.modad.networking.ModPayloads;
import com.bubbaboogs.modad.weapons.EvilSmasherEventHandler;
import com.bubbaboogs.modad.weapons.EvilSmasherItem;
import com.bubbaboogs.modad.worldgen.biomemodifiers.BiomeModifier;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModOfDoomAndDespair implements ModInitializer {
	public static final String MOD_ID = "modad";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.initialize();
		//ModBlockFamilies.initialize();
		ModItems.initialize();
		ModAttributes.initialize();
		EvilSmasherEventHandler.register();
		ModComponents.initialize();
		ModEntities.registerModEntities();
		ModPayloads.initialize();
		ModTimer.register();
		ModEffects.initialize();
		ModEvents.initialize();
		BiomeModifier.init();
	}
}