package com.xiaoyue.celestial_artifacts.content.core.modular;

import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.content.core.feature.FeatureMap;
import com.xiaoyue.celestial_artifacts.content.core.feature.FeatureType;
import com.xiaoyue.celestial_artifacts.content.core.feature.IFeature;
import dev.xkmc.l2library.capability.conditionals.ConditionalData;
import dev.xkmc.l2library.capability.player.PlayerCapabilityHolder;
import dev.xkmc.l2library.capability.player.PlayerCapabilityNetworkHandler;
import dev.xkmc.l2library.capability.player.PlayerCapabilityTemplate;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.HashMap;
import java.util.Map;

@SerialClass
public class CurioCacheCap extends PlayerCapabilityTemplate<CurioCacheCap> {

	public static final Capability<CurioCacheCap> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
	});

	public static final PlayerCapabilityHolder<CurioCacheCap> HOLDER = new PlayerCapabilityHolder<>(
			CelestialArtifacts.loc("curios_cache"), CAPABILITY,
			CurioCacheCap.class, CurioCacheCap::new, PlayerCapabilityNetworkHandler::new
	);

	private final Map<Item, ItemStack> map = new HashMap<>();
	private final FeatureMap features = new FeatureMap();
	private long lastTime = -1;

	private void refresh() {
		if (player.level().getGameTime() != lastTime) {
			lastTime = player.level().getGameTime();
			map.clear();
			features.clear();
			var opt = CuriosApi.getCuriosInventory(player);
			if (opt.resolve().isPresent()) {
				for (var e : opt.resolve().get().getCurios().values()) {
					for (int i = 0; i < e.getStacks().getSlots(); i++) {
						ItemStack stack = e.getStacks().getStackInSlot(i);
						map.put(stack.getItem(), stack);
						if (stack.getItem() instanceof ModularCurio modular) {
							features.addAll(modular.features());
						}
					}
				}
			}
			for (var e : ConditionalData.HOLDER.get(player).data.values()) {
				if (e instanceof IFacet t) {
					features.add(t);
				}
			}
		}
	}

	public ItemStack get(Item item) {
		refresh();
		return map.getOrDefault(item, ItemStack.EMPTY);
	}

	public boolean has(Item... items) {
		refresh();
		for (var e : items) {
			if (!map.containsKey(e))
				return false;
		}
		return true;
	}

	public static void register() {

	}

	public <T extends IFeature> Iterable<T> getFeature(FeatureType<T> type) {
		refresh();
		return features.get(type);
	}

}
