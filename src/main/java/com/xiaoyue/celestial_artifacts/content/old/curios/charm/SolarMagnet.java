package com.xiaoyue.celestial_artifacts.content.old.curios.charm;

import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.LevelUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class SolarMagnet extends AttackICurio {
    public SolarMagnet() {
        super(new Item.Properties().rarity(Rarity.RARE));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.solar_magnet.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.solar_magnet.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.solar_magnet.shift3");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.solar_magnet.shift4");
    }

    public void getAttractingItems(Player player) {
        Level level = player.level();
        if (LevelUtils.isServerLevel(level)) {
            if (CurioUtils.hasCurio(player, this)) {
                List<ItemEntity> entities = level.getEntitiesOfClass(ItemEntity.class, player.getBoundingBox().inflate(5));
                for (ItemEntity list : entities) {
                    list.teleportTo(player.getX(), player.getY(), player.getZ());
                }
            }
        }
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        if (LevelUtils.isServerLevel(player.level())) {
            this.getAttractingItems(player);
            if (player.level().isDay()) {
                if (player.isOnFire()) {
                    player.clearFire();
                }
                if (!player.getCooldowns().isOnCooldown(this)) {
                    if (player.getLastHurtMob() != null) {
                        player.getLastHurtMob().setSecondsOnFire(40);
                        player.getCooldowns().addCooldown(this, 50);
                    }
                }
            }
        }
    }

    @Override
    public void onPlayerHurtEntity(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            if (event.getEntity().getMobType() == MobType.UNDEAD) {
                event.setAmount(event.getAmount() * 1.25f);
            }
        }
    }
}
