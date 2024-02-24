package com.xiaoyue.celestial_artifacts.content.old.curios.charm;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class HolySword extends AttackICurio {
	public HolySword() {
		super(new Item.Properties().rarity(Rarity.UNCOMMON));
	}

	@Override
	public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.holy_sword.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.holy_sword.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.holy_sword.shift3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.holy_sword.shift4",
				ChatFormatting.GOLD, CAModConfig.HOLY_SWORD_LOST_LIFE_ADD_DAMAGE.get() + "%");
	}

	public static float lossLifeAdd(Player player) {
		return (float) (((player.getMaxHealth() - player.getHealth()) / CAModConfig.HOLY_SWORD_LOST_LIFE_ADD_DAMAGE.get()) * 0.02f);
	}

	@Override
	public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
		Modifiers.put(L2DamageTracker.CRIT_RATE.get(), new AttributeModifier(uuid, "aabb", 0.15, AttributeModifier.Operation.ADDITION));
		return Modifiers;
	}

	@Override
	public void onPlayerHurtEntity(SlotContext context, Player player, LivingHurtEvent event) {
		if (CurioUtils.hasCurio(player, this)) {
			float min = (float) Math.min(HolySword.lossLifeAdd(player), CAModConfig.HOLY_SWORD_MAX_ADD_DAMAGE.get());
			event.setAmount(event.getAmount() * (1 + min));
			if (event.getEntity().getMobType() == MobType.UNDEAD) {
				player.heal(event.getAmount());
			}
		}
	}

	@Override
	public void onUnderHurt(SlotContext context, Player player, LivingHurtEvent event) {
		if (CurioUtils.hasCurio(player, this)) {
			if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
				livingEntity.hurt(player.damageSources().playerAttack(player), event.getAmount() * 0.12f);
			}
		}
	}
}
