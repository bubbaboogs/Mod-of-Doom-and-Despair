package com.bubbaboogs.modad.client.entities;

import com.bubbaboogs.modad.ModOfDoomAndDespair;
import com.bubbaboogs.modad.ModOfDoomAndDespairClient;
import com.bubbaboogs.modad.entities.living.MenderEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class MenderEntityRenderer extends MobEntityRenderer<MenderEntity, MenderEntityRenderState, MenderEntityModel> {
    public MenderEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new MenderEntityModel(context.getPart(ModOfDoomAndDespairClient.MODEL_MENDER_LAYER)), 0.5f);
    }

    @Override
    public MenderEntityRenderState createRenderState() {
        return new MenderEntityRenderState();
    }

    @Override
    public Identifier getTexture(MenderEntityRenderState state) {
        return Identifier.of(ModOfDoomAndDespair.MOD_ID, "textures/entity/mender_entity.png");
    }
}
