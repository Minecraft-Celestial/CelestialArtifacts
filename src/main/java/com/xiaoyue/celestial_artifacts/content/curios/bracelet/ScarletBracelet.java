package com.xiaoyue.celestial_artifacts.content.curios.bracelet;

import com.xiaoyue.celestial_artifacts.content.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.utils.CurioUtiks;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class ScarletBracelet extends AttackICurio {
    public ScarletBracelet() {
        super(new Item.Properties().rarity(IRarityUtils.RED));
    }

    @Override
    public void addCurioLinkageInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.scarlet_bracelet.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.scarlet_bracelet.shift2");
    }

    @Override
    public void onPlayerHurtEntity(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtiks.hasCurio(player, this)) {
            if (player.getHealth() > player.getMaxHealth() * 0.5f) {
                float i = player.getHealth() - player.getMaxHealth() * 0.5f;
                float addDamage = i * (event.getEntity().getMaxHealth() * 0.0001f);
                float min = Math.min(event.getEntity().getMaxHealth() * 0.5f, event.getAmount() * (1 + addDamage));
                player.setHealth(player.getMaxHealth() * 0.5f);
                event.setAmount(min);
            }
        }
    }
}
