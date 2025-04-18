package net.zfair.devilcraft.client;

import net.minecraft.core.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zfair.devilcraft.network.ModMessages;
import net.zfair.devilcraft.network.packet.RadiusInputS2CPacket;

@Mod.EventBusSubscriber(modid = "devilcraft", value = Dist.CLIENT)
public class ClientEventHandler {
    private static BlockPos awaitingInputPos = null;

    public static void setAwaitingInputPos(BlockPos pos) {
        awaitingInputPos = pos;
    }

    @SubscribeEvent
    public static void onClientChat(ClientChatEvent event) {
        if (awaitingInputPos != null) {
            String message = event.getMessage().trim();
            ModMessages.sendToServer(new RadiusInputS2CPacket(awaitingInputPos, message));
            event.setCanceled(true);
            awaitingInputPos = null;
        }
    }
}