package com.xiaoyue.celestial_artifacts.content.curios.scroll;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.Tags;

public class TwistedScroll implements TickFacet, SingleLineText {

	private static final String TAG = "celestial_artifacts_duplicated";

	private static double chance() {
		return CAModConfig.COMMON.scroll.twistedDuplicateChance.get();
	}

	@Override
	public MutableComponent getLine() {
		return CALang.Scroll.TWIST.get(TextFacet.perc(chance()));
	}

	@Override
	public void tick(LivingEntity entity, ItemStack stack) {
		if (entity.tickCount % 20 == 0) {
			for (var e : EntityUtils.getExceptForCentralEntity(entity, 24, 8, e -> e instanceof Enemy)) {
				if (e.getType().is(Tags.EntityTypes.BOSSES)) continue;
				if (!e.getTags().contains(TAG)) {
					e.addTag(TAG);
					if (CAAttackToken.chance(entity, chance())) {
						add(e);
					}
				}
			}
		}
	}

	private void add(LivingEntity entity) {
		var level = entity.level();
		LivingEntity e0 = (LivingEntity) entity.getType().create(level);
		assert e0 != null;
		e0.addTag(TAG);
		e0.copyPosition(entity);
		level.addFreshEntity(e0);
	}

}
