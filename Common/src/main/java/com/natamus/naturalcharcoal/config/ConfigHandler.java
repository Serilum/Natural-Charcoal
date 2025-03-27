package com.natamus.naturalcharcoal.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.naturalcharcoal.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean charcoalIsImmuneToFire = true;
	@Entry(min = 0, max = 1.0) public static double burnedLogBecomesCharcoalChance = 0.67;
	@Entry(min = 1, max = 64) public static int charcoalDropAmount = 1;

	public static void initConfig() {
		configMetaData.put("charcoalIsImmuneToFire", Arrays.asList(
			"Whether the charcoal item should survive when being dropped inside fire."
		));
		configMetaData.put("burnedLogBecomesCharcoalChance", Arrays.asList(
			"The chance a log block that has been burned turns into charcoal."
		));
		configMetaData.put("charcoalDropAmount", Arrays.asList(
			"How much charcoal should be dropped from a burned log."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}