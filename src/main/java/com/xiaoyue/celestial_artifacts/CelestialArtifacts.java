package com.xiaoyue.celestial_artifacts;

import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.xiaoyue.celestial_artifacts.content.core.modular.CurioCacheCap;
import com.xiaoyue.celestial_artifacts.data.*;
import com.xiaoyue.celestial_artifacts.events.CAAttackListener;
import com.xiaoyue.celestial_artifacts.register.*;
import dev.xkmc.l2damagetracker.contents.attack.AttackEventHandler;
import dev.xkmc.l2library.base.L2Registrate;
import dev.xkmc.l2library.serial.config.PacketHandler;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkDirection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.xiaoyue.celestial_artifacts.CelestialArtifacts.MODID;

@Mod(CelestialArtifacts.MODID)
@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CelestialArtifacts {


	public static final String MODID = "celestial_artifacts";
	public static final Logger LOGGER = LogManager.getLogger();
	public static final L2Registrate REGISTRATE = new L2Registrate(MODID);
	public static final RegistryEntry<CreativeModeTab> TAB =
			REGISTRATE.buildModCreativeTab("curios", "Celestial Artifacts",
					e -> e.icon(CAItems.AMETHYST_RING::asStack));
	public static final PacketHandler HANDLER = new PacketHandler(loc("main"), 1,
			e -> e.create(CAbilityPacket.class, NetworkDirection.PLAY_TO_SERVER));

	public CelestialArtifacts() {
		CAItems.register();
		CAEffects.register();
		CALootModifier.register();
		CurioCacheCap.register();
		CAMenus.register();
		CAModConfig.init();
		AttackEventHandler.register(3460, new CAAttackListener());
		REGISTRATE.addDataGenerator(ProviderType.LANG, CALang::addLang);
		REGISTRATE.addDataGenerator(ProviderType.RECIPE, CARecipeGen::onRecipeGen);
		REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, CATagGen::onItemTagGen);
		REGISTRATE.addDataGenerator(ProviderType.ENTITY_TAGS, CATagGen::onEntityTagGen);
		REGISTRATE.addDataGenerator(ProviderType.LOOT, CALootTableGen::onLootGen);
	}

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		boolean included = event.includeServer();
		var pvd = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();
		DataGenerator gen = event.getGenerator();
		PackOutput output = gen.getPackOutput();
		gen.addProvider(included, new CAGLMProvider(output));
		gen.addProvider(included, new CASlotGen(gen));
	}


	public static ResourceLocation loc(String id) {
		return new ResourceLocation(MODID, id);
	}


}
