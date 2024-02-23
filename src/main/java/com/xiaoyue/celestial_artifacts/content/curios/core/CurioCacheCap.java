package com.xiaoyue.celestial_artifacts.content.curios.core;

import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.content.curios.modular.BreakSpeedFacet;
import com.xiaoyue.celestial_artifacts.content.curios.modular.ModularCurio;
import com.xiaoyue.celestial_artifacts.content.curios.token.CAAttackToken;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	private final List<CAAttackToken> token = new ArrayList<>();
	private final List<BreakSpeedFacet> mining = new ArrayList<>();
	private long lastTime = -1;

	private void refresh() {
		if (player.level().getGameTime() != lastTime) {
			lastTime = player.level().getGameTime();
			map.clear();
			token.clear();
			mining.clear();
			var opt = CuriosApi.getCuriosInventory(player);
			if (opt.resolve().isPresent()) {
				for (var e : opt.resolve().get().getCurios().values()) {
					for (int i = 0; i < e.getStacks().getSlots(); i++) {
						ItemStack stack = e.getStacks().getStackInSlot(i);
						map.put(stack.getItem(), stack);
						if (stack.getItem() instanceof ModularCurio modular) {
							token.addAll(modular.atkTokens());
							mining.addAll(modular.miningTokens());
						}
					}
				}
			}
			for (var e : ConditionalData.HOLDER.get(player).data.values()) {
				if (e instanceof CAAttackToken t) {
					token.add(t);
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

	public List<CAAttackToken> getAtkTokens() {
		refresh();
		return token;
	}

	public List<BreakSpeedFacet> getMining() {
		refresh();
		return mining;
	}

}
