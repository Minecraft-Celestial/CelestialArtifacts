package com.xiaoyue.celestial_artifacts.content.old.curios.charm;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.old.generic.intf.GetCurioBreak;
import com.xiaoyue.celestial_artifacts.content.old.generic.intf.GetCurioSkill;
import com.xiaoyue.celestial_artifacts.content.old.generic.XICurioBase;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class CorruptBadge extends XICurioBase implements GetCurioSkill, GetCurioBreak {
    public CorruptBadge() {
        super(new Item.Properties().rarity(IRarityUtils.DARK_PURPLE));
    }

    @Override
    public boolean isHasCsCurio() {
        return true;
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.corrupt_badge.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.corrupt_badge.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.corrupt_badge.shift3");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.corrupt_badge.shift4");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.corrupt_badge.shift5");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.corrupt_badge.shift6");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.corrupt_badge.shift7");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.corrupt_badge.shift8",
                ChatFormatting.LIGHT_PURPLE, corrupt_badge_add * 14 + "%");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.corrupt_badge.shift9",
                ChatFormatting.LIGHT_PURPLE, corrupt_badge_add * 3 + "%");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.corrupt_badge.shift10",
                ChatFormatting.LIGHT_PURPLE, corrupt_badge_add * 9 + "%");
    }

    @Override
    public void getCurioSkill(ServerLevel level, ServerPlayer player, SlotContext context) {
        if (CurioUtils.hasCurio(player, this)) {
            if (!player.getCooldowns().isOnCooldown(this)) {
                EntityUtils.addEct(player, MobEffects.POISON, 300, 0);
                EntityUtils.addEct(player, MobEffects.UNLUCK, 300, 0);
                EntityUtils.addEct(player, MobEffects.MOVEMENT_SLOWDOWN, 300, 0);
            }
            player.getCooldowns().addCooldown(this, 1200);
        }
    }

    public int corrupt_badge_add;

    private Multimap<Attribute, AttributeModifier> createAttributeMap() {
        Multimap<Attribute, AttributeModifier> attributesDefault = HashMultimap.create();
        attributesDefault.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ToolTipUtils.getFUuid(), "cbat", corrupt_badge_add * 0.14, AttributeModifier.Operation.MULTIPLY_BASE));
        attributesDefault.put(Attributes.ATTACK_SPEED, new AttributeModifier(ToolTipUtils.getFUuid(), "cbas", corrupt_badge_add * 0.03, AttributeModifier.Operation.MULTIPLY_BASE));
        return attributesDefault;
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        player.getAttributes().addTransientAttributeModifiers(createAttributeMap());
        corrupt_badge_add = EntityUtils.getHarmfulEffect(player);
    }

    @Override
    public void takeTick(SlotContext context, Player player) {
        player.getAttributes().removeAttributeModifiers(createAttributeMap());
        corrupt_badge_add = 0;
    }

    @Override
    public void onPlayerBreak(SlotContext context, Player player, PlayerEvent.BreakSpeed event) {
        if (CurioUtils.hasCurio(player, this)) {
            int harmfulEffect = EntityUtils.getHarmfulEffect(player);
            event.setNewSpeed(event.getNewSpeed() * (1 + harmfulEffect * 0.09f));
        }
    }
}
