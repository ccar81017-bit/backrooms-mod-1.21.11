package com.example.backrooms;

import com.example.backrooms.networking.ConfigSyncPayload;
import com.example.backrooms.networking.SyncConfigS2CPayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackroomsMod implements ModInitializer {
    public static final String MOD_ID = "backrooms";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.log(org.slf4j.Slf4j.Level.INFO, "Initializing Backrooms Mod!");

        // Регистрация сетевых пакетов с правильными именами полей (ID и CODEC)
        PayloadTypeRegistry.playC2S().id(ConfigSyncPayload.ID, ConfigSyncPayload.CODEC);
        PayloadTypeRegistry.playS2C().id(SyncConfigS2CPayload.ID, SyncConfigS2CPayload.CODEC);
    }
}
