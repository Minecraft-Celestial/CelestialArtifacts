package com.xiaoyue.celestial_artifacts.register;

import com.tterrag.registrate.util.entry.MenuEntry;
import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.content.client.PotionsBagScreen;
import com.xiaoyue.celestial_artifacts.content.container.PotionsBagMenu;

public class CAMenus {

	public static final MenuEntry<PotionsBagMenu> POTIONS_BAG_MENU;

	static {
		POTIONS_BAG_MENU = CelestialArtifacts.REGISTRATE.menu("potion_bag",
				PotionsBagMenu::fromNetwork, () -> PotionsBagScreen::new).register();
	}

	public static void register() {

	}
}
