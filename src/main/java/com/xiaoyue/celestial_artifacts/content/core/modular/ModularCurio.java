package com.xiaoyue.celestial_artifacts.content.core.modular;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.core.feature.FeatureMap;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.*;

public final class ModularCurio extends BaseCurio {

	public static Builder builder() {
		return new Builder();
	}

	public static ModularCurio of(IFacet... facets) {
		return builder().build(facets);
	}

	private final List<AttrFacet> attributes = new ArrayList<>();
	private final List<SlotFacet> slots = new ArrayList<>();
	private final List<TextFacet> text = new ArrayList<>();
	private final List<TickFacet> tick = new ArrayList<>();
	private final List<SetFacet> set = new ArrayList<>();
	private final FeatureMap features = new FeatureMap();

	private final Prop prop;

	private ModularCurio(Properties props, Prop prop, IFacet... facets) {
		super(props);
		this.prop = prop;
		for (var e : facets) {
			add(e);
		}
	}

	private void add(IFacet facet) {
		if (facet instanceof AttrFacet e) attributes.add(e);
		if (facet instanceof SlotFacet e) slots.add(e);
		if (facet instanceof TextFacet e) text.add(e);
		if (facet instanceof TickFacet e) tick.add(e);
		if (facet instanceof SetFacet e) set.add(e);
		features.add(facet);
	}

	public FeatureMap features() {
		return features;
	}

	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack) {
		if (slotContext.cosmetic()) return;
		for (var e : tick) {
			e.tick(slotContext.entity(), stack);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		if (Screen.hasShiftDown()) {
			for (var e : text) {
				e.addText(level, list);
			}
			if (prop.enderMask) {
				list.add(CALang.Modular.ENDER_MASK.get().withStyle(ChatFormatting.GRAY));
			}
			if (prop.immune) {
				list.add(CALang.Modular.IMMUNE.get().withStyle(ChatFormatting.GRAY));
			}
		} else {
			if (prop.requireCS) {
				list.add(CALang.Modular.curse());
			}
			list.add(CALang.Modular.shift());
		}
		if (!set.isEmpty()) {
			if (Screen.hasAltDown()) {
				for (var e : set) {
					e.addText(level, list);
				}
			} else {
				list.add(CALang.Modular.alt());
			}
		}
	}

	@Override
	public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
		return prop.fortune;
	}

	@Override
	public int getLootingLevel(SlotContext slotContext, DamageSource source, LivingEntity target, int baseLooting, ItemStack stack) {
		return prop.loot;
	}

	@Override
	public boolean canBeHurtBy(DamageSource source) {
		if (prop.immune) return false;
		return super.canBeHurtBy(source);
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> ans = LinkedHashMultimap.create();
		ResourceLocation id = ForgeRegistries.ITEMS.getKey(this);
		assert id != null;
		for (var e : attributes) {
			e.modify(uuid, id.toString(), ans);
		}
		for (var e : slots) {
			e.modify(uuid, ans);
		}
		return ans;
	}

	@Override
	public List<Component> getAttributesTooltip(List<Component> tooltips, ItemStack stack) {
		if (Screen.hasShiftDown()) return List.of();
		if (attributes.isEmpty()) return tooltips;
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < tooltips.size(); i++) {
			var txt = tooltips.get(i);
			if (txt.getContents() instanceof TranslatableContents tr) {
				var args = tr.getArgs();
				if (args.length == 2 && args[1] instanceof MutableComponent comp) {
					if (comp.getContents() instanceof TranslatableContents sub) {
						map.put(sub.getKey(), i);
					}
				}
			}
		}
		for (AttrFacet ent : attributes) {
			double val = ent.val().getAsDouble();
			Integer index = map.get(ent.attr().get().getDescriptionId());
			if (index == null) continue;
			if (prop.hideAttr()) {
				tooltips.set(index, null);
				continue;
			}
			MutableComponent rep = null;
			if (AttrFacet.isMult(ent.attr().get())) {
				rep = AttrFacet.getDesc(ent.attr().get(), val, ent.op());
			}
			if (ent.attr().get() == L2DamageTracker.REDUCTION.get()) {
				rep = AttrFacet.getDesc(ent.attr().get(), val, ent.op());
			}
			if (rep != null) {
				tooltips.set(index, rep);
			}
		}
		if (!prop.hideAttr()) {
			if (prop.fortune != 0)
				tooltips.add(AttrFacet.simpleAdd(CALang.Modular.FORTUNE.get(), prop.fortune));
			if (prop.loot != 0)
				tooltips.add(AttrFacet.simpleAdd(CALang.Modular.LOOT.get(), prop.loot));
		}
		tooltips.removeIf(Objects::isNull);
		return tooltips;
	}

	@Override
	public boolean isEnderMask(ItemStack stack, Player player, EnderMan endermanEntity) {
		return prop.enderMask;
	}

	@Override
	public boolean canEquip(ItemStack stack, EquipmentSlot armorType, Entity entity) {
		if (prop.requireCS()) {
			if (!(entity instanceof Player player && CurioUtils.isCsOn(player))) {
				return false;
			}
		}
		return super.canEquip(stack, armorType, entity);
	}

	@Override
	public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
		return !prop.curse() || slotContext.entity() instanceof Player player && player.isCreative();
	}

	@NotNull
	@Override
	public ICurio.DropRule getDropRule(SlotContext ctx, DamageSource source, int loot, boolean hit, ItemStack stack) {
		return prop.curse() ? ICurio.DropRule.ALWAYS_KEEP : super.getDropRule(ctx, source, loot, hit, stack);
	}

	public record Prop(
			boolean requireCS, boolean curse, boolean immune, boolean hideAttr, boolean enderMask,
			int fortune, int loot
	) {
	}

	public static class Builder {

		private final Item.Properties prop;
		private boolean requireCS = false, curse = false, immune = false, hideAttr = false,
				enderMask = false;
		private int fortune = 0, loot = 0;

		private Builder() {
			prop = new Properties().stacksTo(1);
		}

		public Builder immune() {
			immune = true;
			prop.fireResistant();
			return this;
		}

		public Builder requireCS() {
			this.requireCS = true;
			return this;
		}

		public Builder hideAttr() {
			this.hideAttr = true;
			return this;
		}

		public Builder curse() {
			this.curse = true;
			return this;
		}

		public Builder enderMask() {
			this.enderMask = true;
			return this;
		}

		public Builder rarity(Rarity rarity) {
			this.prop.rarity(rarity);
			return this;
		}

		public Builder fortune(int fortune) {
			this.fortune = fortune;
			return this;
		}

		public Builder loot(int loot) {
			this.loot = loot;
			return this;
		}

		public ModularCurio build(IFacet... facet) {
			return new ModularCurio(prop,
					new Prop(requireCS, curse, immune, hideAttr, enderMask, fortune, loot),
					facet);
		}

	}


}
