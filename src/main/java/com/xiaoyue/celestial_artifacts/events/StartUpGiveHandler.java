package com.xiaoyue.celestial_artifacts.events;

import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.content.generic.PlayerFlagData;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import static com.xiaoyue.celestial_artifacts.CelestialArtifacts.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class StartUpGiveHandler {

	@SubscribeEvent
	public static void tickPlayer(TickEvent.PlayerTickEvent event) {
		var player = event.player;
		if (player.level().isClientSide()) return;
		var data = PlayerFlagData.HOLDER.get(player);
		if (CAModConfig.COMMON.misc.giveItemsOnStart.get() && !data.hasFlag("hello_world")) {
			data.addFlag("hello_world");
			CAItems.HEIRLOOM_NECKLACE.get().enableMap(e -> player.addItem(e.getDefaultInstance()));
			if (!CAModConfig.COMMON.misc.catastropheScrollEquipOnStart.get())
				CAItems.CATASTROPHE_SCROLL.get().enableMap(e -> player.addItem(e.getDefaultInstance()));
		}
		if (CAModConfig.COMMON.misc.catastropheScrollEquipOnStart.get() &&
				CAItems.CATASTROPHE_SCROLL.get().enableConfig().get()) {
			if (!data.hasFlag("cs") && !CurioUtils.isCsOn(player)) {
				var opt = CuriosApi.getCuriosInventory(player).resolve()
						.flatMap(e -> e.getStacksHandler("catastrophe"))
						.map(ICurioStacksHandler::getStacks);
				if (opt.isPresent() && opt.get().getSlots() > 0 && opt.get().getStackInSlot(0).isEmpty()) {
					opt.get().setStackInSlot(0, CAItems.CATASTROPHE_SCROLL.asStack());
					data.addFlag("cs");
				}
			}
		}
	}
}
