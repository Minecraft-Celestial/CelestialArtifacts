package com.xiaoyue.celestial_artifacts.content.curios.curse;

import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.AttrAdder;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.content.core.token.ClientTokenHelper;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.content.generic.PlayerFlagData;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

import static com.xiaoyue.celestial_artifacts.data.CALang.Curse.*;

@SerialClass
public class CatastropheScroll extends BaseTickingToken implements CAAttackToken {

	private static double getChaosBonus() {
		return CAModConfig.COMMON.curse.chaoticBlessDamageReduction.get();
	}

	private static double getChaosCurseExplosion() {
		return CAModConfig.COMMON.curse.chaoticExplosionDamage.get();
	}

	private static double getChaosCurse() {
		return CAModConfig.COMMON.curse.chaoticOtherDamage.get();
	}

	public static int getOriginTrigger() {
		return CAModConfig.COMMON.curse.originTriggerDurability.get();
	}

	public static double getOriginCurse() {
		return CAModConfig.COMMON.curse.originCurseDamage.get();
	}

	public static double getOriginBonus() {
		return CAModConfig.COMMON.curse.originBlessDamage.get();
	}

	public static double getLifeCurseHealth() {
		return CAModConfig.COMMON.curse.lifeCurseHealth.get();
	}

	public static double getLifeCurseHeal() {
		return CAModConfig.COMMON.curse.lifeCurseHeal.get();
	}

	public static double getLifeBonusHealth() {
		return CAModConfig.COMMON.curse.lifeBlessHealth.get();
	}

	public static double getLifeBonusHeal() {
		return CAModConfig.COMMON.curse.lifeBlessHeal.get();
	}

	private static double getTruthCurse() {
		return CAModConfig.COMMON.curse.truthCurseThreshold.get();
	}

	private static double getTruthBonus() {
		return CAModConfig.COMMON.curse.truthBlessThreshold.get();
	}

	public static double getNihilityCurse() {
		return CAModConfig.COMMON.curse.nihilityCurseDuration.get();
	}

	private static double getNihilityBonus() {
		return CAModConfig.COMMON.curse.nihilityBlessReduction.get();
	}

	private static int getNihilityDuration() {
		return CAModConfig.COMMON.curse.nihilityBlessDuration.get();
	}

	private static int getNihilityLevel() {
		return 3;
	}

	private static MobEffect getNihilityEffect() {
		return MobEffects.POISON;
	}

	private static double getEndCurseThreshold() {
		return CAModConfig.COMMON.curse.endCurseThreshold.get();
	}

	private static double getEndBonus() {
		return CAModConfig.COMMON.curse.endBlessHeal.get();
	}

	private static int getEndCurseDuration() {
		return CAModConfig.COMMON.curse.endCurseDuration.get();
	}

	private static int getEndLevelA() {
		return 2;
	}

	private static MobEffect getEndEffectA() {
		return MobEffects.WEAKNESS;
	}

	private static int getEndLevelB() {
		return 2;
	}

	private static MobEffect getEndEffectB() {
		return MobEffects.MOVEMENT_SLOWDOWN;
	}

