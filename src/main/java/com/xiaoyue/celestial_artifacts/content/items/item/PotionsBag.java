package com.xiaoyue.celestial_artifacts.content.items.item;

import com.xiaoyue.celestial_artifacts.content.container.menu.PotionsBagMenu;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.register.CAMenus;
import com.xiaoyue.celestial_core.content.container.SimpleInventory;
import com.xiaoyue.celestial_core.content.generic.SimpleMenuItem;
import com.xiaoyue.celestial_core.data.CCTagGen;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PotionsBag extends SimpleMenuItem {
    public PotionsBag() {
        super(new Properties().rarity(Rarity.UNCOMMON).stacksTo(1));
    }

    public static boolean isPotion(ItemStack stack) {
        return stack.getItem() instanceof PotionItem || stack.is(CCTagGen.IS_POTION);
    }

    @Override
    public @NotNull AbstractContainerMenu getSimpleMenu(int id, Inventory inv, Player player, ItemStack stack) {
        return new PotionsBagMenu(CAMenus.POTIONS_BAG_MENU.get(), id, inv, stack);
    }

    @Override
    public @NotNull SimpleContainer getSimpleInv(ItemStack stack) {
        return new SimpleInventory(stack, 54) {
            @Override
            public boolean canPlaceItem(int pIndex, ItemStack pStack) {
                return PotionsBag.isPotion(pStack);
            }
        };
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
        list.add(CALang.Tooltip.CAN_STORAGE_POTION.get().withStyle(ChatFormatting.GRAY));
        list.add(CALang.Tooltip.POTIONS_BAG_INFO.get().withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void onShiftUse(Level level, ServerPlayer player, ItemStack stack) {
        SimpleContainer inv = this.getSimpleInv(stack);
        if (inv.isEmpty()) return;
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack invItem = inv.getItem(i);
            if (invItem.isEmpty() || !(invItem.getItem() instanceof PotionItem)) continue;
            List<MobEffectInstance> effects = PotionUtils.getMobEffects(invItem);
            if (effects.stream().anyMatch(e -> player.getActiveEffects().contains(e))) return;
            effects.forEach(player::addEffect);
            ItemStack copy = invItem.copy();
            copy.shrink(1);
            inv.setItem(i, copy);
        }
    }
}
