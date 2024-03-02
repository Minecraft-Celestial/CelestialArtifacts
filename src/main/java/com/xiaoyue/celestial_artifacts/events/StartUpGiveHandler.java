package com.xiaoyue.celestial_artifacts.events;

import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.content.generic.PlayerFlagData;
import com.xiaoyue.celestial_core.utils.LevelUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import static com.xiaoyue.celestial_artifacts.CelestialArtifacts.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class StartUpGiveHandler {

	@SubscribeEvent
	public static void onPlayerJoin(EntityJoinLevelEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (LevelUtils.isServerLevel(player.level())) {
				var data = PlayerFlagData.HOLDER.get(player);
				if (!data.hasFlag("hello_world")) {
					player.addItem(new ItemStack(CAItems.HEIRLOOM_NECKLACE.get()));
					if (!CAModConfig.COMMON.curse.catastropheScrollStart.get()) {
						player.addItem(new ItemStack(CAItems.CATASTROPHE_SCROLL.get()));
					}
					data.addFlag("hello_world");
				}
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerLogged(PlayerEvent.PlayerLoggedInEvent event) {
		if (CAModConfig.COMMON.curse.catastropheScrollStart.get()) {
			Player player = event.getEntity();
			var data = PlayerFlagData.HOLDER.get(player);
			if (!data.hasFlag("cs")) {
				if (!CurioUtils.isCsOn(player)) {
					var opt = CuriosApi.getCuriosInventory(player).resolve()
							.flatMap(e -> e.getStacksHandler("c_charm"))
							.map(ICurioStacksHandler::getStacks);
					if (opt.isPresent() && opt.get().getSlots() > 0 && opt.get().getStackInSlot(0).isEmpty()) {
						opt.get().setStackInSlot(0, CAItems.CATASTROPHE_SCROLL.asStack());
						data.addFlag("cs");
					}
				}
			}
		}
	}
}
