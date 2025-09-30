package com.bubbaboogs.modad.attributes;

import com.bubbaboogs.modad.ModAttributes;
import net.minecraft.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Regeneration {
    private static final Map<UUID, Float> regenProgress = new HashMap<>();

    public static void tick(LivingEntity entity) {
        if (!entity.isPlayer() || entity.isDead()) return;

        double regenRate = entity.getAttributeValue(ModAttributes.REGENERATION);
        if (regenRate <= 0) return;

        UUID id = entity.getUuid();
        float progress = regenProgress.getOrDefault(id, 0f);

        progress += regenRate;

        if (progress >= 120.0f) {
            entity.heal(1.0F);
            progress -= 120.0f;
        }

        regenProgress.put(id, progress);
    }

    public static void clear(LivingEntity entity) {
        regenProgress.remove(entity.getUuid());
    }
}
