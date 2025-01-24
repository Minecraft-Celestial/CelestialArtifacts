package com.xiaoyue.celestial_artifacts.content.core.modular;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.core.feature.FeatureMap;
import com.xiaoyue.celestial_artifacts.content.core.token.TokenFacet;
import com.xiaoyue.celestial_artifacts.content.curios.curse.CatastropheScroll;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.data.CATagGen;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import dev.xkmc.l2damagetracker.contents.curios.AttrTooltip;
import dev.xkmc.l2damagetracker.contents.curios.L2Totem;
import dev.xkmc.l2damagetracker.contents.curios.TotemHelper;
import dev.xkmc.l2library.util.Proxy;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.ISlotType;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.*;
import java.util.function.Consumer;

public final class ModularCurio extends BaseCurio implements L2Totem {

	public static Builder builder() {
		return new Builder();
	}

	public static ModularCurio of(IFacet... facets) {
		return builder().build(facets);
	}

	private final List<AttrFacet> attributes = new ArrayList<>();
	private final List<SlotFacet> slots = new ArrayList<>();
	private final List<TextFacet> text = new ArrayList<>();
	private final List<AttrTextFacet> attrText = new ArrayList<>();
	private final List<TickFacet> tick = new ArrayList<>();
	private SetFacet set = null;
	private TotemFacet totem = null;
	private TokenFacet<?> token = null;
	private final FeatureMap features = new FeatureMap();

	public final Prop prop;

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
		if (facet instanceof AttrTextFacet e) attrText.add(e);
		if (facet instanceof TickFacet e) tick.add(e);
		if (facet instanceof SetFacet e) set = e;
		if (facet instanceof TotemFacet e) totem = e;
		if (facet instanceof TokenFacet<?> e) token = e;
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

	@Nullable
	private TotemFacet totem(LivingEntity le) {
		if (totem != null) return totem;
		if (token != null && token.get(le) instanceof TotemFacet t) return t;
		return null;
	}

	@Override
	public void trigger(LivingEntity self, ItemStack holded, TotemHelper.TotemSlot second, DamageSource source) {
		TotemFacet totem = totem(self);
		if (totem != null && self instanceof Player pl)
			totem.trigger(pl, holded, second, source);
	}

	@Override
	public boolean allow(LivingEntity self, ItemStack stack, DamageSource pDamageSource) {
		if (!enableConfig().get()) return false;
		TotemFacet totem = totem(self);
		return totem != null && self instanceof Player player && totem.allow(player, stack, pDamageSource);
	}

	@Override
	public boolean isValid(LivingEntity self, ItemStack stack, TotemHelper.TotemSlot slot) {
		if (!enableConfig().get()) return false;
		TotemFacet totem = totem(self);
		return totem != null && slot instanceof TotemHelper.CurioPred && self instanceof Player;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		if (!enableConfig().get()) {
			list.add(CALang.Tooltip.BAN.get().withStyle(ChatFormatting.RED));
			return;
		}
		if (level == null || Screen.hasShiftDown()) {
			for (var e : text) {
				e.addText(level, list);
			}
			if (prop.enderMask) {
				list.add(TextFacet.wrap(CALang.Modular.ENDER_MASK.get().withStyle(ChatFormatting.GRAY)));
			}
		} else {
			if (stack.is(CATagGen.REQUIRE_CURSE)) {
				list.add(CALang.Modular.curse());
			}
			if (!text.isEmpty() || prop.enderMask) {
				list.add(CALang.Modular.shift());
			}
			if (prop.immune) {
				list.add(CALang.Modular.IMMUNE.get().withStyle(ChatFormatting.GOLD));
			}
		}
		if (set != null) {
			if (level == null || Screen.hasAltDown()) {
				set.addText(level, list);
			} else {
				list.add(CALang.Modular.alt());
			}
		}
		if (!Screen.hasShiftDown() && prop.curse()) {
			if (Screen.hasAltDown()) {
				list.addAll(CatastropheScroll.addInfo());
			} else {
				list.add(CALang.Modular.curseAlt());
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

	public void addLootTooltip(List<Component> list) {
		if (prop.fortune != 0) {
			list.add(AttrFacet.simpleAdd(CALang.Modular.FORTUNE.get(), prop.fortune));
		}
		if (prop.loot != 0) {
			list.add(AttrFacet.simpleAdd(CALang.Modular.LOOT.get(), prop.loot));
		}
	}

	@Override
	public List<Component> getAttributesTooltip(List<Component> tooltips, ItemStack stack) {
		if (attributes.isEmpty()) {
			if (prop.hideAttr || Proxy.getPlayer() == null) return tooltips;
			if (attrText.isEmpty() && prop.fortune == 0 && prop.loot == 0) return tooltips;
			for (ISlotType slot : CuriosApi.getItemStackSlots(stack, Proxy.getPlayer()).values()) {
				tooltips.add(Component.empty());
				tooltips.add(Component.translatable("curios.modifiers." + slot.getIdentifier()).withStyle(ChatFormatting.GOLD));
				if (prop.fortune != 0 || prop.loot != 0) {
					addLootTooltip(tooltips);
				}
				for (var e : attrText) {
					e.addAttrText(tooltips);
				}
			}
			return tooltips;
		}
		addLootTooltip(tooltips);
		for (var e : attrText) {
			e.addAttrText(tooltips);
		}
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
			if (AttrTooltip.isMult(ent.attr().get()) || AttrTooltip.isNegative(ent.attr().get())) {
				rep = AttrTooltip.getDesc(ent.attr().get(), val, ent.op());
			}
			if (rep != null) {
				tooltips.set(index, rep);
			}
		}
		tooltips.removeIf(Objects::isNull);
		return tooltips;
	}

	@Override
	public boolean isEnderMask(ItemStack stack, Player player, EnderMan endermanEntity) {
		return prop.enderMask;
	}

	@Override
	public boolean canEquip(SlotContext slotContext, ItemStack stack) {
		if (!enableConfig().get()) return false;
		var entity = slotContext.entity();
		if (stack.is(CATagGen.REQUIRE_CURSE)) {
			if (!(entity instanceof Player player && CurioUtils.isCsOn(player))) {
				return false;
			}
		}
		return super.canEquip(slotContext, stack);
	}

	private String idCache;

	public ForgeConfigSpec.BooleanValue enableConfig() {
		if (idCache == null) {
			var rl = ForgeRegistries.ITEMS.getKey(this);
			assert rl != null;
			idCache = rl.getPath();
		}
		return CAModConfig.COMMON.toggles.get(idCache);
	}

	public void enableMap(Consumer<ModularCurio> cons) {
		if (enableConfig().get()) {
			cons.accept(this);
		}
	}

	@Override
	public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
		if (!prop.curse() || !CAModConfig.COMMON.misc.catastropheScrollPreventUnequip.get()) {
			return true;
		}
		return slotContext.entity() instanceof Player player && player.isCreative();
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

		private final Properties prop;
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
