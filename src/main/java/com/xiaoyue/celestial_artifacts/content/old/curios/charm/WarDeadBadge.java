package com.xiaoyue.celestial_artifacts.content.old.curios.charm;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.old.curios.CatastropheScroll;
import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class WarDeadBadge extends AttackICurio {
    public WarDeadBadge() {
        super(new Item.Properties().rarity(IRarityUtils.DARK_PURPLE));
    }

    @Override
    public boolean isHasCsCurio() {
        return true;
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift3");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift4");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift5");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift6",
                ChatFormatting.LIGHT_PURPLE, war_dead_badge_add * 200 + "%");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift7",
                ChatFormatting.LIGHT_PURPLE, war_dead_badge_add * 200 + "%");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift8",
                ChatFormatting.LIGHT_PURPLE, war_dead_badge_add * 100 + "%");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift9");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift10");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift11");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift12");
    }

    public float war_dead_badge_add;

    private Multimap<Attribute, AttributeModifier> createAttributeMap() {
        Multimap<Attribute, AttributeModifier> attributesDefault = HashMultimap.create();
        attributesDefault.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ToolTipUtils.getFUuid(), "wdbead", ToolTipUtils.getNoZeroValue(war_dead_badge_add * 2), AttributeModifier.Operation.MULTIPLY_BASE));
        attributesDefault.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(ToolTipUtils.getFUuid(), "wdbeat", ToolTipUtils.getNoZeroValue(war_dead_badge_add * 2), AttributeModifier.Operation.MULTIPLY_BASE));
        attributesDefault.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(ToolTipUtils.getFUuid(), "wdbems", ToolTipUtils.getNoZeroValue(war_dead_badge_add * 1), AttributeModifier.Operation.MULTIPLY_BASE));
        return attributesDefault;
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        war_dead_badge_add = (100 - (player.getHealth() / player.getMaxHealth() * 100)) / 100;
        player.getAttributes().addTransientAttributeModifiers(createAttributeMap());
    }

    @Override
    public void takeTick(SlotContext context, Player player) {
        player.getAttributes().removeAttributeModifiers(createAttributeMap());
        war_dead_badge_add = 0;
    }

    @Override
    public void onPlayerHurtEntity(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            if (CatastropheScroll.i_chaotic == 0) {
                if (player.getHealth() < player.getMaxHealth() * 0.1f) {
                    List<LivingEntity> entities = EntityUtils.getExceptForCentralEntity(player, 8, 2);
                    player.heal((player.getMaxHealth() - player.getHealth()) * 0.01f * entities.size());
                }
            }
        }
    }
}
