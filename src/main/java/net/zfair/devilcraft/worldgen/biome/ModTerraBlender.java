package net.zfair.devilcraft.worldgen.biome;

import net.minecraft.resources.ResourceLocation;
import net.zfair.devilcraft.devilcraft;
import terrablender.api.Regions;

public class ModTerraBlender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, "overworld"), 5));
    }
}
