package net.zfair.devilcraft.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.zfair.devilcraft.devilcraft;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class DevilCraftRegion extends Region {
    public DevilCraftRegion() {
        super(ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, "overworld"), RegionType.OVERWORLD, 2);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        mapper.accept(new Pair<>(
                Climate.parameters(
                        Climate.Parameter.span(0.2F, 0.8F), // Temperature
                        Climate.Parameter.span(0.0F, 1.0F), // Humidity
                        Climate.Parameter.span(-0.5F, 0.5F), // Continentalness
                        Climate.Parameter.span(-0.5F, 0.5F), // Erosion
                        Climate.Parameter.span(0.0F, 1.0F), // Depth
                        Climate.Parameter.span(-0.5F, 0.5F), // Weirdness
                        0.0F // Offset
                ),
                ModBiomes.EVIL_BIOME
        ));
    }
}