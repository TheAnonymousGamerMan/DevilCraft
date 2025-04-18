package net.zfair.devilcraft.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.zfair.devilcraft.devilcraft;
import net.zfair.devilcraft.network.packet.RadiusInputS2CPacket;
import net.zfair.devilcraft.network.packet.RequestRadiusInputS2CPacket;

public class ModMessages {
    private static SimpleChannel INSTANCE;
    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(ResourceLocation.tryBuild(devilcraft.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(RequestRadiusInputS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(RequestRadiusInputS2CPacket::new)
                .encoder(RequestRadiusInputS2CPacket::toBytes)
                .consumerMainThread(RequestRadiusInputS2CPacket::handle)
                .add();

        net.messageBuilder(RadiusInputS2CPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(RadiusInputS2CPacket::new)
                .encoder(RadiusInputS2CPacket::toBytes)
                .consumerMainThread(RadiusInputS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}