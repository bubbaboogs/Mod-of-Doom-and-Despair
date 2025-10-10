package com.bubbaboogs.modad.worldgen.biomemodifiers;

import com.bubbaboogs.modad.ModOfDoomAndDespair;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import java.util.Arrays;

public class BiomeModifier {

    public static void init() {
        addToBiome("jadeite_stone_patch", GenerationStep.Feature.UNDERGROUND_ORES);
        removeFromBiome(Identifier.of(ModOfDoomAndDespair.MOD_ID, "old_ore_patch"), "no_old_ores", GenerationStep.Feature.UNDERGROUND_ORES);
    }

    private static void addToBiome(String featureName, GenerationStep.Feature step) {
        Identifier id = Identifier.of(ModOfDoomAndDespair.MOD_ID, featureName);

        BiomeModifications.create(id)
                .add(
                        ModificationPhase.ADDITIONS,
                        context -> context.hasTag(TagKey.of(RegistryKeys.BIOME,
                                Identifier.of(ModOfDoomAndDespair.MOD_ID, "has_structure/jadeite_stone_patch"))),
                        context -> context.getGenerationSettings().addFeature(step, RegistryKey.of(RegistryKeys.PLACED_FEATURE, id))
                );
    }

    private static void removeFromBiome(Identifier feature, String biomeTagName, GenerationStep.Feature step) {
        Identifier removeId = Identifier.of(ModOfDoomAndDespair.MOD_ID, "remove_" + feature.getPath());

        BiomeModifications.create(removeId)
                .add(
                        ModificationPhase.REMOVALS,
                        context -> context.hasTag(TagKey.of(RegistryKeys.BIOME, Identifier.of(ModOfDoomAndDespair.MOD_ID, biomeTagName))),
                        context -> context.getGenerationSettings().removeFeature(step, RegistryKey.of(RegistryKeys.PLACED_FEATURE, feature))
                );
    }

    private static boolean nameMatch(String biomeName, String... targetMatch) {
        return Arrays.stream(targetMatch).anyMatch(biomeName::contains);
    }

    private static boolean nameExactMatch(String biomeName, String... targetMatch) {
        return Arrays.asList(targetMatch).contains(biomeName);
    }
}
