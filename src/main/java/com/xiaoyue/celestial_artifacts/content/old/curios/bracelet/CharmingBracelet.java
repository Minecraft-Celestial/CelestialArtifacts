package com.xiaoyue.celestial_artifacts.content.old.curios.bracelet;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.gossip.GossipType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class CharmingBracelet extends AttackICurio {
    public CharmingBracelet() {
        super(new Item.Properties().rarity(Rarity.RARE));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.charming_bracelet.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.charming_bracelet.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.charming_bracelet.shift3");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(Attributes.ARMOR, new AttributeModifier(uuid, "exar", 2, AttributeModifier.Operation.ADDITION));
        return Modifiers;
    }

    @Override
    public void onUnderHurt(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            if (player.getCooldowns().isOnCooldown(this)) return;
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
                List<LivingEntity> entities = EntityUtils.getExceptForCentralEntity(player, 6, 2);
                for (LivingEntity list : entities) {
                    list.setLastHurtByMob(livingEntity);
                    list.setLastHurtMob(livingEntity);
                }
                player.getCooldowns().addCooldown(this, 200);
            }
        }
    }

    @Override
    public void onPlayerKillEntity(SlotContext context, Player player, LivingDeathEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            if (event.getEntity() instanceof Zombie) {
                List<Villager> entities = player.level().getEntitiesOfClass(Villager.class, EntityUtils.getAABB(player, 6, 2));
                for (Villager list : entities) {
                    list.getGossips().add(player.getUUID(), GossipType.MAJOR_POSITIVE, 1);
                }
            }
        }
    }
}
