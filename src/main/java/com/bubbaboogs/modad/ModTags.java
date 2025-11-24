package com.bubbaboogs.modad;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final TagKey<Block> NEEDS_NETHERITE_TOOL = of("needs_netherite_tool");

    private static TagKey<Block> of(String id) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.withDefaultNamespace(id));
    }
}
