package com.xiaoyue.celestial_artifacts.content.modular;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.core.BaseCurio;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.*;

public final class ModularCurio extends BaseCurio {

	private final List<AttrFacet> attributes = new ArrayList<>();
	private final List<SlotFacet> slots = new ArrayList<>();
	private final List<TextFacet> text = new ArrayList<>();
	private final List<TickFacet> tick = new ArrayList<>();
	private final List<SetFacet> set = new ArrayList<>();

	private final boolean requireCS;

	public ModularCurio(Properties props, boolean requireCS, IFacet... facets) {
		super(props);
		this.requireCS = requireCS;
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
				e.addText(level, list, text.size() > 1);
			}
		} else {
			if (requireCS) {
				ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.has_cs_curio");
			}
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.has_shift_down");
		}
		if (!set.isEmpty()) {
			if (Screen.hasAltDown()) {
				for (var e : set) {
					e.addText(level, list);
				}
			} else {
				ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.has_alt_down");
			}
		}
		super.appendHoverText(stack, level, list, flag);
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
			MutableComponent rep = null;
			if (AttrFacet.isMult(ent.attr().get())) {
				rep = AttrFacet.getDesc(ent.attr().get(), val, ent.op());
			}
			if (ent.attr().get() == L2DamageTracker.REDUCTION.get()) {
				rep = AttrFacet.getDesc(ent.attr().get(), val, ent.op());
			}
			if (rep != null) {
				Integer index = map.get(ent.attr().get().getDescriptionId());
				if (index != null) {
					tooltips.set(index, rep.withStyle(ChatFormatting.BLUE));
				}
			}
		}
		return tooltips;
	}

	@Override
	public boolean canEquip(ItemStack stack, EquipmentSlot armorType, Entity entity) {
		return entity instanceof Player player && CurioUtils.isCsOn(player) && super.canEquip(stack, armorType, entity);
	}
}
