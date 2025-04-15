package net.zfair.devilcraft.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.entity.ModEntities;
import net.zfair.devilcraft.entity.custom.EvilSpiritEntity;

@Mod.EventBusSubscriber(modid = devilcraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.EVIL_SPIRIT.get(), EvilSpiritEntity.createAttributes().build());
    }
}