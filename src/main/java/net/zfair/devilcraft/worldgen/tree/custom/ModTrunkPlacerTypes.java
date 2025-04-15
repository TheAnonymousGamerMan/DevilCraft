package net.zfair.devilcraft.worldgen.tree.custom;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.zfair.devilcraft.devilcraft;

public class ModTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, devilcraft.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<EvilTrunkPlacer>> EVIL_TRUNK_PLACER =
            TRUNK_PLACER.register("evil_trunk_placer", () -> new TrunkPlacerType<>(EvilTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) {
        TRUNK_PLACER.register(eventBus);
    }
}
