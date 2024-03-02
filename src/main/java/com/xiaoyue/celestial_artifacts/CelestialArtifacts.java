package com.xiaoyue.celestial_artifacts;

import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.xiaoyue.celestial_artifacts.content.core.modular.CurioCacheCap;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.data.CARecipeGen;
import com.xiaoyue.celestial_artifacts.events.CAAttackListener;
import com.xiaoyue.celestial_artifacts.network.CMessages;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import dev.xkmc.l2damagetracker.contents.attack.AttackEventHandler;
import dev.xkmc.l2library.base.L2Registrate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import static com.xiaoyue.celestial_artifacts.CelestialArtifacts.MODID;

@Mod(CelestialArtifacts.MODID)
@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CelestialArtifacts {
	public static final String MODID = "celestial_artifacts";
	public static final L2Registrate REGISTRATE = new L2Registrate(MODID);

	public static final RegistryEntry<CreativeModeTab> TAB =
			REGISTRATE.buildModCreativeTab("curios", "Celestial Artifacts",
					e -> e.icon(CAItems.AMETHYST_RING::asStack));

	public CelestialArtifacts() {
		CAItems.register();
		CurioCacheCap.register();
		CAModConfig.init();
		AttackEventHandler.register(3460, new CAAttackListener());
		REGISTRATE.addDataGenerator(ProviderType.LANG, CALang::addLang);
		REGISTRATE.addDataGenerator(ProviderType.RECIPE, CARecipeGen::onRecipeGen);
	}

	@SubscribeEvent
	public static void commonSetup(final FMLCommonSetupEvent event) {
		CMessages.register();
	}

	public static ResourceLocation loc(String id) {
		return new ResourceLocation(MODID, id);
	}


}
