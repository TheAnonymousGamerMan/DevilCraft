package net.zfair.devilcraft.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.entity.custom.EvilSpiritEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, devilcraft.MOD_ID);

    public static final RegistryObject<EntityType<EvilSpiritEntity>> EVIL_SPIRIT =
            ENTITY_TYPES.register("evil_spirit", () -> EntityType.Builder.of(EvilSpiritEntity::new, MobCategory.MONSTER)
                    .sized(1f,2f).build("evil_spirit"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