	public enum Curses {
		CHAOS(CAItems.CHAOTIC_ETCHING, CHAOS_TITLE::get, CHAOS_TRIGGER::get,
				List.of(() -> CHAOS_CURSE_0.get(TextFacet.perc(getChaosCurseExplosion())),
						() -> CHAOS_CURSE_1.get(TextFacet.perc(getChaosCurse()))),
				() -> CHAOS_BONUS.get(TextFacet.perc(0.01), TextFacet.percSmall(getChaosBonus() * 0.01))),
		ORIGIN(CAItems.ORIGIN_ETCHING, ORIGIN_TITLE::get,
				() -> ORIGIN_TRIGGER.get(TextFacet.num(getOriginTrigger())),
				() -> ORIGIN_CURSE.get(TextFacet.perc(getOriginCurse())),
				() -> ORIGIN_BONUS.get(TextFacet.perc(getOriginBonus()))),
		LIFE(CAItems.LIFE_ETCHING, LIFE_TITLE::get, LIFE_TRIGGER::get,
				() -> LIFE_CURSE.get(TextFacet.perc(getLifeCurseHealth()), TextFacet.perc(getLifeCurseHeal())),
				() -> LIFE_BONUS.get(TextFacet.perc(getLifeBonusHealth()), TextFacet.perc(getLifeBonusHeal()))),
		TRUTH(CAItems.TRUTH_ETCHING, TRUTH_TITLE::get, TRUTH_TRIGGER::get,
				() -> TRUTH_CURSE.get(TextFacet.perc(getTruthCurse())),
				() -> TRUTH_BONUS.get(TextFacet.perc(getTruthBonus()))),
		DESIRE(CAItems.DESIRE_ETCHING, DESIRE_TITLE::get, DESIRE_TRIGGER::get, DESIRE_CURSE::get,
				() -> DESIRE_BONUS.get(TextFacet.num(1))),
		NIHILITY(CAItems.NIHILITY_ETCHING, NIHILITY_TITLE::get, NIHILITY_TRIGGER::get,
				() -> NIHILITY_CURSE.get(TextFacet.perc(getNihilityCurse())),
				() -> NIHILITY_BONUS.get(TextFacet.perc(getNihilityBonus()), TextFacet.num(getNihilityDuration()),
						TextFacet.num(getNihilityLevel()), TextFacet.eff(getNihilityEffect()))),
		END(CAItems.END_ETCHING, END_TITLE::get, END_TRIGGER::get,
				() -> END_CURSE.get(TextFacet.perc(getEndCurseThreshold()), TextFacet.num(getEndCurseDuration()),
						TextFacet.num(getEndLevelA()), TextFacet.eff(getEndEffectA()),
						TextFacet.num(getEndLevelB()), TextFacet.eff(getEndEffectB())),
				() -> END_BONUS.get(TextFacet.perc(getEndBonus()))),
		;

		private final Supplier<Item> etching;
		private final Supplier<MutableComponent> title, trigger, bonus;
		private final List<Supplier<MutableComponent>> curse;


		Curses(Supplier<Item> etching, Supplier<MutableComponent> title, Supplier<MutableComponent> trigger, List<Supplier<MutableComponent>> curse, Supplier<MutableComponent> bonus) {
			this.etching = etching;
			this.title = title;
			this.trigger = trigger;
			this.curse = curse;
			this.bonus = bonus;
		}

		Curses(Supplier<Item> etching, Supplier<MutableComponent> title, Supplier<MutableComponent> trigger, Supplier<MutableComponent> curse, Supplier<MutableComponent> bonus) {
			this(etching, title, trigger, List.of(curse), bonus);
		}

		private static void wrap(List<Component> list, MutableComponent comp) {
			list.add(TextFacet.wrap(comp.withStyle(ChatFormatting.GRAY)));
		}

		private static void inner(List<Component> list, MutableComponent comp) {
			list.add(TextFacet.inner(comp.withStyle(ChatFormatting.GRAY)));
		}

		private static void addText(@Nullable Level level, List<Component> list) {
			wrap(list, SCROLL_0.get());
			wrap(list, SCROLL_1.get());
			wrap(list, SCROLL_2.get());
			list.add(Component.empty());
			for (var curse : Curses.values()) {
				boolean disabled = !ClientTokenHelper.flag(level, curse.name());
				boolean bonus = ClientTokenHelper.hasCurio(level, curse.etching.get());
				list.add(TextFacet.wrap(curse.title.get().withStyle(disabled ? ChatFormatting.GRAY :
						bonus ? ChatFormatting.YELLOW : ChatFormatting.RED)));
				if (disabled) {
					inner(list, curse.trigger.get());
				} else if (bonus) {
					inner(list, curse.bonus.get());
				} else {
					for (var e : curse.curse) {
						inner(list, e.get());
					}
				}
			}
		}

		public void trigger(Player player) {
			var flags = PlayerFlagData.HOLDER.get(player);
			if (!flags.hasFlag(name())) {
				flags.addFlag(name());
				if (CurioUtils.isCsOn(player) && player instanceof ServerPlayer sp) {
					sp.sendSystemMessage(TRIGGER.get(title.get()).withStyle(ChatFormatting.RED), true);
				}
			}
		}

		public boolean cursing(Player player) {
			return PlayerFlagData.HOLDER.get(player).hasFlag(name()) &&
					CurioUtils.isCsOn(player) && !CurioUtils.hasCurio(player, etching.get());
		}

