package dolfe.minecarft;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import java.time.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class HudRender implements HudRenderCallback
{
    private static final Identifier OVERLAY_IMAGE = new Identifier("mod-template", "textures/parry.png");
    public static boolean showFlash = false;

    public static void activateOverlay() {
        ModTemplateClient.getHudRender().resetOverlayTimer();
    }

    public void resetOverlayTimer() {
        //this.overlayTimer = DISPLAY_TIME;
        Timer timer = new Timer();

        Thread one = new Thread() {
            public void run() {
                try {
                    showFlash = true;
                    Thread.sleep(50);
                    showFlash = false;

                } catch (InterruptedException v) {
                }
            }
        };
        one.start();
    }

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        if (showFlash) {
            MinecraftClient client = MinecraftClient.getInstance();
            int screenWidth = client.getWindow().getScaledWidth();
            int screenHeight = client.getWindow().getScaledHeight();
            MinecraftClient mc = MinecraftClient.getInstance();
            client.getTextureManager().bindTexture(OVERLAY_IMAGE);
            drawContext.drawTexture(OVERLAY_IMAGE, 0, 0, 0, 0, screenWidth, screenHeight, screenWidth, screenHeight);
        }
    }
}
