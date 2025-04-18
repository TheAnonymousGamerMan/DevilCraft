package net.zfair.devilcraft.network.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.zfair.devilcraft.block.entity.IndestructibleMakerBlockEntity;

import java.util.function.Supplier;

public class RadiusInputS2CPacket {
    private final BlockPos pos;
    private final String input;

    public RadiusInputS2CPacket(BlockPos pos, String input) {
        this.pos = pos;
        this.input = input;
    }

    public RadiusInputS2CPacket(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
        this.input = buf.readUtf();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeUtf(input);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player != null && player.level().getBlockEntity(pos) instanceof IndestructibleMakerBlockEntity blockEntity) {
                blockEntity.processRadiusInput(player, input);
            }
        });
        ctx.setPacketHandled(true);
    }
}