		public boolean blessing(Player player) {
			return PlayerFlagData.HOLDER.get(player).hasFlag(name()) &&
					CurioUtils.isCsOn(player) && CurioUtils.hasCurio(player, etching.get());
		}
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		Curses.addText(level, list);
	}

	private List<AttrAdder> attrs(Player player) {
		String name = "catastrophe_scroll";
		return List.of(
				AttrAdder.of(name, () -> Attributes.ATTACK_DAMAGE, AttributeModifier.Operation.ADDITION, () ->
						Curses.ORIGIN.cursing(player) ? -getOriginCurse() : Curses.ORIGIN.blessing(player) ? getOriginBonus() : 0),
				AttrAdder.of(name, () -> Attributes.MAX_HEALTH, AttributeModifier.Operation.MULTIPLY_BASE, () ->
						Curses.LIFE.cursing(player) ? -getLifeCurseHealth() : Curses.LIFE.blessing(player) ? getLifeBonusHealth() : 0),
				AttrAdder.of(name, CCAttributes.REPLY_POWER, AttributeModifier.Operation.ADDITION, () ->
						Curses.LIFE.cursing(player) ? -getLifeCurseHeal() : Curses.LIFE.blessing(player) ? getLifeBonusHeal() : 0)
		);
	}

	@Override
	protected void removeImpl(Player player) {
		attrs(player).forEach(e -> e.removeImpl(player));
	}

	@Override
	protected void tickImpl(Player player) {
		attrs(player).forEach(e -> e.tickImpl(player));
		if (player.getHealth() > player.getMaxHealth()) {
			player.setHealth(player.getMaxHealth());
		}
		if (player.tickCount % 20 == 0 && Curses.DESIRE.cursing(player)) {
			List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class,
					EntityUtils.getAABB(player, 8, 2));
			for (LivingEntity e : entities) {
				if (e.getLastHurtMobTimestamp() < e.tickCount - 20)
					e.setLastHurtMob(player);
				if (e.getLastHurtByMobTimestamp() < e.tickCount - 20)
					e.setLastHurtByMob(player);
			}
		}
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		if (Curses.END.blessing(player)) {
			player.heal((player.getMaxHealth() - player.getHealth()) * (float) getEndBonus());
		}
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		float factor = 1;
		if (Curses.CHAOS.blessing(player)) {
			float max = (float) getChaosBonus();
			factor *= 1 - max * (1 - player.getHealth() / player.getMaxHealth());
		}
		if (Curses.CHAOS.cursing(player)) {
			if (CAAttackToken.getSource(cache).is(DamageTypes.EXPLOSION)) {
				factor *= (float) (1 + getChaosCurseExplosion());
			} else {
				factor *= (float) (1 + getChaosCurse());
			}
		}
		if (Curses.NIHILITY.blessing(player)) {
			if (CAAttackToken.getSource(cache).is(DamageTypes.FELL_OUT_OF_WORLD)) {
				factor *= 1 - (float) getNihilityBonus();
			}
			if (cache.getAttacker() != null) {
				int dura = getNihilityDuration() * 20;
				EntityUtils.addEct(cache.getAttacker(), getNihilityEffect(), dura, getNihilityLevel() - 1);
			}
		}
		cache.addDealtModifier(DamageModifier.multTotal(factor));
		if (cache.getAttacker() != null) {
			if (Curses.TRUTH.blessing(player)) {
				float hp = player.getMaxHealth() * (float) getTruthBonus();
				cache.addDealtModifier(DamageModifier.nonlinearPre(345, e -> Math.min(e, hp)));
			}
			if (Curses.TRUTH.cursing(player)) {
				float hp = player.getMaxHealth() * (float) getTruthCurse();
				cache.addDealtModifier(DamageModifier.nonlinearPre(345, e -> Math.max(e, hp)));
			}
		}
	}

	@Override
	public void onPlayerDamagedFinal(Player player, AttackCache cache) {
		if (Curses.END.cursing(player)) {
			if (cache.getDamageDealt() > player.getHealth() * getEndCurseThreshold()) {
				int duration = getEndCurseDuration() * 20;
				EntityUtils.addEct(player, getEndEffectA(), duration, getEndLevelA() - 1);
				EntityUtils.addEct(player, getEndEffectB(), duration, getEndLevelB() - 1);
			}
		}
	}

}
