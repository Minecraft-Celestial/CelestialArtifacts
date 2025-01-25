package com.xiaoyue.celestial_artifacts.content.container;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class SimpleInventory extends SimpleContainer {

	private static final String TAG_ITEMS = "items";
	private final ItemStack stack;

	public SimpleInventory(ItemStack stack, int size) {
		super(size);
		this.stack = stack;
		ListTag list = stack.getOrCreateTag().getList(TAG_ITEMS, Tag.TAG_COMPOUND);
		int i = 0;
		for (; i < size && i < list.size(); i++) {
			setItem(i, ItemStack.of(list.getCompound(i)));
		}
	}

	@Override
	public boolean stillValid(Player player) {
		return !stack.isEmpty();
	}

	@Override
	public void setChanged() {
		super.setChanged();
		ListTag list = new ListTag();
		for (int i = 0; i < getContainerSize(); i++) {
			list.add(getItem(i).save(new CompoundTag()));
		}
		stack.getOrCreateTag().put(TAG_ITEMS, list);
	}
}
