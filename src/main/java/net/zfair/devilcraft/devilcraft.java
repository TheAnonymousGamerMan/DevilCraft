package net.zfair.devilcraft;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.zfair.devilcraft.block.ModBlocks;
import net.zfair.devilcraft.block.entity.ModBlockEntities;
import net.zfair.devilcraft.entity.ModEntities;
import net.zfair.devilcraft.entity.client.EvilSpiritRenderer;
import net.zfair.devilcraft.item.ModCreativeModTabs;
import net.zfair.devilcraft.item.ModItems;
import net.zfair.devilcraft.loot.ModLootModifiers;
import net.zfair.devilcraft.recipe.ModRecipes;
import net.zfair.devilcraft.screen.AltarBlockScreen;
import net.zfair.devilcraft.screen.ModMenuTypes;
import net.zfair.devilcraft.sound.ModSounds;
import net.zfair.devilcraft.util.ModWoodTypes;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(devilcraft.MOD_ID)
public class devilcraft
{

    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "devilcraft";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public devilcraft(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        ModLootModifiers.register(modEventBus);

        ModSounds.register(modEventBus);

        ModEntities.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);


        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.EVIL_ROSE.getId(), ModBlocks.POTTED_EVIL_ROSE);
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            Sheets.addWoodType(ModWoodTypes.EVIL);

            EntityRenderers.register(ModEntities.EVIL_SPIRIT.get(), EvilSpiritRenderer::new);

            MenuScreens.register(ModMenuTypes.ALTAR_MENU.get(), AltarBlockScreen::new);
            LOGGER.info("DEVIL CRAFTING");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

        }
    }
}
