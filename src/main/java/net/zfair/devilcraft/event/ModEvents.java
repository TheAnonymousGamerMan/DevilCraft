package net.zfair.devilcraft.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zfair.devilcraft.block.ModBlocks;
import net.zfair.devilcraft.block.custom.IndestructibleMakerBlock;
import net.zfair.devilcraft.block.entity.IndestructibleMakerBlockEntity;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.item.ModItems;

import java.util.List;

@Mod.EventBusSubscriber(modid = devilcraft.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.CLERIC) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.EVIL_GEM.get(), 12),
                    new ItemStack(Items.EMERALD, 6),
                    10, 8, 0.02f));


        }
    }


    @SubscribeEvent
    public static void addCustomWanderingTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 12),
                new ItemStack(ModItems.EVIL_INGOT.get(), 1),
                2, 12, 0.2f));

    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        Level level = (Level) event.getLevel();
        BlockPos brokenPos = event.getPos();

        if (level.getBlockState(brokenPos).getBlock() instanceof net.zfair.devilcraft.block.custom.IndestructibleMakerBlock) {
                return;
        }

        for (BlockPos pos : BlockPos.betweenClosed(brokenPos.offset(-100, -100, -100), brokenPos.offset(100, 100, 100))) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof IndestructibleMakerBlockEntity indestructibleMakerBlockEntity) {
                if (brokenPos.closerThan(pos, indestructibleMakerBlockEntity.getRadius())) {
                    if (!player.isCreative()) {
                        player.sendSystemMessage(Component.literal("The Evil Force Emanating Prevents You From Breaking This Block...")
                                .setStyle(Style.EMPTY.withColor(TextColor.parseColor("#8a0000"))));
                        event.setCanceled(true);

                        return ;
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        if (!(event.getEntity() instanceof Player player) || event.getLevel().isClientSide()) {
            return;
        }

        Level level = (Level) event.getLevel();
        BlockPos placedPos = event.getPos();

        if (level.getBlockState(placedPos).getBlock() instanceof net.zfair.devilcraft.block.custom.IndestructibleMakerBlock) {
            return;
        }

        for (BlockPos pos : BlockPos.betweenClosed(placedPos.offset(-100, -100, -100), placedPos.offset(100, 100, 100))) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof IndestructibleMakerBlockEntity indestructibleMakerBlockEntity) {
                if (placedPos.closerThan(pos, indestructibleMakerBlockEntity.getRadius())) {
                    if (!player.isCreative()) {
                        player.sendSystemMessage(Component.literal("The Evil Force Emanating Prevents You From Placing This Block...")
                                .setStyle(Style.EMPTY.withColor(TextColor.parseColor("#8a0000"))));
                        event.setCanceled(true);


                        return;
                    }
                }
            }
        }
    }
}
