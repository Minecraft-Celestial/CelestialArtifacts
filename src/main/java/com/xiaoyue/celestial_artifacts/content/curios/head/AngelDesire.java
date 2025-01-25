package com.xiaoyue.celestial_artifacts.content.curios.head;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CATagGen;
import com.xiaoyue.celestial_core.utils.CCUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class AngelDesire implements TickFacet, MultiLineText, CAAttackToken {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Head.ANGEL_DESIRE_2.get()));
		list.add(TextFacet.wrap(CALang.Head.ANGEL_DESIRE_3.get()));
	}

	public boolean isCurrent(ItemStack stack) {
		return stack.getItem() instanceof ElytraItem || stack.is(CATagGen.ANGEL_DESIRE_COMPAT);
	}

	@Override
	public void tick(LivingEntity entity, ItemStack stack) {
		if (entity.isFallFlying() && isCurrent(entity.getItemBySlot(EquipmentSlot.CHEST))) {
			CCUtils.addFlySpeed(entity, 1f);
		}
	}

	@Override
	public boolean onPlayerAttacked(Player player, AttackCache cache) {
		var event = cache.getLivingAttackEvent();
		assert event != null;
		return event.getSource().is(DamageTypeTags.IS_PROJECTILE);
	}
}
