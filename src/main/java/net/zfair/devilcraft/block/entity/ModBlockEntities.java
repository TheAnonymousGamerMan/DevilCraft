package net.zfair.devilcraft.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zfair.devilcraft.block.ModBlocks;
import net.zfair.devilcraft.devilcraft;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, devilcraft.MOD_ID);

    public static final RegistryObject<BlockEntityType<AltarBlockEntity>> ALTAR_BLOCK_BE =
            BLOCK_ENTITIES.register("altar_block_be", () ->
                    BlockEntityType.Builder.of(AltarBlockEntity::new,
                            ModBlocks.ALTAR_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
