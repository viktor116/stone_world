package com.stworld;

import com.stworld.world.WorldRegister;
import net.fabricmc.api.ModInitializer;

public class Stone_world implements ModInitializer {

	@Override
	public void onInitialize() {
		WorldRegister.init();
	}
}