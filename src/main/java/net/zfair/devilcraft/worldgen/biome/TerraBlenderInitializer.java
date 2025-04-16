package net.zfair.devilcraft.worldgen.biome;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.zfair.devilcraft.devilcraft;
import terrablender.api.Regions;

@Mod.EventBusSubscriber(modid = devilcraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TerraBlenderInitializer {

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> Regions.register(new DevilCraftRegion()));
    }
}