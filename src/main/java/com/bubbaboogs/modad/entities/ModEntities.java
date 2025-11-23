package com.bubbaboogs.modad.entities;

import com.bubbaboogs.modad.ModOfDoomAndDespair;
import com.bubbaboogs.modad.entities.living.MenderEntity;
import com.bubbaboogs.modad.entities.projectile.CinquedeaEntity;
import com.bubbaboogs.modad.entities.projectile.GildedDaggerEntity;
import com.bubbaboogs.modad.entities.projectile.GrapplingProjectile;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    public static final ResourceKey<EntityType<?>> CINQUEDEA_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "cinqueadea_entity"));
    public static final EntityType<CinquedeaEntity> CINQUEDEA = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "cinquedea"),
            EntityType.Builder.<CinquedeaEntity>of(CinquedeaEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build(CINQUEDEA_KEY));
    public static final ResourceKey<EntityType<?>> GILDED_DAGGER_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "gilded_dagger"));
    public static final EntityType<GildedDaggerEntity> GILDED_DAGGER = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "gilded_dagger"),
            EntityType.Builder.<GildedDaggerEntity>of(GildedDaggerEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build(GILDED_DAGGER_KEY));

    public static final ResourceKey<EntityType<?>> GRAPPLING_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "grappling_projectile_entity"));
    public static final EntityType<GrapplingProjectile> GRAPPLING_PROJECTILE =
            Registry.register(BuiltInRegistries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "grappling_projectile"),
                    FabricEntityTypeBuilder.<GrapplingProjectile>create(MobCategory.MISC, GrapplingProjectile::new)
                            .dimensions(EntityDimensions.fixed(0.25F,0.25F))
                            .trackRangeBlocks(64).trackedUpdateRate(10).build(GRAPPLING_KEY));

    public static final ResourceKey<EntityType<?>> MENDER_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "mender_entity"));
    public static final EntityType<MenderEntity> MENDER = Registry.register(BuiltInRegistries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "mender_entity"),
            EntityType.Builder.of(
            MenderEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build(MENDER_KEY)
    );


    public static void registerModEntities(){
        ModOfDoomAndDespair.LOGGER.info("Registering Mod Entities for " + ModOfDoomAndDespair.MOD_ID);
        FabricDefaultAttributeRegistry.register(MENDER, MenderEntity.createMobAttributes());
    }
}
