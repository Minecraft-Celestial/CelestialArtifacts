package com.xiaoyue.celestial_artifacts.content.curios.ring;

import com.xiaoyue.celestial_artifacts.content.generic.AttackICurio;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_artifacts.utils.CurioUtiks;
import com.xiaoyue.celestial_core.utils.LevelUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class NetheriteRing extends AttackICurio {
    public NetheriteRing() {
        super(new Properties().rarity(Rarity.RARE).fireResistant());
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.netherite_ring.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.netherite_ring.shift2");
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        if (player.tickCount % 20 == 0) {
            EntityUtils.addEct(player, MobEffects.FIRE_RESISTANCE, 200, 0);
        }
    }

    @Override
    public void onUnderHurt(SlotContext context, Player player, LivingHurtEvent event) {
        if (LevelUtils.isServerLevel(player.level())) {
            if (CurioUtiks.hasCurio(player, this)) {
                if (player.level().dimension().equals(Level.NETHER)) {
                    event.setAmount(event.getAmount() * 0.9f);
                }
            }
        }
    }
}
