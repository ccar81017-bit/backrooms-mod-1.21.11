package com.example.backrooms;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record SyncConfigS2CPayload(BackroomsConfig.Data config) implements CustomPayload {
    public static final CustomPayload.Id<SyncConfigS2CPayload> ID = new CustomPayload.Id<>(Identifier.of(BackroomsMod.MOD_ID, "sync_config_s2c"));
    public static final PacketCodec<RegistryByteBuf, SyncConfigS2CPayload> CODEC = PacketCodec.of(
        (value, buf) -> buf.writeString(new com.google.gson.Gson().toJson(value.config)),
        buf -> new SyncConfigS2CPayload(new com.google.gson.Gson().fromJson(buf.readString(), BackroomsConfig.Data.class))
    );

    @Override
    public Id<? extends CustomPayload> getId() { return ID; }
}
