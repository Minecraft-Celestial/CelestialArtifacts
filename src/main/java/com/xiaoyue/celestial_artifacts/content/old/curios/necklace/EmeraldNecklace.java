package com.xiaoyue.celestial_artifacts.content.old.curios.necklace;

import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.events.CEventHandler;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class EmeraldNecklace extends AttackICurio {
    public EmeraldNecklace() {
        super(new Item.Properties().rarity(IRarityUtils.GREEN));
    }

    @Override
    public boolean isHasLinkageCurio() {
        return true;
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.emerald_necklace.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.emerald_necklace.shift2");
    }

    @Override
    public void addCurioLinkageInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.emerald.alt1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.emerald.alt2");
    }

    @Override
    public void takeTick(SlotContext context, Player player) {
        if (CurioUtils.hasCurio(player, CAItems.EMERALD_RING.get(), CAItems.EMERALD_BRACELET.get())) {
            player.getAttributes().removeAttributeModifiers(CEventHandler.createEmeraldAttributeMap());
        }
    }

    @Override
    public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
        return super.getFortuneLevel(slotContext, lootContext, stack) + 1;
    }

    @Override
    public void onPlayerKillEntity(SlotContext context, Player player, LivingDeathEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            float luck = player.getLuck();
            if (Math.random() < 0.02 + (luck * 0.02)) {
                event.getEntity().spawnAtLocation(net.minecraft.world.item.Items.EMERALD);
            }
        }
    }
}
