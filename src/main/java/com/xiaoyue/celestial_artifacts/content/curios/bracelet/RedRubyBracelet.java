package com.xiaoyue.celestial_artifacts.content.curios.bracelet;

import com.xiaoyue.celestial_artifacts.content.generic.AttackICurio;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_artifacts.utils.CurioUtiks;
import com.xiaoyue.celestial_core.utils.LevelUtils;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class RedRubyBracelet extends AttackICurio {
    public RedRubyBracelet() {
        super(new Item.Properties().rarity(IRarityUtils.RED));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.red_ruby_bracelet.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.red_ruby_bracelet.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.red_ruby_bracelet.shift3");
    }

    @Override
    public void onUnderAttack(SlotContext context, Player player, LivingAttackEvent event) {
        if (CurioUtiks.hasCurio(player, this)) {
            if (event.getSource().is(DamageTypes.HOT_FLOOR)) {
                event.setCanceled(true);
            }
        }
    }

    @Override
    public void onUnderHurt(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtiks.hasCurio(player, this)) {
            if (LevelUtils.sourceIsFire(event.getSource())) {
                event.setAmount(event.getAmount() * 0.65f);
                if (Math.random() > 0.5) {
                    EntityUtils.addEct(player, MobEffects.DAMAGE_BOOST, 60, 0);
                }
            }
        }
    }
}
