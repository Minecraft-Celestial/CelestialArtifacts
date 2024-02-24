package com.xiaoyue.celestial_artifacts.content.old.curios.bracelet;

import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class HiddenBracelet extends AttackICurio {
	public HiddenBracelet() {
		super(new Item.Properties().rarity(IRarityUtils.DARK_PURPLE));
	}

	@Override
	public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.hidden_bracelet.shift1",
				ChatFormatting.LIGHT_PURPLE, CAModConfig.HIDDEN_BRACELET_EFFECT_INTERVAL.get() / 20);
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.hidden_bracelet.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.hidden_bracelet.shift3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.hidden_bracelet.shift4");
	}

	@Override
	public void equipmentTick(SlotContext context, Player player) {
		if (player.tickCount % CAModConfig.HIDDEN_BRACELET_EFFECT_INTERVAL.get() == 0) {
			EntityUtils.addEct(player, CCEffects.HIDDEN.get(), 40, 0);
		}
		if (player.getLastHurtMob() != null) {
			if (player.getLastHurtMobTimestamp() < 100) {//FIXME
				if (player.hasEffect(CCEffects.HIDDEN.get())) {
					player.removeEffect(CCEffects.HIDDEN.get());
				}
			}
		}
	}

	@Override
	public void onPlayerHurtEntity(SlotContext context, Player player, LivingHurtEvent event) {
		if (CurioUtils.hasCurio(player, this)) {
			if (player.hasEffect(CCEffects.HIDDEN.get())) {
				event.setAmount(event.getAmount() * 1.25f);
			}
		}
	}

	@Override
	public void onUnderHurt(SlotContext context, Player player, LivingHurtEvent event) {
		if (CurioUtils.hasCurio(player, this)) {
			if (player.hasEffect(CCEffects.HIDDEN.get())) {
				event.setCanceled(true);
				player.removeEffect(CCEffects.HIDDEN.get());
			}
		}
	}
}
