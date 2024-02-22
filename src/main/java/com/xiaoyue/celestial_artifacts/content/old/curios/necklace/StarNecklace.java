package com.xiaoyue.celestial_artifacts.content.old.curios.necklace;

import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.LevelUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class StarNecklace extends AttackICurio {
    public StarNecklace() {
        super(new Item.Properties().rarity(Rarity.RARE));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.star_necklace.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.star_necklace.shift2");
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        if (LevelUtils.isServerLevel(player.level())) {
            if (player.level().isNight()) {
                EntityUtils.addEct(player, MobEffects.DAMAGE_RESISTANCE, 100 ,0);
            }
        }
    }

    @Override
    public void onPlayerHurtEntity(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            if (EntityUtils.isLookingBehindTarget(event.getEntity(), player.getEyePosition()) && player.level().isNight()) {
                event.setAmount(event.getAmount() * 1.4f);
            }
        }
    }
}
