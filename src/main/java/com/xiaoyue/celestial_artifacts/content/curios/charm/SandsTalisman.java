package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.content.generic.Interface.GetCurioXp;
import com.xiaoyue.celestial_artifacts.utils.CurioUtiks;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class SandsTalisman extends AttackICurio implements GetCurioXp {
    public SandsTalisman() {
        super(new Item.Properties().rarity(Rarity.UNCOMMON));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sands_talisman.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sands_talisman.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sands_talisman.shift3");
    }

    @Override
    public int getLootingLevel(SlotContext slotContext, DamageSource source, LivingEntity target, int baseLooting, ItemStack stack) {
        return super.getLootingLevel(slotContext, source, target, baseLooting, stack) + 1;
    }

    @Override
    public void onPlayerHurtEntity(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtiks.hasCurio(player, this)) {
            float biomesTemp = player.level().getBiome(player.blockPosition()).get().getBaseTemperature() * 100;
            if (biomesTemp >= 1) {
                event.setAmount(event.getAmount() * 1.3f);
            }
        }
    }

    @Override
    public void onPlayerPickupXp(SlotContext context, Player player, PlayerXpEvent.PickupXp event) {
        if (CurioUtiks.hasCurio(player, this)) {
            player.giveExperiencePoints((int) (event.getOrb().value * 0.5));
        }
    }
}