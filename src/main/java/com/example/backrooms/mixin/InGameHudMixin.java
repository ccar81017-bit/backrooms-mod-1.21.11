package com.example.backrooms.mixin;

import com.example.backrooms.client.HorrorHudOverlay;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "render", at = @At("TAIL"))
    private void renderBackroomsHud(DrawContext context, float tickDelta, CallbackInfo ci) {
        HorrorHudOverlay.render(context, tickDelta);
    }
}
