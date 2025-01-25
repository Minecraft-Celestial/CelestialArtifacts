package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.feature.HealFeature;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.AttrAdder;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_core.register.CCAttributes;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class DemonCurse extends BaseTickingToken implements HealFeature {

	@SerialClass.SerialField
	public double demon_curse_add;

	public static double atkBonus() {
		return CAModConfig.COMMON.charm.demonCurseAttackBonus.get();
	}

	public static double speedBonus() {
		return CAModConfig.COMMON.charm.demonCurseSpeedBonus.get();
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Charm.DEMON_CURSE_0.get().withStyle(ChatFormatting.RED)));
		list.add(TextFacet.wrap(CALang.Charm.DEMON_CURSE_1.get()));
		list.add(TextFacet.inner(atk().getText(atkBonus())));
		list.add(TextFacet.inner(speed().getText(speedBonus())));
		list.add(CALang.Modular.CURRENT_BONUS.get().withStyle(ChatFormatting.DARK_PURPLE));
		list.add(TextFacet.wrap(atk().getTooltip()));
		list.add(TextFacet.wrap(speed().getTooltip()));
	}

	@Override
	public void onPlayerHeal(Player player, LivingHealEvent event) {
		event.setCanceled(true);
	}

	private AttrAdder atk() {
		return AttrAdder.of("demon_curse", () -> Attributes.ATTACK_DAMAGE,
				AttributeModifier.Operation.MULTIPLY_BASE, demon_curse_add * 100 * atkBonus());
	}

	private AttrAdder speed() {
		return AttrAdder.of("demon_curse", () -> Attributes.MOVEMENT_SPEED,
				AttributeModifier.Operation.MULTIPLY_BASE, demon_curse_add * 100 * speedBonus());
	}

	@Override
	protected void removeImpl(Player player) {
		atk().removeImpl(player);
		speed().removeImpl(player);
	}

	@Override
	protected void tickImpl(Player player) {
		demon_curse_add = player.getAttributeValue(CCAttributes.REPLY_POWER.get()) - 1;
		atk().tickImpl(player);
		speed().tickImpl(player);
	}

}
