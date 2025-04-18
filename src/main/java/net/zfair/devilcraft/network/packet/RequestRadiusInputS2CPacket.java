package net.zfair.devilcraft.network.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.zfair.devilcraft.client.ClientEventHandler;

import java.util.function.Supplier;

public class RequestRadiusInputS2CPacket {
    private final BlockPos pos;

    public RequestRadiusInputS2CPacket(BlockPos pos) {
        this.pos = pos;
    }

    public RequestRadiusInputS2CPacket(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            ClientEventHandler.setAwaitingInputPos(pos);
        });
        ctx.setPacketHandled(true);
    }
}