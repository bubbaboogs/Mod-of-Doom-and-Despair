package com.bubbaboogs.modad.client.entities;

import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class MenderEntityModel extends net.minecraft.client.model.EntityModel<MenderEntityRenderState> {
	private final ModelPart bone;
	public MenderEntityModel(ModelPart root) {
        super(root);
        this.bone = root.getChild("bone");
	}
	public static LayerDefinition getModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition bone = modelPartData.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 8).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		return LayerDefinition.create(modelData, 16, 16);
	}

	@Override
	public void setupAnim(MenderEntityRenderState state) {
	}

}