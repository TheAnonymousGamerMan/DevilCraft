package net.zfair.devilcraft.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zfair.devilcraft.block.custom.*;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.item.ModItems;
import net.zfair.devilcraft.util.ModWoodTypes;
import net.zfair.devilcraft.worldgen.tree.EvilTreeGrower;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, devilcraft.MOD_ID);

    public static final RegistryObject<Block> CRYING_DEVIL_BLOCK = registerBlock("crying_devil_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.ANCIENT_DEBRIS)));

    public static final RegistryObject<Block> DEVIL_PUMPKIN = registerBlock("devil_pumpkin",
            () -> new Oriental(BlockBehaviour.Properties.copy(Blocks.PUMPKIN).sound(SoundType.NETHERRACK).noParticlesOnBreak()));

    public static final RegistryObject<Block> DEVIL_O_LANTERN = registerBlock("devil_o_lantern",
            () -> new Oriental(BlockBehaviour.Properties.copy(Blocks.JACK_O_LANTERN).sound(SoundType.NETHERRACK).noParticlesOnBreak()));

    public static final RegistryObject<Block> EVIL_ORE = registerBlock("evil_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.NETHER_GOLD_ORE).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3, 6)));

    public static final RegistryObject<Block> DEEPSLATE_EVIL_ORE = registerBlock("deepslate_evil_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.NETHER_GOLD_ORE).strength(2.5f).requiresCorrectToolForDrops(), UniformInt.of(3, 6)));

    public static final RegistryObject<Block> EVIL_BLOCK = registerBlock("evil_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.NETHER_GOLD_ORE)));

    public static final RegistryObject<Block> VERY_EVIL_BLOCK = registerBlock("very_evil_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.NETHER_GOLD_ORE)));

    public static final RegistryObject<Block> PURE_EVIL_BLOCK = registerBlock("pure_evil_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.NETHER_GOLD_ORE)));

    public static final RegistryObject<Block> DEVIL_PORTAL_FRAME = registerBlock("devil_portal_frame",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(-1.0f).noLootTable()));

    public static final RegistryObject<Block> EVIL_LOG = registerBlock("evil_log",
            () -> new ModEvilFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3f).sound(SoundType.WOOD).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> EVIL_WOOD = registerBlock("evil_wood",
            () -> new ModEvilFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3f).sound(SoundType.WOOD).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> STRIPPED_EVIL_LOG = registerBlock("stripped_evil_log",
            () -> new ModEvilFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3f).sound(SoundType.WOOD).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> STRIPPED_EVIL_WOOD = registerBlock("stripped_evil_wood",
            () -> new ModEvilFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3f).sound(SoundType.WOOD).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> EVIL_LEAVES = registerBlock("evil_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).sound(SoundType.AZALEA_LEAVES)));

    public static final RegistryObject<Block> EVIL_PLANK = registerBlock("evil_plank",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2f).sound(SoundType.WOOD).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> EVIL_STAIRS = registerBlock("evil_stairs",
            () -> new StairBlock(() -> ModBlocks.EVIL_PLANK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2f).sound(SoundType.WOOD).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> EVIL_FENCE = registerBlock("evil_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2f).sound(SoundType.WOOD).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> EVIL_SLAB = registerBlock("evil_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2f).sound(SoundType.WOOD).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> EVIL_FENCE_GATE = registerBlock("evil_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2f).sound(SoundType.WOOD).requiresCorrectToolForDrops(), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));

    public static final RegistryObject<Block> EVIL_DOOR = registerBlock("evil_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2f).sound(SoundType.WOOD).requiresCorrectToolForDrops().noOcclusion(), BlockSetType.OAK));

    public static final RegistryObject<Block> EVIL_TRAPDOOR = registerBlock("evil_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2f).sound(SoundType.WOOD).requiresCorrectToolForDrops().noOcclusion(), BlockSetType.OAK));

    public static final RegistryObject<Block> EVIL_BUTTON = registerBlock("evil_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).strength(2f).sound(SoundType.WOOD).requiresCorrectToolForDrops(), BlockSetType.OAK, 10, true));

    public static final RegistryObject<Block> EVIL_PRESSURE_PLATE = registerBlock("evil_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE).strength(2f).sound(SoundType.WOOD).requiresCorrectToolForDrops(), BlockSetType.OAK));

    public static final RegistryObject<Block> DEVIL_PORTAL_BLOCK = registerBlock("devil_portal_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHER_PORTAL).strength(-1.0f).sound(SoundType.GLASS).noLootTable()));

    public static final RegistryObject<Block> EVIL_DIRT = registerBlock("evil_dirt",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT).strength(2f).sound(SoundType.GRAVEL)));

    public static final RegistryObject<Block> EVIL_GRASS = registerBlock("evil_grass",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).strength(2f).sound(SoundType.GRAVEL)));

    public static final RegistryObject<Block> EVIL_CROP = BLOCKS.register("evil_crop",
            () -> new EvilCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> EVIL_ROSE = registerBlock("evil_rose",
            () -> new FlowerBlock(() -> MobEffects.BAD_OMEN, 5, BlockBehaviour.Properties.copy(Blocks.POPPY).noOcclusion().noCollission()));

    public static final RegistryObject<Block> POTTED_EVIL_ROSE = BLOCKS.register("potted_evil_rose",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.EVIL_ROSE,
            BlockBehaviour.Properties.copy(Blocks.POPPY).noOcclusion()));

    public static final RegistryObject<Block> ALTAR_BLOCK = registerBlock("altar_block",
            () -> new AltarBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).noOcclusion()));

    public static final RegistryObject<Block> EVIL_SIGN = BLOCKS.register("evil_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), ModWoodTypes.EVIL));

    public static final RegistryObject<Block> EVIL_WALL_SIGN = BLOCKS.register("evil_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), ModWoodTypes.EVIL));


    public static final RegistryObject<Block> EVIL_HANGING_SIGN = BLOCKS.register("evil_hanging_sign",
            () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), ModWoodTypes.EVIL));

    public static final RegistryObject<Block> EVIL_WALL_HANGING_SIGN = BLOCKS.register("evil_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), ModWoodTypes.EVIL));

    public static final RegistryObject<Block> EVIL_SAPLING = registerBlock("evil_sapling",
            () -> new SaplingBlock(new EvilTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).sound(SoundType.GRASS)));

    public static final RegistryObject<Block> POTTED_EVIL_SAPLING = BLOCKS.register("potted_evil_sapling",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.EVIL_SAPLING,
                    BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).noOcclusion()));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register (IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
