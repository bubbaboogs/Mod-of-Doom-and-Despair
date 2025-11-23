package com.bubbaboogs.modad.client.entities;

import com.bubbaboogs.modad.ModOfDoomAndDespair;
import com.bubbaboogs.modad.ModOfDoomAndDespairClient;
import com.bubbaboogs.modad.entities.living.MenderEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class MenderEntityRenderer extends MobRenderer<MenderEntity, MenderEntityRenderState, MenderEntityModel> {
    public MenderEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new MenderEntityModel(context.bakeLayer(ModOfDoomAndDespairClient.MODEL_MENDER_LAYER)), 0.5f);
    }

    @Override
    public @NotNull MenderEntityRenderState createRenderState() {
        return new MenderEntityRenderState();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(MenderEntityRenderState state) {
        return ResourceLocation.fromNamespaceAndPath(ModOfDoomAndDespair.MOD_ID, "textures/entity/mender_entity.png");
    }
}
