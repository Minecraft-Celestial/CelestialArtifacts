package com.xiaoyue.celestial_artifacts.events;

import com.xiaoyue.celestial_artifacts.content.curios.charm.CursedTotem;
import com.xiaoyue.celestial_artifacts.content.curios.charm.GluttonyBadge;
import com.xiaoyue.celestial_artifacts.content.curios.charm.SacrificialObject;
import com.xiaoyue.celestial_artifacts.content.curios.curse.CatastropheScroll;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.data.CATagGen;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.events.DamageItemEvent;
import com.xiaoyue.celestial_core.utils.ItemUtils;
import dev.xkmc.l2library.base.effects.EffectBuilder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.event.CurioEquipEvent;

import java.util.List;

import static com.xiaoyue.celestial_artifacts.CelestialArtifacts.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CAMiscCuriosHandler {

	public static final String COPPER_REF = "CelestialCore_copperReinforce";
	public static final String AMETHYST_REF = "CelestialCore_amethystReinforce";

	@SubscribeEvent
	public static void anvilRecipe(AnvilUpdateEvent event) {
		ItemStack left = event.getLeft().copy();
		ItemStack right = event.getRight();
		if (!left.isDamageableItem()) return;
		if (right.is(CAItems.COPPER_REINFORCE_PLATE.get())) {
			left.getOrCreateTag().putBoolean(COPPER_REF, true);
			ItemUtils.defaultAnvilOutput(event, left);
		}
		if (right.is(CAItems.AMETHYST_REINFORCE_PLATE.get())) {
			left.getOrCreateTag().putBoolean(AMETHYST_REF, true);
			ItemUtils.defaultAnvilOutput(event, left);
		}
	}

	@SubscribeEvent
	public static void onItemDamage(DamageItemEvent event) {
		ItemStack stack = event.getStack();
		if (!stack.hasTag()) return;
		if (stack.getTag().getBoolean(COPPER_REF)) {
			Double chance = CAModConfig.COMMON.misc.copperReinforceChance.get();
			event.setAmount(ItemUtils.calculateRef(event.getRandom(), event.getAmount(), chance));
		}
		if (stack.getTag().getBoolean(AMETHYST_REF)) {
			event.setAmount(Math.min(event.getAmount(), CAModConfig.COMMON.misc.amethystReinforceEffect.get()));
		}
	}

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
	}

	@SubscribeEvent(priority = EventPriority.LOW)
	public static void onEnderManAnger(EnderManAngerEvent event) {
		if (event.getPlayer() instanceof ServerPlayer sp && sp.hasLineOfSight(event.getEntity()))
			CatastropheScroll.Curses.END.trigger(sp);
	}

	@SubscribeEvent
	public static void onEquipEtching(CurioEquipEvent event) {
		if (event.getEntity() instanceof ServerPlayer sp && event.getStack().is(CATagGen.ETCHINGS)) {
			CatastropheScroll.Curses.TRUTH.trigger(sp);
		}
	}

	@SubscribeEvent
	public static void onArrowHit(ProjectileImpactEvent event) {
		Projectile projectile = event.getProjectile();
		Entity entity = event.getEntity();
		if (projectile instanceof AbstractArrow arrow) {
			if (arrow.getOwner() instanceof Player player) {
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
		new EffectBuilder(instance).setDuration((int) (instance.getDuration() * (1 + factor)));
	}

	@SubscribeEvent
	public static void onFinishItemUse(LivingEntityUseItemEvent.Finish event) {
		ItemStack itemStack = event.getItem();
		LivingEntity entity = event.getEntity();
		if (entity instanceof Player player) {
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
					event.setDuration(event.getDuration() - 1);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onUseTotem(LivingUseTotemEvent event) {
		LivingEntity entity = event.getEntity();
		Entity attacker = event.getSource().getEntity();
		if (entity instanceof Player player) {
			if (CurioUtils.hasCurio(player, CAItems.CURSED_TOTEM.get())) {
				if (attacker instanceof LivingEntity livingEntity) {
					livingEntity.addEffect(CursedTotem.eff());
				}
			}
		}
	}

	@SubscribeEvent
	public static void onChangeLevel(PlayerEvent.PlayerChangedDimensionEvent event) {
		Player entity = event.getEntity();
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
