package net.zfair.devilcraft.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.zfair.devilcraft.entity.animations.ModAnimationDefinitions;
import net.zfair.devilcraft.entity.custom.EvilSpiritEntity;

public class EvilSpiritModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart evil_spirit;
    private final ModelPart arm1;
    private final ModelPart arm2;
    private final ModelPart body;
    private final ModelPart head;

    public EvilSpiritModel(ModelPart root) {
        this.evil_spirit = root.getChild("evil_spirit");
        this.arm1 = this.evil_spirit.getChild("arm1");
        this.arm2 = this.evil_spirit.getChild("arm2");
        this.body = this.evil_spirit.getChild("body");
        this.head = this.evil_spirit.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition evilSpiritGroup = partdefinition.addOrReplaceChild("evil_spirit", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition arm1 = evilSpiritGroup.addOrReplaceChild("arm1", CubeListBuilder.create().texOffs(20, 11).addBox(-0.9437F, 0.252F, -2.9661F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, -2.0F));

        PartDefinition arm2 = evilSpiritGroup.addOrReplaceChild("arm2", CubeListBuilder.create().texOffs(20, 11).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 3.0F));

        PartDefinition body = evilSpiritGroup.addOrReplaceChild("body", CubeListBuilder.create().texOffs(24, 26).addBox(1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 27).addBox(1.0F, 0.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(4, 27).addBox(1.0F, 1.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 27).addBox(1.0F, 0.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(12, 27).addBox(-2.0F, 0.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 27).addBox(-2.0F, 1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 0).addBox(-2.0F, 1.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 2).addBox(-2.0F, 0.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 4).addBox(-2.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-2.0F, -12.0F, -3.0F, 4.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, 0.0F));

        PartDefinition head = evilSpiritGroup.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 18).addBox(-3.0F, -5.0F, -2.0F, 6.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 6).addBox(0.0F, -7.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(28, 8).addBox(0.0F, -8.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 22).addBox(0.0F, -7.0F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 26).addBox(0.0F, -8.0F, -2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 22).addBox(0.0F, -7.0F, 2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

        this.animateWalk(ModAnimationDefinitions.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(((EvilSpiritEntity)entity).idleAnimationState, ModAnimationDefinitions.IDLE, ageInTicks, 1f);
        this.animate(((EvilSpiritEntity) entity).attackAnimationState, ModAnimationDefinitions.ATTACK, ageInTicks, 1f);
    }

private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
    pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
    pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

    this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
    this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
}

@Override
public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    evil_spirit.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
}

@Override
public ModelPart root() {
    return evil_spirit;
}
}
