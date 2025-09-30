package com.bubbaboogs.modad.entities;

import com.bubbaboogs.modad.ModOfDoomAndDespair;
import com.bubbaboogs.modad.entities.living.MenderEntity;
import com.bubbaboogs.modad.entities.projectile.CinquedeaEntity;
import com.bubbaboogs.modad.entities.projectile.GildedDaggerEntity;
import com.bubbaboogs.modad.entities.projectile.GrapplingProjectile;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final RegistryKey<EntityType<?>> CINQUEDEA_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(ModOfDoomAndDespair.MOD_ID, "cinqueadea_entity"));
    public static final EntityType<CinquedeaEntity> CINQUEDEA = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(ModOfDoomAndDespair.MOD_ID, "cinquedea"),
            EntityType.Builder.<CinquedeaEntity>create(CinquedeaEntity::new, SpawnGroup.MISC).dimensions(0.5f, 0.5f).build(CINQUEDEA_KEY));
    public static final RegistryKey<EntityType<?>> GILDED_DAGGER_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(ModOfDoomAndDespair.MOD_ID, "gilded_dagger"));
    public static final EntityType<GildedDaggerEntity> GILDED_DAGGER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(ModOfDoomAndDespair.MOD_ID, "gilded_dagger"),
            EntityType.Builder.<GildedDaggerEntity>create(GildedDaggerEntity::new, SpawnGroup.MISC).dimensions(0.5f, 0.5f).build(GILDED_DAGGER_KEY));

    public static final RegistryKey<EntityType<?>> GRAPPLING_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(ModOfDoomAndDespair.MOD_ID, "grappling_projectile_entity"));
    public static final EntityType<GrapplingProjectile> GRAPPLING_PROJECTILE =
            Registry.register(Registries.ENTITY_TYPE, Identifier.of(ModOfDoomAndDespair.MOD_ID, "grappling_projectile"),
                    FabricEntityTypeBuilder.<GrapplingProjectile>create(SpawnGroup.MISC, GrapplingProjectile::new)
                            .dimensions(EntityDimensions.fixed(0.25F,0.25F))
                            .trackRangeBlocks(64).trackedUpdateRate(10).build(GRAPPLING_KEY));

    public static final RegistryKey<EntityType<?>> MENDER_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(ModOfDoomAndDespair.MOD_ID, "mender_entity"));
    public static final EntityType<MenderEntity> MENDER = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ModOfDoomAndDespair.MOD_ID, "mender_entity"),
            EntityType.Builder.create(
            MenderEntity::new, SpawnGroup.MISC).dimensions(0.5f, 0.5f).build(MENDER_KEY)
    );


    public static void registerModEntities(){
        ModOfDoomAndDespair.LOGGER.info("Registering Mod Entities for " + ModOfDoomAndDespair.MOD_ID);
        FabricDefaultAttributeRegistry.register(MENDER, MenderEntity.createMobAttributes());
    }
}
