package com.bubbaboogs.modad.worldgen.biomemodifiers;

import com.bubbaboogs.modad.ModOfDoomAndDespair;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import java.util.Arrays;

public class BiomeModifier {

    public static void init() {
        addToBiome("jadeite_stone_patch", GenerationStep.Decoration.UNDERGROUND_ORES);
        addToBiome("gabbro_stone_patch", GenerationStep.Decoration.UNDERGROUND_ORES);
        addToBiome("gypsum_stone_patch", GenerationStep.Decoration.UNDERGROUND_ORES);
    }

    private static void addToBiome(String featureName, GenerationStep.Decoration step) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, featureName);

        BiomeModifications.create(id)
                .add(
                        ModificationPhase.ADDITIONS,
                        context -> context.hasTag(TagKey.create(Registries.BIOME,
                                ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "has_structure/" + featureName))),
                        context -> context.getGenerationSettings().addFeature(step, ResourceKey.create(Registries.PLACED_FEATURE, id))
                );
    }

    private static void removeFromBiome(ResourceLocation feature, String biomeTagName, GenerationStep.Decoration step) {
        ResourceLocation removeId = ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "remove_" + feature.getPath());

        BiomeModifications.create(removeId)
                .add(
                        ModificationPhase.REMOVALS,
                        context -> context.hasTag(TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, biomeTagName))),
                        context -> context.getGenerationSettings().removeFeature(step, ResourceKey.create(Registries.PLACED_FEATURE, feature))
                );
    }

    private static boolean nameMatch(String biomeName, String... targetMatch) {
        return Arrays.stream(targetMatch).anyMatch(biomeName::contains);
    }

    private static boolean nameExactMatch(String biomeName, String... targetMatch) {
        return Arrays.asList(targetMatch).contains(biomeName);
    }
}
