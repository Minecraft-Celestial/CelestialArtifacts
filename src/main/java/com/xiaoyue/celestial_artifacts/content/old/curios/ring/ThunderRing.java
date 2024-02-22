package com.xiaoyue.celestial_artifacts.content.old.curios.ring;

import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class ThunderRing extends AttackICurio {
    public ThunderRing() {
        super(new Item.Properties().rarity(Rarity.RARE));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.thunder_ring.shift1");
    }

    @Override
    public void onUnderAttack(SlotContext context, Player player, LivingAttackEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            if (event.getSource().is(DamageTypes.LIGHTNING_BOLT)) {
                event.setCanceled(true);
            }
        }
    }
}