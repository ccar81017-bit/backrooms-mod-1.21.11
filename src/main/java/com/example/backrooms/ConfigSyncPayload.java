package com.example.backrooms;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ConfigSyncPayload(BackroomsConfig.Data config) implements CustomPayload {
    public static final CustomPayload.Id<ConfigSyncPayload> ID = new CustomPayload.Id<>(Identifier.of(BackroomsMod.MOD_ID, "config_sync"));
    public static final PacketCodec<RegistryByteBuf, ConfigSyncPayload> CODEC = PacketCodec.of(
        (value, buf) -> buf.writeString(new com.google.gson.Gson().toJson(value.config)),
        buf -> new ConfigSyncPayload(new com.google.gson.Gson().fromJson(buf.readString(), BackroomsConfig.Data.class))
    );

    @Override
    public Id<? extends CustomPayload> getId() { return ID; }
}
