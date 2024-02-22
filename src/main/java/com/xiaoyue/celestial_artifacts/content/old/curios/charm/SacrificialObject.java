package com.xiaoyue.celestial_artifacts.content.old.curios.charm;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class SacrificialObject extends AttackICurio {
    public SacrificialObject() {
        super(new Item.Properties().rarity(Rarity.EPIC));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sacrificial_object.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sacrificial_object.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sacrificial_object.shift3");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sacrificial_object.shift4");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(CCAttributes.DAMAGE_REDUCTION.get(), new AttributeModifier(uuid, "soedr", 0.05, AttributeModifier.Operation.ADDITION));
        return Modifiers;
    }

    @Override
    public int getLootingLevel(SlotContext slotContext, DamageSource source, LivingEntity target, int baseLooting, ItemStack stack) {
        return super.getLootingLevel(slotContext, source, target, baseLooting, stack) + 1;
    }

    @Override
    public void onPlayerDeath(SlotContext context, Player player, LivingDeathEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            if (player.getRandom().nextFloat() < 0.5) {
                player.spawnAtLocation(Items.GOLD_INGOT);
            }
            List<LivingEntity> entities = EntityUtils.getDelimitedMonster(player, 8);
            entities.remove(player);
            for (LivingEntity list : entities) {
                if (list.getMaxHealth() < player.getMaxHealth()) {
                    if (list.getRandom().nextFloat() < 0.5) {
                        list.kill();
                    }
                }
            }
        }
    }
}
