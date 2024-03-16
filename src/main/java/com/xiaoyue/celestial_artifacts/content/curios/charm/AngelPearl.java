package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.AttrAdder;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class AngelPearl extends BaseTickingToken {

	@SerialClass.SerialField
	private int angel_pearl_add;

	private static int armorAmount() {
		return CAModConfig.COMMON.charm.angelPearlArmor.get();
	}

	private static double replyAmount() {
		return CAModConfig.COMMON.charm.angelPearlRegen.get();
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Charm.ANGEL_PEARL_1.get()));
		list.add(TextFacet.wrap(CALang.Charm.ANGEL_PEARL_2.get()));
		list.add(TextFacet.inner(reply().getText(replyAmount())));
		list.add(TextFacet.inner(armor().getText(armorAmount())));
		list.add(CALang.Modular.CURRENT_BONUS.get().withStyle(ChatFormatting.DARK_PURPLE));
		list.add(TextFacet.wrap(reply().getTooltip()));
		list.add(TextFacet.wrap(armor().getTooltip()));
	}

	private AttrAdder reply() {
		double val = angel_pearl_add * replyAmount();
		return AttrAdder.of("angel_pearl", CCAttributes.REPLY_POWER::get, AttributeModifier.Operation.ADDITION, val);
	}

	private AttrAdder armor() {
		double val = angel_pearl_add * armorAmount();
		return AttrAdder.of("angel_pearl", () -> Attributes.ARMOR, AttributeModifier.Operation.ADDITION, val);

	}

	@Override
	protected void removeImpl(Player player) {
		reply().removeImpl(player);
		armor().removeImpl(player);
	}

	@Override
	protected void tickImpl(Player player) {
		angel_pearl_add = EntityUtils.getBeneficialEffect(player);
		reply().tickImpl(player);
		armor().tickImpl(player);
		if (player.tickCount % 100 == 0) {
			List<LivingEntity> entities = EntityUtils.getExceptForCentralEntity(player, 8, 2, living -> living instanceof Player);
			for (LivingEntity list : entities) {
				EntityUtils.addEct(list, MobEffects.REGENERATION, 100, 0);
			}
		}
	}

}
