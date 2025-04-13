package net.zfair.devilcraft.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import  net.minecraftforge.fml.common.Mod;
import net.zfair.devilcraft.block.entity.ModBlockEntities;
import net.zfair.devilcraft.block.entity.renderer.AltarBlockEntityRenderer;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.entity.client.EvilSpiritModel;
import net.zfair.devilcraft.entity.client.ModModelLayers;

@Mod.EventBusSubscriber(modid = devilcraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerlayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.EVIL_SPIRIT_LAYER, EvilSpiritModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.ALTAR_BLOCK_BE.get(), AltarBlockEntityRenderer::new);
    }
}
