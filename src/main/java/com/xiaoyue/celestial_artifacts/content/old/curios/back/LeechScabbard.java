package com.xiaoyue.celestial_artifacts.content.old.curios.back;

import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class LeechScabbard extends AttackICurio {
    public LeechScabbard() {
        super(new Properties().rarity(Rarity.RARE));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.leech_scabbard.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.leech_scabbard.shift2");
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        if (player.tickCount % 100 == 0) {
            EntityUtils.addEct(player, CCEffects.BLADE_MODIFIER.get(), 40, 0);
        }
    }

    @Override
    public void onPlayerMeleeEntity(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            if (player.hasEffect(CCEffects.BLADE_MODIFIER.get())) {
                player.heal(event.getAmount() * 0.25f);
            }
        }
    }
}