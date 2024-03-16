package com.xiaoyue.celestial_artifacts.events;

import com.xiaoyue.celestial_artifacts.content.curios.charm.CursedTotem;
import com.xiaoyue.celestial_artifacts.content.curios.charm.GluttonyBadge;
import com.xiaoyue.celestial_artifacts.content.curios.charm.SacrificialObject;
import com.xiaoyue.celestial_artifacts.content.curios.curse.CatastropheScroll;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import dev.xkmc.l2library.base.effects.EffectBuilder;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSpawnPhantomsEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.xiaoyue.celestial_artifacts.CelestialArtifacts.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CAMiscCuriosHandler {

	@SubscribeEvent
	public static void playerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase != TickEvent.Phase.END) return;
		Player player = event.player;
		Level level = player.level();
		if (level.isClientSide()) return;
		ItemStack stack = player.getMainHandItem();
		if (stack.isDamageableItem() && stack.getMaxDamage() >= CatastropheScroll.getOriginTrigger()) {
			CatastropheScroll.Curses.ORIGIN.trigger(player);
		}
		for (var e : player.getArmorSlots()) {
			if (e.isEnchanted()) {
				CatastropheScroll.Curses.CHAOS.trigger(player);
				break;
			}
		}
		if (!level.isNight() || player.isSleeping()) return;
		float time = level.getTimeOfDay(1);
		if (0.49 < time && time < 0.51) {
			CatastropheScroll.Curses.END.trigger(player);
		}

	}

	@SubscribeEvent
	public static void onArrowHit(ProjectileImpactEvent event) {
		Projectile projectile = event.getProjectile();
		Entity entity = event.getEntity();
		if (projectile instanceof AbstractArrow arrow) {
			if (arrow.getOwner() instanceof Player player) {
				// 火焰箭袋
				if (CurioUtils.hasCurio(player, CAItems.FLAME_ARROW_BAG.get())) {
					entity.setSecondsOnFire(CAModConfig.COMMON.back.flameArrowBagTime.get());
				}
			}
		}
	}

	@SubscribeEvent
	public static void onAddedEffect(MobEffectEvent.Added event) {
		MobEffectInstance instance = event.getEffectInstance();
		if (!(event.getEntity() instanceof Player player)) return;
		if (instance.getEffect().isBeneficial())
			CatastropheScroll.Curses.NIHILITY.trigger(player);
		if (instance.getEffect().getCategory() != MobEffectCategory.HARMFUL) return;
		if (!CatastropheScroll.Curses.NIHILITY.cursing(player)) return;
		double factor = CatastropheScroll.getNihilityCurse();
		new EffectBuilder(instance).setDuration((int) (instance.getDuration() * factor));

	}

	@SubscribeEvent
	public static void onFinishItemUse(LivingEntityUseItemEvent.Finish event) {
		ItemStack itemStack = event.getItem();
		LivingEntity entity = event.getEntity();
		if (entity instanceof Player player) {
			// 暴食徽章
			if (CurioUtils.hasCurio(player, CAItems.GLUTTONY_BADGE.get())) {
				if (itemStack.getUseAnimation() == UseAnim.EAT) {
					player.addEffect(GluttonyBadge.effAtk());
					player.addEffect(GluttonyBadge.effReg());
				}
			}
		}
	}

	@SubscribeEvent
	public static void onStarItemUse(LivingEntityUseItemEvent.Tick event) {
		ItemStack itemStack = event.getItem();
		LivingEntity entity = event.getEntity();
		if (entity instanceof Player player) {
			if (CurioUtils.hasCurio(player, CAItems.SPIRIT_BRACELET.get())) {
				if (CurioUtils.isRangeUseAnim(itemStack.getUseAnimation())) {
					event.setDuration(event.getDuration() + 1);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onUseTotem(LivingUseTotemEvent event) {
		LivingEntity entity = event.getEntity();
		Entity attacker = event.getSource().getEntity();
		if (entity instanceof Player player) {
			// 被咒者的图腾
			if (CurioUtils.hasCurio(player, CAItems.CURSED_TOTEM.get())) {
				if (attacker instanceof LivingEntity livingEntity) {
					livingEntity.addEffect(CursedTotem.eff());
				}
			}
		}
	}

	@SubscribeEvent
	public static void onSpawnPhantom(PlayerSpawnPhantomsEvent event) {
		Player player = event.getEntity();
		// 怨影吊坠 TODO
		if (CurioUtils.hasCurio(player, CAItems.SHADOW_PENDANT.get())) {
			event.setResult(Event.Result.DENY);
		}
	}

	@SubscribeEvent
	public static void onSpawnEntity(MobSpawnEvent.FinalizeSpawn event) {
		Mob entity = event.getEntity();
		// 扭曲卷轴 TODO
		if (!entity.level().isClientSide() && entity instanceof Monster) {
			List<Player> entities = entity.level().getEntitiesOfClass(Player.class, EntityUtils.getAABB(entity, 6, 2));
			for (Player list : entities) {
				if (CurioUtils.hasCurio(list, CAItems.TWISTED_SCROLL.get())) {
					if (list.getRandom().nextDouble() < 0.6) {
						entity.level().addFreshEntity(entity);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onChangeLevel(PlayerEvent.PlayerChangedDimensionEvent event) {
		Player entity = event.getEntity();
		// 旅行者卷轴
		if (CurioUtils.hasCurio(entity, CAItems.TRAVELER_SCROLL.get())) {
			entity.addEffect(CAModConfig.COMMON.scroll.travelerScrollSpeedEffect());
			entity.addEffect(CAModConfig.COMMON.scroll.travelerScrollRegenEffect());
		}
	}

	@SubscribeEvent
	public static void onLivingDeath(LivingDeathEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CurioUtils.hasCurio(player, CAItems.SACRIFICIAL_OBJECT.get())) {
				SacrificialObject.onPlayerDeath(player);
			}
		}
	}

	public static void onEnchTable(@Nullable Slot slot, List<EnchantmentInstance> original) {
		if (slot != null && slot.container instanceof Inventory inv) {
			Player player = inv.player;
			int lv = CAModConfig.COMMON.pendant.chaoticPendantEnchantLevel.get();
			if (CurioUtils.hasCurio(player, CAItems.CHAOTIC_PENDANT.get())) {
				for (int i = 0; i < original.size(); i++) {
					var ins = original.get(i);
					int ilv = Math.max(ins.level, Math.min(ins.enchantment.getMaxLevel(), ins.level + lv));
					original.set(i, new EnchantmentInstance(ins.enchantment, ilv));
				}
			}
		}
	}
}
