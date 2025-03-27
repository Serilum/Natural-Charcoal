package com.natamus.naturalcharcoal;

import com.natamus.collective.check.ShouldLoadCheck;
import com.natamus.naturalcharcoal.util.Reference;
import net.fabricmc.api.ClientModInitializer;

public class ModFabricClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() { 
		if (!ShouldLoadCheck.shouldLoad(Reference.MOD_ID)) {
			return;
		}

		registerEvents();
	}
	
	private void registerEvents() {

	}
}
