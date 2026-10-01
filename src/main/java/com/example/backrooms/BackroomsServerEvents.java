package com.example.backrooms;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

import java.util.Random;

public class BackroomsServerEvents {
    private static final Random RANDOM = new Random();

    public static void onServerTick(MinecraftServer server) {
        if (!BackroomsConfig.current.eventActive) return;

        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            if (BackroomsConfig.current.enableSounds) {
                if (RANDOM.nextDouble() < 0.005) {
                    player.getWorld().playSound(null, player.getX() + (RANDOM.nextDouble()-0.5)*30, player.getY(), player.getZ() + (RANDOM.nextDouble()-0.5)*30,
                        SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.AMBIENT, 0.7f, 0.5f);
                }
                if (RANDOM.nextDouble() < BackroomsConfig.current.suddenNoiseFrequency) {
                    player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_WARDEN_HEARTBEAT, SoundCategory.RECORDS, 1.5f, 0.8f);
                }
            }
        }
    }
}
