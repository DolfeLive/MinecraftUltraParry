package dolfe.minecarft;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.BreezeWindChargeEntity;
import net.minecraft.entity.projectile.AbstractWindChargeEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import java.lang.*;
import java.util.concurrent.TimeUnit;


public class ModTemplate implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("mod-template");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		registerSounds();
		AttackEntityCallback.EVENT.register((player, world, hand, entity, entityHitResult) -> {
			if (entity instanceof FireballEntity || entity instanceof  AbstractWindChargeEntity) {
				// Check if the attacked entity is a fireball
				// Your logic here
				// For example, play a sound
				//LOGGER.info("Player hit ghast fireball player, pos: " + player.getBlockPos());
				//world.playSound(null, player.getBlockPos(), PARRYSoundEvent, SoundCategory.PLAYERS, 1.0f, 1.0f);
				StartParry();
				return ActionResult.PASS; // Return SUCCESS if the interaction is handled
			}
			//if (entity instanceof PlayerEntity)
			//{
			//	//LOGGER.info("Player hit a  player, pos: " + player.getBlockPos());
			//	StartParry();
			//	return ActionResult.PASS;
//
			//}
			//if(player.fallDistance > 0.01)
			//{
			//	//LOGGER.info("Player hit a critical hit, pos: " + player.getBlockPos());
			//	StartParry();
			//	return ActionResult.PASS;
			//}
			return ActionResult.PASS; // Return PASS to allow other mods to handle the interaction
		});

	}
	public static void StartParry()
	{
		playParry = true;
		// If u want delay (could get you banned)
        //try {
        //    //TimeUnit.MILLISECONDS.sleep(10);
        //} catch (InterruptedException e) {
        //    //throw new RuntimeException(e);
        //}
    }
	public static final Identifier  PARRY = new Identifier("mod-template:parry");
	public static SoundEvent PARRYSoundEvent = SoundEvent.of(PARRY);

	public static void registerSounds()
	{
		Registry.register(Registries.SOUND_EVENT, PARRY, PARRYSoundEvent);
	}
	public static boolean playParry;

	private boolean isLookingDown(PlayerEntity player) {
		Vec3d lookVec = player.getRotationVec(1.0F).normalize();
		return lookVec.y < -0.1; // You can adjust this threshold as needed
	}


}
