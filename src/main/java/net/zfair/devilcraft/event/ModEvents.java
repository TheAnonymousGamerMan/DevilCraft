package net.zfair.devilcraft.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.item.ModItems;

import java.util.List;

@Mod.EventBusSubscriber(modid = devilcraft.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event){
        if (event.getType() == VillagerProfession.CLERIC){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.EVIL_GEM.get(), 12),
                    new ItemStack(Items.EMERALD, 6),
                    10,8,0.02f));


        }
    }



    @SubscribeEvent
    public static void addCustomWanderingTrades(WandererTradesEvent event){
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 12),
                new ItemStack(ModItems.EVIL_INGOT.get(), 1),
                2,12,0.2f));

    }

}
