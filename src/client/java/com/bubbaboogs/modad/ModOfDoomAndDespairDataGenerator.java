package com.bubbaboogs.modad;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ModOfDoomAndDespairDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModOfDoomAndDespairModelProvider::new);
		pack.addProvider(ModOfDoomAndDespairBlockTagProvider::new);
		pack.addProvider(ModOfDoomAndDespairBlockLootTableProvider::new);
	}
}
