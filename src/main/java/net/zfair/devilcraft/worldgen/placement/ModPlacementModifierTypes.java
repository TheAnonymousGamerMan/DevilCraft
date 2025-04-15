package net.zfair.devilcraft.worldgen.placement;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.worldgen.WeightedCountPlacement;

public class ModPlacementModifierTypes {
    public static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIERS =
            DeferredRegister.create(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE.key(), devilcraft.MOD_ID);

    public static final RegistryObject<PlacementModifierType<WeightedCountPlacement>> WEIGHTED_COUNT =
            PLACEMENT_MODIFIERS.register("weighted_count", () -> new PlacementModifierType<>() {
                @Override
                public Codec<WeightedCountPlacement> codec() {
                    return WeightedCountPlacement.CODEC;
                }
            });
}