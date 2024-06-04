package dolfe.minecarft;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import dolfe.minecarft.ModTemplate;

public class ModSounds {
	public static final Logger LOGGER = LoggerFactory.getLogger(ModTemplate.class);




	//public static SoundEvent GetParry()
	//{
	//	return PARRYSoundEvent;
	//}
//
	//private static SoundEvent registerSoundEvent(String name) {
	//	LOGGER.info("Register SFX!!!!!!!!!!!!!!!!!!");
	//	Identifier id = new Identifier("dolfe", name);
	//	return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
	//}
}
