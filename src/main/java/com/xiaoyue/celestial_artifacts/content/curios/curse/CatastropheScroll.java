package com.xiaoyue.celestial_artifacts.content.curios.curse;

import com.xiaoyue.celestial_artifacts.content.core.effect.EffectFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.ModularCurio;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.*;
import com.xiaoyue.celestial_artifacts.content.loot.EnabledCondition;
import com.xiaoyue.celestial_artifacts.content.loot.HasCurioCondition;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.data.CATagGen;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.content.generic.PlayerFlagData;
import com.xiaoyue.celestial_core.content.loot.PlayerFlagCondition;
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
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.predicates.AllOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.xiaoyue.celestial_artifacts.data.CALang.Curse.*;

@SerialClass
public class CatastropheScroll extends BaseTickingToken implements CAAttackToken {

	public static final TokenFacet<CatastropheScroll> TOKEN = new TokenFacet<>("catastrophe_scroll", CatastropheScroll::new);

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
		return CAModConfig.COMMON.curse.truthCurseMinDamage.get();
	}

	private static double getTruthBonus() {
		return CAModConfig.COMMON.curse.truthBlessMaxDamage.get();
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

	private static MobEffectInstance getNihilityEffect() {
		return new MobEffectInstance(MobEffects.POISON, getNihilityDuration() * 20, 2);
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


	private static MobEffectInstance getEndEffectA() {
		return new MobEffectInstance(MobEffects.WEAKNESS, getEndCurseDuration() * 20, 1);
	}

	private static MobEffectInstance getEndEffectB() {
		return new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, getEndCurseDuration() * 20, 1);
	}

	public static List<Component> addInfo() {
		List<Component> list = new ArrayList<>();
		for (var curse : Curses.values()) {
			list.add(curse.title.get());
			list.add(TextFacet.wrap(TRIGGER_COND.get().append(curse.trigger.get().withStyle(ChatFormatting.YELLOW))));
			if (curse.curse.size() == 1) {
				list.add(TextFacet.wrap(CURSE_EFF.get().append(curse.curse.get(0).get().withStyle(ChatFormatting.RED))));
			} else {
				list.add(TextFacet.wrap(CURSE_EFF.get()));
				for (var e : curse.curse) {
					list.add(TextFacet.inner(e.get().withStyle(ChatFormatting.RED)));
				}
			}
			list.add(TextFacet.wrap(BLESS_EFF.get().append(curse.bonus.get().withStyle(ChatFormatting.GREEN))));
		}
		return list;
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		Curses.addText(level, list);
	}

	private List<AttrAdder> attrs(Player player) {
		String name = "catastrophe_scroll";
		return List.of(
				AttrAdder.of(name, () -> Attributes.ATTACK_DAMAGE, AttributeModifier.Operation.MULTIPLY_TOTAL, () ->
						Curses.ORIGIN.cursing(player) ? -getOriginCurse() : Curses.ORIGIN.blessing(player) ? getOriginBonus() : 0),
				AttrAdder.of(name, () -> Attributes.MAX_HEALTH, AttributeModifier.Operation.MULTIPLY_TOTAL, () ->
						Curses.LIFE.cursing(player) ? -getLifeCurseHealth() : Curses.LIFE.blessing(player) ? getLifeBonusHealth() : 0),
				AttrAdder.of(name, CCAttributes.REPLY_POWER::get, AttributeModifier.Operation.ADDITION, () ->
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
				if (e.getType().is(CATagGen.DESIRE_BLACKLIST))
					continue;
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
				cache.getAttacker().addEffect(getNihilityEffect());
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
				cache.addDealtModifier(DamageModifier.nonlinearPre(345, e -> e <= 0 ? 0 : Math.max(e, hp)));
			}
		}
	}

	@Override
	public void onPlayerDamagedFinal(Player player, AttackCache cache) {
		if (Curses.END.cursing(player)) {
			if (cache.getDamageDealt() >= player.getMaxHealth() * getEndCurseThreshold()) {
				player.addEffect(getEndEffectA());
				player.addEffect(getEndEffectB());
			}
		}
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
				() -> NIHILITY_BONUS.get(TextFacet.perc(getNihilityBonus()), EffectFacet.getDesc(getNihilityEffect()))),
		END(CAItems.END_ETCHING, END_TITLE::get, END_TRIGGER::get,
				() -> END_CURSE.get(TextFacet.perc(getEndCurseThreshold()),
						EffectFacet.getDesc(getEndEffectA()),
						EffectFacet.getDesc(getEndEffectB())),
				() -> END_BONUS.get(TextFacet.perc(getEndBonus()))),
		;

		public final Supplier<MutableComponent> title, trigger, bonus;
		private final Supplier<? extends Item> etching;
		private final List<Supplier<MutableComponent>> curse;


		Curses(Supplier<? extends Item> etching, Supplier<MutableComponent> title, Supplier<MutableComponent> trigger, List<Supplier<MutableComponent>> curse, Supplier<MutableComponent> bonus) {
			this.etching = etching;
			this.title = title;
			this.trigger = trigger;
			this.curse = curse;
			this.bonus = bonus;
		}

		Curses(Supplier<? extends Item> etching, Supplier<MutableComponent> title, Supplier<MutableComponent> trigger, Supplier<MutableComponent> curse, Supplier<MutableComponent> bonus) {
			this(etching, title, trigger, List.of(curse), bonus);
		}

		private static void wrap(List<Component> list, MutableComponent comp) {
			list.add(TextFacet.wrap(comp.withStyle(ChatFormatting.GRAY)));
		}

		private static void inner(List<Component> list, MutableComponent comp) {
			list.add(TextFacet.inner(comp.withStyle(ChatFormatting.GRAY)));
		}

		private static void addText(@Nullable Level level, List<Component> list) {
			if (CAModConfig.COMMON.misc.catastropheScrollPreventUnequip.get())
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

		public LootItemCondition asCondition() {
			return AllOfCondition.allOf(
					new EnabledCondition((ModularCurio) etching.get()),
					new HasCurioCondition(CAItems.CATASTROPHE_SCROLL.get()),
					() -> new PlayerFlagCondition(name()),
					new HasCurioCondition(etching.get()).invert()).build();
		}

	}

}
