package com.example.backrooms.client;

import com.example.backrooms.BackroomsConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;

public class HorrorHudOverlay {

    public static void render(DrawContext context, float tickDelta) {
        if (!BackroomsConfig.current.eventActive) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.options.hudHidden) return;

        PlayerEntity player = client.player;
        int width = context.getScaledWindowWidth();
        int height = context.getScaledWindowHeight();

        // Atmospheric Horror Vignette / Grain Header
        // Bottom left corner: Custom Horror Health & Hunger Bars
        int baseX = 20;
        int baseY = height - 35;

        // Background semi-transparent dark panel
        context.fill(baseX - 6, baseY - 26, baseX + 140, baseY + 22, 0xC00A0A0A);
        context.fill(baseX - 5, baseY - 25, baseX + 139, baseY + 21, 0x801F1F1F);

        // Health Display (Horror style: Red bleeding bar & text)
        float health = player.getHealth();
        float maxHealth = player.getMaxHealth();
        String healthText = String.format("§4♥ §f%.0f/%.0f", health, maxHealth);
        context.drawTextWithShadow(client.textRenderer, healthText, baseX, baseY - 20, 0xFF5555);

        // Health Bar fill
        int healthBarWidth = 100;
        int currentHealthWidth = (int) ((health / maxHealth) * healthBarWidth);
        context.fill(baseX + 30, baseY - 18, baseX + 30 + healthBarWidth, baseY - 12, 0xFF330000);
        if (currentHealthWidth > 0) {
            context.fill(baseX + 30, baseY - 18, baseX + 30 + currentHealthWidth, baseY - 12, 0xFFAA0000);
        }

        // Hunger Display (Horror style: Rusty/Yellowish bar)
        int foodLevel = player.getHungerManager().getFoodLevel();
        String hungerText = String.format("§e🍔 §f%d/20", foodLevel);
        context.drawTextWithShadow(client.textRenderer, hungerText, baseX, baseY - 6, 0xFFAA55);

        int hungerBarWidth = 100;
        int currentHungerWidth = (int) (((float) foodLevel / 20.0f) * hungerBarWidth);
        context.fill(baseX + 30, baseY - 4, baseX + 30 + hungerBarWidth, baseY + 2, 0xFF332200);
        if (currentHungerWidth > 0) {
            context.fill(baseX + 30, baseY - 4, baseX + 30 + currentHungerWidth, baseY + 2, 0xFFFFaa00);
        }

        // Top Right: Backrooms Event & Fragments Counter (Liminal HUD)
        int panelWidth = 160;
        int panelX = width - panelWidth - 15;
        int panelY = 15;

        context.fill(panelX - 4, panelY - 4, panelX + panelWidth + 4, panelY + 36, 0xC00A0A0A);
        context.fill(panelX - 3, panelY - 3, panelX + panelWidth + 3, panelY + 35, 0x90262626);

        context.drawTextWithShadow(client.textRenderer, "§6[ THE BACKROOMS ]", panelX + 15, panelY, 0xFFD700);
        
        String fragmentsStr = String.format("§7Fragments: §e%d/%d", 
            BackroomsConfig.current.collectedFragments, 
            BackroomsConfig.current.requiredFragments
        );
        context.drawTextWithShadow(client.textRenderer, fragmentsStr, panelX + 15, panelY + 16, 0xCCCCCC);
    }
}
