package dolfe.minecarft.mixin.client;

import dolfe.minecarft.ModTemplate;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public class MenuButton extends Screen {

    protected MenuButton(Text title) {
        super(title);
    }
    //@Inject(at = @At("RETURN"), method = "init()V")
    //private void addModsButton(CallbackInfo ci) {
    //    this.addDrawableChild(ButtonWidget.builder(Text.translatable("modname.customButton"), (button) -> {
    //        this.client.setScreen(new SelectWorldScreen(this));
    //        ModTemplate.LOGGER.info("Width: " + this.width + " | Height: " + this.height + " \n \n \n");
    //    }).dimensions((this.width / 2) + 190, (this.height / 2) - 120, 50, 20).build());
    //}

    @Inject(at = @At("RETURN"), method = "init()V")
    private void addModsButton(CallbackInfo ci) {
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("Button"), (button) -> {
            //this.client.player.sendMessage(Text.of("Button pressed"));
            ModTemplate.LOGGER.info("Width: " + this.width + " | Height: " + this.height + " \n \n \n");
            ModTemplate.StartParry();
        }).dimensions((this.width / 2) + 190, (this.height / 2) - 120, 50, 20).build());
    }
}

