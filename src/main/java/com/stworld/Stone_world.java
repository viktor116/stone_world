package com.stworld;

import com.stworld.world.WorldRegister;
import com.stworld.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

public class Stone_world implements ModInitializer {
	@Override
	public void onInitialize() {
		ModWorldGeneration.generateModWorldGen();
	}
}