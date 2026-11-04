package com.xiaoyue.celestial_artifacts;

import com.tterrag.registrate.providers.ProviderType;
import com.xiaoyue.celestial_artifacts.content.core.modular.CurioCacheCap;
import com.xiaoyue.celestial_artifacts.data.*;
import com.xiaoyue.celestial_artifacts.events.CAAttackListener;
import com.xiaoyue.celestial_artifacts.register.*;
import com.xiaoyue.celestial_invoker.content.common.registrar.RegistrateExtra;
import dev.xkmc.l2core.init.reg.registrate.L2Registrate;
import dev.xkmc.l2core.init.reg.registrate.SimpleEntry;
import dev.xkmc.l2damagetracker.contents.attack.AttackEventHandler;
import dev.xkmc.l2serial.network.PacketHandler;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.xiaoyue.celestial_artifacts.CelestialArtifacts.MODID;

@Mod(CelestialArtifacts.MODID)
@EventBusSubscriber(modid = MODID)
public class CelestialArtifacts {

	public static final String MODID = "celestial_artifacts";
	public static final Logger LOGGER = LogManager.getLogger();
	public static final L2Registrate REGISTRATE = new L2Registrate(MODID);
	public static final RegistrateExtra<L2Registrate> EXTRA = new RegistrateExtra<>(REGISTRATE);
	public static final PacketHandler HANDLER = new PacketHandler(MODID, 1,
			e -> e.create(CAbilityPacket.class, PacketHandler.NetDir.PLAY_TO_SERVER));

	public static final SimpleEntry<CreativeModeTab> TAB =
			REGISTRATE.buildModCreativeTab("curios", "Celestial Artifacts",
					e -> e.icon(CAItems.AMETHYST_RING::asStack));

	public CelestialArtifacts() {
		CAItems.register();
		CAEffects.register();
		CALootModifier.register();
		CurioCacheCap.register();
		CAMenus.register();
		CAModConfig.init();
		CAObjects.register();
		AttackEventHandler.register(3460, new CAAttackListener());
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void gatherData(GatherDataEvent event) {
		boolean included = event.includeServer();
		var pvd = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();
		DataGenerator gen = event.getGenerator();
		PackOutput output = gen.getPackOutput();
		gen.addProvider(included, new CAGLMProvider(output, pvd));
		gen.addProvider(included, new CASlotGen(gen, pvd, helper));
		REGISTRATE.addDataGenerator(ProviderType.LANG, CALang::addLang);
		REGISTRATE.addDataGenerator(ProviderType.RECIPE, CARecipeGen::onRecipeGen);
		REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, CATagGen::onItemTagGen);
		REGISTRATE.addDataGenerator(ProviderType.ENTITY_TAGS, CATagGen::onEntityTagGen);
		REGISTRATE.addDataGenerator(ProviderType.LOOT, CALootTableGen::onLootGen);
	}

	public static ResourceLocation loc(String id) {
		return ResourceLocation.fromNamespaceAndPath(MODID, id);
	}
}
