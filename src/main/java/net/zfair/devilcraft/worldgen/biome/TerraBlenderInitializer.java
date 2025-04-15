package net.zfair.devilcraft.worldgen.biome;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.worldgen.DevilCraftRegion;
import terrablender.api.Regions;
import terrablender.core.TerraBlender;

@Mod.EventBusSubscriber(modid = devilcraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TerraBlenderInitializer extends TerraBlender {

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        Regions.register(new DevilCraftRegion());
    }
}