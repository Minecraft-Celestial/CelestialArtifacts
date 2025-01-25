package com.xiaoyue.celestial_artifacts.content.container;

import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_core.utils.ItemUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PotionsBagMenu extends AbstractContainerMenu {

	public final ItemStack bag;
	public final Container bagInv;

	public PotionsBagMenu(MenuType<?> type, int id, Inventory inv, ItemStack bag) {
		super(type, id);
		this.bag = bag;
		this.bagInv = new SimpleInventory(bag, 27);
		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 9; ++col) {
				addSlot(new PotionSlot(bagInv, col + row * 9, 8 + col * 18, 18 + row * 18));
			}
		}
		addPlayerSlots(inv, 85);
	}

	public static PotionsBagMenu fromNetwork(MenuType<?> type, int windowId, Inventory inv, FriendlyByteBuf buf) {
		return new PotionsBagMenu(type, windowId, inv, CAItems.POTIONS_BAG.asStack());
	}

	private void addPlayerSlots(Container playerInv, int yOffset) {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 9; col++) {
				addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, yOffset + row * 18));
			}
		}
		for (int col = 0; col < 9; col++) {
			addSlot(new Slot(playerInv, col, 8 + col * 18, yOffset + 58));
		}
	}

	@Override
	public @NotNull ItemStack quickMoveStack(Player player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = slots.get(index);
		if (slot.hasItem()) {
			ItemStack slotItem = slot.getItem();
			stack = slotItem.copy();
			int size = bagInv.getContainerSize();
			if (!slot.mayPlace(slotItem)) {
				return ItemStack.EMPTY;
			}
			if (index < size) {
				if (!moveItemStackTo(slotItem, size, slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!moveItemStackTo(slotItem, 0, size, false)) {
				return ItemStack.EMPTY;
			}
			if (slotItem.getCount() == 0) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}
		return stack;
	}

	@Override
	public boolean stillValid(Player player) {
		return ItemUtils.hasInHand(player, bag);
	}
}
