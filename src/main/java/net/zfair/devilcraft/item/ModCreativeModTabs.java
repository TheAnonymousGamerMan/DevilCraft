package net.zfair.devilcraft.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.zfair.devilcraft.block.ModBlocks;
import net.zfair.devilcraft.devilcraft;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
DeferredRegister.create(Registries.CREATIVE_MODE_TAB, devilcraft.MOD_ID);

    public static final RegistryObject<CreativeModeTab> DEVIL_TAB = CREATIVE_MODE_TABS.register("devil_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.EYE.get()))
                    .title(Component.translatable("creativetab.devil_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.EYE.get());
                        pOutput.accept(ModItems.DEVIL_SPAWN_EGG.get());
                        pOutput.accept(ModItems.UN_EYE.get());
                        pOutput.accept(ModItems.EVIL_GEM.get());
                        pOutput.accept(ModItems.EVIL_INGOT.get());
                        pOutput.accept(ModItems.LUST.get());
                        pOutput.accept(ModItems.GLUTTONY.get());
                        pOutput.accept(ModItems.GREED.get());
                        pOutput.accept(ModItems.SLOTH.get());
                        pOutput.accept(ModItems.WRATH.get());
                        pOutput.accept(ModItems.ENVY.get());
                        pOutput.accept(ModItems.PRIDE.get());
                        pOutput.accept(ModItems.PURE_EVIL.get());
                        pOutput.accept(ModItems.EVIL_DETECTOR.get());
                        pOutput.accept(ModItems.SATAN_PITCHFORK.get());
                        pOutput.accept(ModItems.DEVIL_WHIP.get());
                        pOutput.accept(ModItems.DEVIL_MEAT.get());
                        pOutput.accept(ModItems.DEVIL_HORNS.get());
                        pOutput.accept(ModItems.TEST.get());

                        pOutput.accept(ModItems.EVIL_SWORD.get());
                        pOutput.accept(ModItems.EVIL_PICKAXE.get());
                        pOutput.accept(ModItems.EVIL_AXE.get());
                        pOutput.accept(ModItems.EVIL_SHOVEL.get());
                        pOutput.accept(ModItems.EVIL_HOE.get());

                        pOutput.accept(ModItems.EVIL_HELMET.get());
                        pOutput.accept(ModItems.EVIL_CHESTPLATE.get());
                        pOutput.accept(ModItems.EVIL_LEGGINGS.get());
                        pOutput.accept(ModItems.EVIL_BOOTS.get());

                        pOutput.accept(ModItems.EVIL_SPIRIT_SPAWN_EGG.get());

                        pOutput.accept(ModItems.FIREBALL_ITEM.get());

                        pOutput.accept(ModBlocks.ALTAR_BLOCK.get());

                        pOutput.accept(ModBlocks.CRYING_DEVIL_BLOCK.get());
                        pOutput.accept(ModBlocks.DEVIL_PUMPKIN.get());
                        pOutput.accept(ModBlocks.DEVIL_O_LANTERN.get());
                        pOutput.accept(ModBlocks.EVIL_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_EVIL_ORE.get());
                        pOutput.accept(ModBlocks.EVIL_BLOCK.get());
                        pOutput.accept(ModBlocks.PURE_EVIL_BLOCK.get());
                        pOutput.accept(ModBlocks.VERY_EVIL_BLOCK.get());
                        pOutput.accept(ModBlocks.DEVIL_PORTAL_FRAME.get());

                        pOutput.accept(ModBlocks.EVIL_RACK.get());
                        pOutput.accept(ModBlocks.MOD_PORTAL.get());
                        pOutput.accept(ModBlocks.INDESTRUCTIBLE_MAKER_BLOCK.get());

                        pOutput.accept(ModBlocks.EVIL_LOG.get());
                        pOutput.accept(ModBlocks.EVIL_WOOD.get());
                        pOutput.accept(ModBlocks.STRIPPED_EVIL_LOG.get());
                        pOutput.accept(ModBlocks.STRIPPED_EVIL_WOOD.get());

                        pOutput.accept(ModBlocks.EVIL_PLANK.get());
                        pOutput.accept(ModBlocks.EVIL_LEAVES.get());

                        pOutput.accept(ModBlocks.EVIL_SAPLING.get());

                        pOutput.accept(ModBlocks.EVIL_GRASS.get());
                        pOutput.accept(ModBlocks.EVIL_DIRT.get());

                        pOutput.accept(ModBlocks.EVIL_BUTTON.get());
                        pOutput.accept(ModBlocks.EVIL_FENCE_GATE.get());
                        pOutput.accept(ModBlocks.EVIL_DOOR.get());
                        pOutput.accept(ModBlocks.EVIL_SLAB.get());
                        pOutput.accept(ModBlocks.EVIL_STAIRS.get());
                        pOutput.accept(ModBlocks.EVIL_TRAPDOOR.get());
                        pOutput.accept(ModBlocks.EVIL_PRESSURE_PLATE.get());
                        pOutput.accept(ModBlocks.EVIL_FENCE.get());
                        pOutput.accept(ModBlocks.EVIL_TRAPDOOR.get());

                        pOutput.accept(ModBlocks.EVIL_SIGN.get());
                        pOutput.accept(ModBlocks.EVIL_HANGING_SIGN.get());

                        pOutput.accept(ModItems.EVIL_SEEDS.get());

                        pOutput.accept(ModBlocks.EVIL_ROSE.get());

                    })

                    .build());

    public static void register(IEventBus eventbus) {
        CREATIVE_MODE_TABS.register(eventbus);
    }
}
