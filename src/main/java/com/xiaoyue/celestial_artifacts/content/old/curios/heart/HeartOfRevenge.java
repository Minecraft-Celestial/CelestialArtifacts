package com.xiaoyue.celestial_artifacts.content.old.curios.heart;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class HeartOfRevenge extends AttackICurio {
    public HeartOfRevenge() {
        super(new Item.Properties().rarity(IRarityUtils.GOLD));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.heart_of_revenge.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.heart_of_revenge.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.heart_of_revenge.shift3");
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        if (player.getLastAttacker() != null) {
            EntityUtils.addEct(player, MobEffects.DAMAGE_BOOST, 60, 1);
            EntityUtils.addEct(player, MobEffects.DAMAGE_RESISTANCE, 50, 0);
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(L2DamageTracker.BOW_STRENGTH.get(), new AttributeModifier(uuid, "extra arrow damage", 0.06, AttributeModifier.Operation.MULTIPLY_BASE));
        return Modifiers;
    }

    @Override
    public void onPlayerHurtEntity(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            if (player.getLastHurtByMobTimestamp() <= 60) {
                event.setAmount(event.getAmount() * 1.25f);
            }
        }
    }
}
