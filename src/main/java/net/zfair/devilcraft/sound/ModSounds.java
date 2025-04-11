package net.zfair.devilcraft.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zfair.devilcraft.devilcraft;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, devilcraft.MOD_ID);

    public static final RegistryObject<SoundEvent> TEST = registerSoundEvents("test");

    public static final RegistryObject<SoundEvent> TEST_BLOCK_BREAK = registerSoundEvents("test_block_break");
    public static final RegistryObject<SoundEvent> TEST_BLOCK_STEP = registerSoundEvents("test_block_step");
    public static final RegistryObject<SoundEvent> TEST_BLOCK_FALL = registerSoundEvents("test_block_fall");
    public static final RegistryObject<SoundEvent> TEST_BLOCK_PLACE = registerSoundEvents("test_block_place");
    public static final RegistryObject<SoundEvent> TEST_BLOCK_HIT = registerSoundEvents("test_block_hit");

    public static final ForgeSoundType TEST_SOUNDS = new ForgeSoundType(1f,1f,
            ModSounds.TEST_BLOCK_BREAK, ModSounds.TEST_BLOCK_STEP, ModSounds.TEST_BLOCK_FALL,
            ModSounds.TEST_BLOCK_PLACE,ModSounds.TEST_BLOCK_HIT);

    public static final RegistryObject<SoundEvent> EVIL_SPIRIT_AMBIENT = registerSoundEvents("evil_spirit_ambient");
    public static final RegistryObject<SoundEvent> EVIL_SPIRIT_HURT = registerSoundEvents("evil_spirit_hurt");
    public static final RegistryObject<SoundEvent> EVIL_SPIRIT_DEATH = registerSoundEvents("evil_spirit_death");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(devilcraft.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
