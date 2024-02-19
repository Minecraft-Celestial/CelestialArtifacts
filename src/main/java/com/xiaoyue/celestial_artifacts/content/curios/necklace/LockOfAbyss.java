package com.xiaoyue.celestial_artifacts.content.curios.necklace;

import com.xiaoyue.celestial_artifacts.content.generic.AttackICurio;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_artifacts.utils.CurioUtiks;
import com.xiaoyue.celestial_core.utils.IAttackUtils;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class LockOfAbyss extends AttackICurio {
    public LockOfAbyss() {
        super(new Item.Properties().rarity(IRarityUtils.DARK_AQUA));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.lock_of_abyss.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.lock_of_abyss.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.lock_of_abyss.shift3");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.lock_of_abyss.shift4");
    }

    @Override
    public void onPlayerHurtEntity(SlotContext context, Player player, LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        if (CurioUtiks.hasCurio(player, this)) {
            if (entity.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                int amplifier = entity.getEffect(MobEffects.MOVEMENT_SLOWDOWN).getAmplifier();
                if (amplifier >= 7) {
                    entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
                    IAttackUtils.attackEntitySecondary(player.damageSources().fellOutOfWorld(), event.getAmount() * 2.5f, entity);
                } else {
                    EntityUtils.addEct(entity, MobEffects.MOVEMENT_SLOWDOWN, 200, amplifier + 1);
                }
            } else {
                EntityUtils.addEct(entity, MobEffects.MOVEMENT_SLOWDOWN, 200, 0);
            }
        }
    }
}
