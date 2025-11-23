package com.bubbaboogs.modad.components;

import com.bubbaboogs.modad.ModOfDoomAndDespair;
import net.minecraft.resources.ResourceLocation;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class ModCardinalComponents implements EntityComponentInitializer {
    public static final ComponentKey<LifeCrystalsUsedComponent> LIFE_CRYSTALS_USED = ComponentRegistry.getOrCreate(
            ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "life_crystals_used"), LifeCrystalsUsedComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry){
        registry.registerForPlayers(LIFE_CRYSTALS_USED, player -> new LifeCrystalsUsedComponent(), RespawnCopyStrategy.ALWAYS_COPY);
    }
}
