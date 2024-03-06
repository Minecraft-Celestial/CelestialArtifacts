package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HolyTalisman implements MultiLineText, TickFacet, CAAttackToken {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {//TODO text
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.holy_talisman.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.holy_talisman.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.holy_talisman.shift3");
	}

	@Override
	public void tick(LivingEntity entity, ItemStack stack) {
		if (entity.tickCount % 200 == 0) {
			List<LivingEntity> entities = EntityUtils.getExceptForCentralEntity(entity, 8, 2, LivingEntity::isAlive);
			int size = entities.size();
			for (LivingEntity list : entities) {
				if (list instanceof Monster monster) {
					EntityUtils.addEct(monster, MobEffects.WEAKNESS, size * 50, 0);
				}
			}
		}
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		var e = cache.getAttacker();
		if (e != null) {
			cache.addDealtModifier(DamageModifier.multTotal(
					e.getMobType() == MobType.UNDEAD ? 0.65f : 0.75f));
			cache.addDealtModifier(DamageModifier.nonlinearFinal(1230, v -> parse(player, v)));
		}
	}

	private float parse(Player player, float val) {
		Item item = CAItems.HOLY_TALISMAN.get();
		if (player.getHealth() < val) {
			if (!player.getCooldowns().isOnCooldown(item)) {
				player.setAbsorptionAmount(player.getMaxHealth());
				player.getCooldowns().addCooldown(item, 1200);
				return 0;
			}
		}
		return val;
	}
}
