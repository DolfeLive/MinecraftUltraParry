package dolfe.minecarft;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.client.util.math.MatrixStack;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import java.awt.*;
import dolfe.minecarft.mixin.client.*;

public class ModTemplateClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("mod-template");
	public static final Identifier PARRY = new Identifier("mod-template:parry");
	public static SoundEvent PARRYSoundEvent = SoundEvent.of(PARRY);

	public static void registerSounds() {
		Registry.register(Registries.SOUND_EVENT, PARRY, PARRYSoundEvent);
	}

	public static MinecraftClient mc;
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		registerSounds();

		hudRender = new HudRender();
		HudRenderCallback.EVENT.register(hudRender);

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (ModTemplate.playParry) {
				_player = MinecraftClient.getInstance().player;
				mc = MinecraftClient.getInstance();
				if (_player != null) {

					HudRender.activateOverlay();
					_player.playSound(ModTemplate.PARRYSoundEvent, 1F, 1F);
					ModTemplate.playParry = false;
				}
			}
		});
	}

	public static ClientPlayerEntity _player;

	private static HudRender hudRender;

	public static HudRender getHudRender() {
		return hudRender;
	}
}