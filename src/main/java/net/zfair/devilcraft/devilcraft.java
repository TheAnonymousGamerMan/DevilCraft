package net.zfair.devilcraft;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.zfair.devilcraft.block.ModBlocks;
import net.zfair.devilcraft.block.entity.ModBlockEntities;
import net.zfair.devilcraft.entity.ModEntities;
import net.zfair.devilcraft.entity.ModMobCategories;
import net.zfair.devilcraft.entity.ModMobCategory;
import net.zfair.devilcraft.entity.client.EvilSpiritRenderer;
import net.zfair.devilcraft.entity.custom.EvilSpiritEntity;
import net.zfair.devilcraft.item.ModCreativeModTabs;
import net.zfair.devilcraft.item.ModItems;
import net.zfair.devilcraft.loot.ModLootModifiers;
import net.zfair.devilcraft.network.ModMessages;
import net.zfair.devilcraft.recipe.ModRecipes;
import net.zfair.devilcraft.screen.AltarBlockScreen;
import net.zfair.devilcraft.screen.ModMenuTypes;
import net.zfair.devilcraft.sound.ModSounds;
import net.zfair.devilcraft.util.ModWoodTypes;
import net.zfair.devilcraft.worldgen.biome.DevilCraftRegion;
import net.zfair.devilcraft.worldgen.biome.ModTerraBlender;
import net.zfair.devilcraft.worldgen.biome.surface.ModSurfaceRules;
import net.zfair.devilcraft.worldgen.tree.custom.ModTrunkPlacerTypes;
import org.slf4j.Logger;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(devilcraft.MOD_ID)
public class devilcraft
{

    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "devilcraft";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MOD_ID);

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

        ModTrunkPlacerTypes.register(modEventBus);

        ModTerraBlender.registerBiomes();

        ModMessages.register();

        Regions.register(new DevilCraftRegion());

        BIOME_MODIFIER_SERIALIZERS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.EVIL_ROSE.getId(), ModBlocks.POTTED_EVIL_ROSE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.EVIL_SAPLING.getId(), ModBlocks.POTTED_EVIL_SAPLING);

            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeRules());
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

            EntityRenderers.register(ModEntities.FIREBALL_PROJECTILE.get(), ThrownItemRenderer::new);

            ModMobCategories.init();

            MenuScreens.register(ModMenuTypes.ALTAR_MENU.get(), AltarBlockScreen::new);
            LOGGER.info("DEVIL CRAFTING");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
