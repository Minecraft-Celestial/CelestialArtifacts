package com.xiaoyue.celestial_artifacts.mixin;

import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.List;

@Mixin(EnchantmentMenu.class)
public abstract class MixinEnchTable {

    @Shadow @Final private Container enchantSlots;

    @Shadow @Final private ContainerLevelAccess access;

    @Shadow protected abstract List<EnchantmentInstance> getEnchantmentList(ItemStack p_39472_, int p_39473_, int p_39474_);

    @Shadow @Final public int[] costs;

    @Shadow @Final private DataSlot enchantmentSeed;

    @Shadow public abstract void slotsChanged(Container p_39461_);

    @Inject(at = {@At("HEAD")}, method = {"clickMenuButton"})
    public void celestial_artifacts$EnchButton(Player p_39465_, int p_39466_, CallbackInfoReturnable<Boolean> cir) {
        ItemStack itemStack = this.enchantSlots.getItem(0);
        if (CurioUtils.hasCurio(p_39465_, CAItems.CHAOTIC_PENDANT.get())) {
            this.access.execute((level, blockPos) -> {
                List<EnchantmentInstance> list = this.getEnchantmentList(itemStack, p_39466_, this.costs[p_39466_]);
                p_39465_.onEnchantmentPerformed(itemStack, p_39466_ + 1);
                Iterator<EnchantmentInstance> iterator = list.iterator();
                while (iterator.hasNext()) {
                    EnchantmentInstance next = iterator.next();
                    itemStack.enchant(next.enchantment, next.level + 3);
                    this.enchantSlots.setChanged();
                    this.enchantmentSeed.set(p_39465_.getEnchantmentSeed());
                    this.slotsChanged(this.enchantSlots);
                }
            });
        }
    }
}
