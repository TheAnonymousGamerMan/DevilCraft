package net.zfair.devilcraft.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.entity.custom.EvilSpiritEntity;

public class EvilSpiritRenderer extends MobRenderer<EvilSpiritEntity, EvilSpiritModel<EvilSpiritEntity>> {
    public EvilSpiritRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new EvilSpiritModel<>(pContext.bakeLayer(ModModelLayers.EVIL_SPIRIT_LAYER)),0.2f);
    }

    @Override
    public ResourceLocation getTextureLocation(EvilSpiritEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, "textures/entity/evil_spirit.png");
    }

    @Override
    public void render(EvilSpiritEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
