package com.xiaoyue.celestial_artifacts.content.items.tool;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.PickaxeItem;
import net.minecraftforge.common.ForgeMod;

public class EarthPickaxe extends PickaxeItem {

	private final Multimap<Attribute, AttributeModifier> defaultModifiers;

	public EarthPickaxe() {
		super(new EarthTierUtils(), 1, -2.5f, new Properties());
		ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.putAll(super.getDefaultAttributeModifiers(EquipmentSlot.MAINHAND));
		builder.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(EarthTierUtils.BASE_BLOCK_REACH_UUID, "Tool modifier", 4, AttributeModifier.Operation.ADDITION));
		builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(EarthTierUtils.BASE_ENTITY_REACH_UUID, "Tool modifier", 2, AttributeModifier.Operation.ADDITION));
		this.defaultModifiers = builder.build();
	}

	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
		return pEquipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
	}

}
