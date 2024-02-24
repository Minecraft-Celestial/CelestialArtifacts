package com.xiaoyue.celestial_artifacts.content.curios.set;

import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.mixin.AbstractArrowAccessor;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class SeaGodSet extends BaseTickingToken implements CAAttackToken {

	@Override
	protected void removeImpl(Player player) {

	}

	@Override
	protected void tickImpl(Player player) {

	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sea_god.alt1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sea_god.alt2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sea_god.alt3");
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		if (getSource(cache).getDirectEntity() instanceof AbstractArrow e) {
			ItemStack item = ((AbstractArrowAccessor) e).callGetPickupItem();
			if (item != null && item.isDamageableItem())
				cache.addHurtModifier(DamageModifier.multTotal(1.75f));
		} else if (cache.getWeapon().is(Tags.Items.TOOLS_TRIDENTS))
			cache.addHurtModifier(DamageModifier.multTotal(1.5f));

	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		if (cache.getAttacker() instanceof Mob mob) {
			if (mob.getMobType() == MobType.WATER) {
				cache.addDealtModifier(DamageModifier.multTotal(0.65f));
			}
		}
	}

}
