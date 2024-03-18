package com.xiaoyue.celestial_artifacts.content.curios.bracelet;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
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

	private static int reputation() {
		return CAModConfig.COMMON.bracelet.charmingBraceletReputationBonus.get();
	}

	private static int cd() {
		return CAModConfig.COMMON.bracelet.charmingBraceletCooldown.get();
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Bracelet.CHARM_0.get(TextFacet.num(reputation()))));
		list.add(TextFacet.wrap(CALang.Bracelet.CHARM_1.get(TextFacet.num(cd()))));
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		Item self = CAItems.CHARMING_BRACELET.get();
		if (player.getCooldowns().isOnCooldown(self)) return;
		if (CAAttackToken.getSource(cache).getEntity() instanceof LivingEntity le) {
			List<LivingEntity> entities = EntityUtils.getExceptForCentralEntity(player, 6, 2);
			for (LivingEntity e : entities) {
				e.setLastHurtByMob(le);
				e.setLastHurtMob(le);
				if (e instanceof Mob mob) mob.setTarget(le);
			}
			player.getCooldowns().addCooldown(self, cd() * 20);
		}
	}

	@Override
	public void onPlayerKill(Player player, LivingDeathEvent event) {
		if (event.getEntity() instanceof Zombie) {
			List<Villager> entities = player.level().getEntitiesOfClass(Villager.class, EntityUtils.getAABB(player, 6, 2));
			for (Villager v : entities) {
				v.getGossips().add(player.getUUID(), GossipType.MAJOR_POSITIVE, 1);
			}
		}
	}

}
