package com.xiaoyue.celestial_artifacts.content.curios.bracelet;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.gossip.GossipType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CharmingBracelet implements MultiLineText, CAAttackToken {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {//TODO text
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.charming_bracelet.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.charming_bracelet.shift3");
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		Item self = CAItems.CHARMING_BRACELET.get();
		if (player.getCooldowns().isOnCooldown(self)) return;
		if (CAAttackToken.getSource(cache).getEntity() instanceof LivingEntity le) {
			List<LivingEntity> entities = EntityUtils.getExceptForCentralEntity(player, 6, 2);
			for (LivingEntity list : entities) {
				list.setLastHurtByMob(le);
				list.setLastHurtMob(le);
			}
			player.getCooldowns().addCooldown(self, 200);
		}
	}

	@Override
	public void onPlayerKill(Player player, LivingDeathEvent event) {
		if (event.getEntity() instanceof Zombie) {
			List<Villager> entities = player.level().getEntitiesOfClass(Villager.class, EntityUtils.getAABB(player, 6, 2));
			for (Villager list : entities) {
				list.getGossips().add(player.getUUID(), GossipType.MAJOR_POSITIVE, 1);
			}
		}
	}

}
