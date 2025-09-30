package com.bubbaboogs.modad.client.entities;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;

public class MenderEntityModel extends EntityModel<MenderEntityRenderState> {
	private final ModelPart bone;
	public MenderEntityModel(ModelPart root) {
        super(root);
        this.bone = root.getChild("bone");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(0, 8).cuboid(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 16, 16);
	}

	@Override
	public void setAngles(MenderEntityRenderState state) {
	}

}