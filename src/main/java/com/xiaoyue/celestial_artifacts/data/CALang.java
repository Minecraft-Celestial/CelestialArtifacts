package com.xiaoyue.celestial_artifacts.data;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import dev.xkmc.l2damagetracker.init.data.L2DamageTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Locale;
import java.util.function.Predicate;

public class CALang {

	private static final HashMap<Class<?>, EnumEntry> MAP = new HashMap<>();

	static {
		putLang(Tooltip.class, "tooltip", Tooltip.values());
		putLang(JEIInfo.class, "info", JEIInfo.values());
		putLang(CALootTableGen.class, "loot", CALootTableGen.values());
		putLang(Modular.class, "modular", Modular.values());
		putLang(Condition.class, "condition", Condition.values());
		putLang(DamageTypes.class, "damage_type", DamageTypes.values());
		putLang(Back.class, "back", Back.values());
		putLang(Bracelet.class, "bracelet", Bracelet.values());
		putLang(Charm.class, "charms", Charm.values());
		putLang(Curse.class, "curse", Curse.values());
		putLang(Head.class, "head", Head.values());
		putLang(Heart.class, "heart", Heart.values());
		putLang(Necklace.class, "necklace", Necklace.values());
		putLang(Pendant.class, "pendant", Pendant.values());
		putLang(Ring.class, "ring", Ring.values());
		putLang(Scroll.class, "scroll", Scroll.values());
		putLang(Sets.class, "sets", Sets.values());
	}

	@SafeVarargs
	private static <T extends Info> void putLang(Class<T> cls, String str, T... vals) {
		MAP.put(cls, new EnumEntry(str, vals));
	}

	public static void addLang(RegistrateLangProvider pvd) {
		for (var ent : MAP.values()) {
			for (var e : ent.info()) {
				pvd.add(e.desc(), e.entry().def());
			}
		}
		for (var type : CASlotGen.Type.values()) {
			pvd.add("curios.identifier." + type.id(), "Celestial - " + RegistrateLangProvider.toEnglishName(type.id()));
			pvd.add("curios.modifiers." + type.id(), "When wearing as " + RegistrateLangProvider.toEnglishName(type.id()) + ": ");
		}
		pvd.add("key.celestial_artifacts.ability_key", "Activate Curio Effects");
		pvd.add("key.category.celestial_artifacts.celestial_artifacts", "Celestial Artifacts");
	}

	public static MutableComponent translate(String key, Object... objs) {
		return Component.translatable(key, objs);
	}

	public enum Tooltip implements Info {
		END_DUST("%2$s chance to drop when players equipped with [%1$s] kill monsters targeting them", 2),
		NEBULA_CUBE("Dropped when player failed to get etchings while meeting conditions", 0),
		BACKTRACK("Teleport player to spawning point. Cool down: %s seconds", 1),
		REPENT("Teleport player to last death position. Cool down: %s seconds", 1),
		PURIFIED("Remove curse on off hand item", 0),
		AMETHYST_REINFORCE_PLATE("Reinforced item may lose at most %s durability at a time", 1),
		COPPER_REINFORCE_PLATE("Reinforced item has %s resistance to durability loss", 1),
		REINFORCE_PLATE_USED("Reinforced %s", 1),
		CAN_STORAGE_POTION("Able to store potions", 0),
		POTIONS_BAG_INFO("Sneak right click with the bag to consume potions that is not active on player", 0),
		GAIA_TOTEM("Triggers when dying and gives the holder %s for a few seconds", 1),
		CURRENT_POS("Current pos: %s %s %s", 3),
		CURRENT_LEVEL("Current level: %s", 1),
		CURRENT_CHARGING("Current charging: %s", 1),
		SCEPTER_CHARGING("Use [%s] to right click scepter in inventory to recharge", 1),
		ENDER_JUMP_SCEPTER("Right click on a block to bind position, and right-click a mob to teleport it to the bound position", 0),
		BAN("This item is disabled", 0);

		final Entry entry;

		Tooltip(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		@Override
		public Entry entry() {
			return entry;
		}

	}

	public enum JEIInfo implements Info {
		FIND("Can be found in %s", 1),
		CURSE("%s chance to drop when player bearing %s Curse kills %s", 3),
		CURSE_BY("%s chance to drop when %s is killed by %s", 3),
		DESIRE("%s with %s or higher looting", 2),
		END("%s while having %s or more harmful effects", 2),
		ORIGIN("%s at y=200 or higher", 1),
		LIFE("mobs with %s or higher health", 1),
		NIHILITY("%s with abyss damage", 1),
		CHAOTIC("explosion damage", 0),
		TRUTH("raiders", 0),

		CURSE_DROP("With %s, %s chance to drop when player kills %s", 3),
		CHARMING("When player with %s or higher reputation kills villager: %s chance to drop %s, otherwise drops %s", 4),
		WITHER_KILL("%s chance to drop when %s kills %s", 3),
		;

		final Entry entry;

		JEIInfo(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		@Override
		public Entry entry() {
			return entry;
		}

		@Override
		public MutableComponent get(MutableComponent... objs) {
			for (var e : objs) e.setStyle(Style.EMPTY);
			return Info.super.get(objs);
		}


	}

	public enum Modular implements Info {
		EFFECT_REFRESH("Grants wearer: ", 0),
		EFFECT_FLASH("For every %s seconds, grants wearer: ", 1),
		EFFECT_INFLICT("On hit targets, inflicts: ", 0),
		EFFECT_INFLICT_CHANCE("On hit targets, %s chance to inflict: ", 1),
		EFFECT_HURT("On hurt, grants: ", 0),
		EFFECT_HURT_CHANCE("On hurt, %s chance to grant: ", 1),
		FORTUNE("Fortune", 0),
		LOOT("Looting", 0),
		XP("Xp Gain", 0),
		ENDER_MASK("Looking at endermen will not aggravate them", 0),
		IMMUNE("This item cannot be destroyed", 0),
		CURSE("Requires %s to be equipped", 1),
		SHIFT("Press [%s] to display curio effects", 1),
		ALT("Press [%s] to display set effects", 1),
		INVUL_TIME("Invulnerable time", 0),
		HURT_BONUS("Increase damage by %s", 1),
		JUMP_POWER("Jump Power", 0),
		PROTECT("Reduce damage by %s", 1),
		PROTECT_TYPE("Reduce incoming %s damage by %s", 2),
		DODGE_TYPE("%s chance to dodge %s damage", 2),
		NEGATE_TYPE("Negates %s damage", 1),
		COMMA(", ", 0),
		SET("Requires [%s] to take effect:", 1),
		SKILL("Active Skill: ", 0),
		SKILL_CD("Cool Down: %s seconds", 1),
		CURRENT_BONUS("Current bonus:", 0),
		DIG_SPEED("Dig Speed", 0),
		PUT_DOWN_FIRE("Put down fire", 0),
		;

		final Entry entry;

		Modular(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public static MutableComponent comma() {
			return COMMA.get();
		}

		public static MutableComponent item(ItemStack stack) {
			return stack.getHoverName().copy().withStyle(stack.getRarity().getStyleModifier());
		}

		public static MutableComponent curseItem() {
			return item(CAItems.CATASTROPHE_SCROLL.asStack());
		}

		public static MutableComponent curse() {
			return CURSE.get(curseItem()).withStyle(ChatFormatting.RED);
		}

		public static MutableComponent shift() {
			return SHIFT.get(Component.literal("SHIFT").withStyle(ChatFormatting.YELLOW))
					.withStyle(ChatFormatting.GRAY);
		}

		public static MutableComponent alt() {
			return ALT.get(Component.literal("ALT").withStyle(ChatFormatting.YELLOW))
					.withStyle(ChatFormatting.GRAY);
		}

		public static MutableComponent curseAlt() {
			return Curse.ALT.get(Component.literal("ALT").withStyle(ChatFormatting.YELLOW))
					.withStyle(ChatFormatting.GRAY);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Condition implements Info {
		PLAYER_WET("When player is in water or rain:", 0),
		NIGHT("At Night:", 0),
		DAY("At Day:", 0),
		HOT_REGION("When in hot biomes:", 0),
		ATTACK_BEHIND("When attacking from behind:", 0),
		FRONT_DAMAGE("When attacked by mobs in front:", 0),
		TARGET_HAS_ARMOR("When target has armor:", 0),
		REVENGE("When hurt, for the next %s seconds: ", 1),
		LUCK("When you have %s or more Luck: ", 1),
		TITAN("With %s, when you deal melee damage to mobs with higher max health than you: ", 1),
		NETHER("When you are in nether: ", 0),
		SNEAK("When you are sneaking: ", 0),
		HURT_FIRE("When you are hurt with fire damage:", 0),
		LOW_HEALTH("When you are at %s or lower health:", 1);

		final Entry entry;

		Condition(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum DamageTypes implements Info {
		MAGIC("magic", e -> e.is(L2DamageTypes.MAGIC)),
		FIRE("fire", e -> e.is(DamageTypeTags.IS_FIRE)),
		FALL("fall", e -> e.is(DamageTypeTags.IS_FALL)),
		FREEZE("freezing", e -> e.is(DamageTypeTags.IS_FREEZING)),
		LIGHTNING("lightning", e -> e.is(DamageTypeTags.IS_LIGHTNING)),
		PROJECTILE("projectile", e -> e.is(DamageTypeTags.IS_PROJECTILE)),
		WATER_MOB("water mob", e -> e.getEntity() instanceof Mob mob && mob.getMobType() == MobType.WATER),
		;

		final Entry entry;
		final Predicate<DamageSource> pred;

		DamageTypes(String def, Predicate<DamageSource> pred) {
			this.entry = new Entry(name().toLowerCase(Locale.ROOT), def, 0);
			this.pred = pred;
		}

		public Entry entry() {
			return entry;
		}

		public boolean pred(DamageSource source) {
			return pred.test(source);
		}
	}

	public enum Back implements Info {
		FLAME("Arrows you shoot will burn target for %s seconds", 1),
		LEECH("When you have %s effect, heal %s of your melee damage dealt", 2),

		TWIST_0("Every time you kill a mob, you gain 1 [Twist]", 0),
		TWIST_1("You lose 1 [Twist] every %s seconds", 1),
		TWIST_2("Every [Twist] increase your melee damage by %s", 1),
		TWIST_3("When you bear %s curse, increase by %s instead", 2),
		TWIST_4("Current [Twist]: %s", 1);

		final Entry entry;

		Back(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Bracelet implements Info {

		HIDDEN_0("Gain %3$s %2$s seconds after %1$s disappears", 3),
		HIDDEN_1("When you have %s, increase attack damage by %s", 2),

		CHARM_1("When you are attacked, mobs around you will help you to revenge. Cool down: %s seconds", 1),
		CHARM_0("When you kill zombies, increase your reputation by %s", 1),

		SCARLET_0("When you attack, consume your health till %s of your max health", 1),
		SCARLET_1("For every HP consumed, increase your damage by %s of target max health, capped at %s", 2),

		SPIRIT_0("Increase bow / crossbow pulling speed by %s", 1),
		SPIRIT_1("Projectiles you shoot will inflict %s", 1);

		final Entry entry;

		Bracelet(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Charm implements Info {
		WAR_DEAD_BADGE_1("For every 1% health lost:", 0),
		WAR_DEAD_BADGE_9("If you bear Chaotic Curse and your health is below %s:", 1),
		WAR_DEAD_BADGE_11("For  mob around you, heals %s after attack", 1),

		CURSED_PROTECTOR_0("Shields on your offhand will not be disabled", 0),
		CURSED_PROTECTOR_1("When you take damage higher than %s of your current health, reduce it by %s", 2),

		UNDEAD_CHARM("Negate a fatal damage with %s seconds cool down", 1),
		TWISTED_BRAIN("%s chance to avoid incoming damage and gain %s", 2),

		SOUL_BOX_0("When hurt, %s chance to inflict %s to attacker", 2),
		SOUL_BOX_1("Negate a fatal damage with %s seconds cool down, and:", 1),
		SOUL_BOX_2("Inflict %s to attacker", 1),
		SOUL_BOX_3("Hurt opponent with abyssal damage for %s of your max health", 1),

		SOLAR_MAGNET("Attract surrounding items", 0),

		SACRIFICIAL_OBJECT_1("%s chance to leave a gold ingot on death", 1),
		SACRIFICIAL_OBJECT_2("%a chance to kill surrounding enemies with lower max health than you", 1),

		KNIGHT_SHELTER_1("When you have shield in off hand, heal 1 point every %s seconds. Heals at double rate when you have shield in main hand.", 1),
		KNIGHT_SHELTER_2("When you use shield to block damage reflect %s of the damage blocked", 1),

		HOLY_TALISMAN_1("For every %s seconds, inflict surrounding mobs with Weakness for mob count x %s seconds", 2),
		HOLY_TALISMAN_2("Reduce incoming damage by %s. If it's from undead mobs, reduce by %s instead.", 2),
		HOLY_TALISMAN_3("Negate a fatal damage and gain absorption equal to your max health. Cool down: %s seconds", 1),

		HOLY_SWORD_1("Reflect %s of damage you take", 1),
		HOLY_SWORD_2("Heal the same amount when you deal damage to undead mob", 0),
		HOLY_SWORD_3("For every life point you lose, increase damage by %s", 1),

		GLUTTONY_BADGE_1("Gain %s and %s after eating food", 2),
		GLUTTONY_BADGE_2("For every food point you have, reduce incoming damage by %s", 1),

		DEMON_CURSE_0("You cannot heal", 0),
		DEMON_CURSE_1("For every 1% extra regen rate:", 0),

		CURSED_TOTEM_1("When you trigger totem, inflict attacker with %s", 1),
		CURSED_TOTEM_2("When you take non-fatal damage, stack 1 [Resentment]. Capped at %s.", 1),
		CURSED_TOTEM_3("When you take fatal damage, consume %s [Resentment] and negate the damage.", 1),
		CURSED_TOTEM_4("Current [Resentment]: %s", 1),

		CURSED_TALISMAN_1("For every cursed enchantment you have on your equipments:", 0),

		CORRUPT_BADGE_2("Gain %s, %s, and %s", 3),
		CORRUPT_BADGE_3("For every harmful effect you have:", 0),

		ANGEL_PEARL_1("Surrounding players gain regeneration:", 0),
		ANGEL_PEARL_2("For every beneficial effect you have:", 0),

		ANGEL_HEART_1("For every %s seconds, heal %s health", 2),
		ANGEL_HEART_2("For every %s seconds, clear all harmful effects", 1),

		ABYSS_WILL_BADGE_1("Increase [Abyssal Call] to %s for %s seconds", 2),
		ABYSS_WILL_BADGE_2("After skill ends, clear [Abyssal Call] and retain only %s of current health", 1),
		ABYSS_WILL_BADGE_3("%s chance to deal %s damage, and %s chance to deal %s", 4),
		ABYSS_WILL_BADGE_4("%s chance to take %s damage, and %s chance to take %s", 4),
		ABYSS_WILL_BADGE_5("For every %s seconds, gain 1 [Abyssal Call]. Capped at %s", 2),
		ABYSS_WILL_BADGE_6("Every [Abyssal Call] increase attack damage by %s and incoming damage by %s", 2),
		ABYSS_WILL_BADGE_7("Current [Abyssal Call]: %s", 1);

		final Entry entry;

		Charm(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Curse implements Info {
		SCROLL_0("You cannot take it down once equipped", 0),
		SCROLL_1("Unlocks some powerful trinkets and loot", 0),
		SCROLL_2("Find and equip etchings to reverse the curses", 0),
		TRIGGER("%s Curse is activated", 1),
		TRIGGER_COND("Trigger Condition:", 0),
		CURSE_EFF("Effect in Curse:", 0),
		BLESS_EFF("Effect with Etchings:", 0),
		ALT("Press [%s] to display curse details", 1),

		CHAOS_TITLE("[Chaotic]", 0),
		CHAOS_TRIGGER("Activates when you equip enchanted armor", 0),
		CHAOS_CURSE_0("Increase incoming explosion damage by %s", 1),
		CHAOS_CURSE_1("Increase other incoming damage by %s", 1),
		CHAOS_BONUS("For every %s HP you lose, reduce damage by %s", 2),

		ORIGIN_TITLE("[Origin]", 0),
		ORIGIN_TRIGGER("Activates when you hold items with %s or more durability", 1),
		ORIGIN_CURSE("Reduce attack damage by %s", 1),
		ORIGIN_BONUS("Increase dig speed and attack damage by %s", 1),

		LIFE_TITLE("[Life]", 0),
		LIFE_TRIGGER("Activates when you take damage", 0),
		LIFE_CURSE("Reduce max HP by %s. Reduce regen rate by %s.", 2),
		LIFE_BONUS("Increase max HP by %s. Increase regen rate by %s.", 2),

		TRUTH_TITLE("[Truth]", 0),
		TRUTH_TRIGGER("Activates when you equip etchings", 0),
		TRUTH_CURSE("When you are attacked by entities, the damage will be at least %s of your max HP", 1),
		TRUTH_BONUS("When you are attacked by entities, the damage will be at most %s of your max HP", 1),

		DESIRE_TITLE("[Desire]", 0),
		DESIRE_TRIGGER("Activates when you attack passive mobs.", 0),
		DESIRE_CURSE("Passive mobs will escape from you, and neutral mobs will attack you.", 0),
		DESIRE_BONUS("Increase Fortune and Looting by %s", 1),

		NIHILITY_TITLE("[Nihility]", 0),
		NIHILITY_TRIGGER("Activates when you gain beneficial effects.", 0),
		NIHILITY_CURSE("Harmful effects you get will have +%s duration", 1),
		NIHILITY_BONUS("Reduce void damage by %s. Inflict target with %s", 2),

		END_TITLE("[End]", 0),
		END_TRIGGER("Activates when you look at enderman", 0),
		END_CURSE("When you take damage higher than %s of your max health, gain %s and %s", 3),
		END_BONUS("When you deal damage, recover %s of your lost health", 1),
		;

		final Entry entry;

		Curse(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Head implements Info {
		EVIL_EYE("Immune to Darkness and Blindness effects", 0),
		ABYSS_CORE("When you take %s or more damage, reduce it to %s. Cool down: %s seconds", 3),
		GUARDIAN_EYE_1("Inflict %s to surrounding mobs.", 1),
		GUARDIAN_EYE_2("Gain infinite water breathing", 0),
		SEA_GOD_CROWN("Change weather to raining", 0),
		PRAYER_CROWN("When you take damage, %s chance to recover %s of the damage took.", 2),
		SPIRIT_CROWN_1("When there are %s or fewer mobs around you, increase projectile damage by %s", 2),
		SPIRIT_CROWN_2("For every block in between increase projectile damage by %s", 1),
		SAKURA_HAIRPIN("For every luck: ", 0),
		YELLOW_DUCK("You can walk on the water", 0),
		ANGEL_DESIRE_1("When flying: ", 1),
		ANGEL_DESIRE_2("Immune to projectile damage", 0),
		ANGEL_DESIRE_3("Boost elytra flying", 0),
		;

		final Entry entry;

		Head(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Heart implements Info {
		DEMON_HEART_1("For every curse you bear:", 0),
		DEMON_HEART_2("+%s Armor Toughness", 1),
		DEMON_HEART_3("+%s Attack Damage", 1),
		DEMON_HEART_4("-%s Incoming Damage", 1),
		DEMON_HEART_5("When you bear 3 or more curses, you are not flammable.", 0),
		DEMON_HEART_6("When you bear 5 or more curses, you are immune to Slowness and Weakness.", 0),

		TWISTED_HEART_1("Reduce Armor Toughness by %s", 1),
		TWISTED_HEART_2("Reduce attack damage by %s", 1),
		TWISTED_HEART_3("If you bear Nihility curse, reverse the above effects", 0);

		final Entry entry;

		Heart(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Necklace implements Info {
		GALLOP("When you attack while moving, increase damage by %s of your speed", 1),
		EMERALD("%s chance to gain an emerald when killing mobs", 1),
		ENDER_PROTECTOR("When you block damage with shield, %s chance to teleport attacker away", 1),
		HOLY("Gain %s after healing. Cool down: %s seconds", 2),
		LOCK_OF_ABYSS_1("Inflict target with %s seconds of Slowness on hit", 1),
		LOCK_OF_ABYSS_2("Increase target Slowness level instead if it already has the effect.", 0),
		LOCK_OF_ABYSS_3("If target has %s or higher Slowness effect when attacked:", 1),
		LOCK_OF_ABYSS_4("Remove slowness and append abyssal damage equal to %s of original damage", 1),
		TREASURE_HUNTER_NECKLACE("When fishing in some biomes, you may catch the structural spoils of the corresponding biomes, cooldown for %s seconds", 1);

		final Entry entry;

		Necklace(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Body implements Info {

		;

		final Entry entry;

		Body(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Pendant implements Info {
		SHADOW_1("Heal with %s of damage you dealt", 1),
		SHADOW_2("Prevent Phantom spawn", 0),
		SHADOW_3("For every level of brightness below %s:", 1),
		SHADOW_4("Increase attack damage by %s", 1),
		SHADOW_5("Reduce incoming damage by %s", 1),
		CHAOTIC("Increase enchantment level obtained from enchanting table by %s", 1);

		final Entry entry;

		Pendant(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Ring implements Info {
		FLIGHT("Gain creative flying ability", 0),
		NETHER_FIRE("Burn target for %s seconds on hit", 1),
		RING_OF_LIFE("For every %s seconds, boost growth on surrounding crops", 1);

		final Entry entry;

		Ring(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Scroll implements Info {
		SEA_GOD("Boost mining speed under water", 0),
		SKY_WALKER_2("Store current position when sneaking, otherwise teleport you to stored position.", 0),
		SKY_WALKER_3("Can only teleport in the same dimension", 0),
		SKY_WALKER_4("Current stored position: %s - (%s,%s,%s)", 4),
		TRAVELER("Gain %s and %s when changing dimensions", 2),
		TWIST("Mobs you encounter has %s chance to duplicate", 1);

		final Entry entry;

		Scroll(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Sets implements Info {
		SEA_GOD("When you hold trident:", 0),
		SPIRIT_0("Gain %2$s after pulling bow for %1$s", 2),
		SPIRIT_1("Increase %s damage from behind by %s", 2),
		SPIRIT_2("When you have %s:", 1),
		SPIRIT_3("Your projectile has %s chance to inflict %s", 2);

		final Entry entry;

		Sets(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public interface Info {

		Entry entry();

		default String path() {
			return MAP.get(getClass()).path();
		}

		default String desc() {
			return CelestialArtifacts.MODID + "." + path() + "." + entry().id();
		}

		default MutableComponent get(MutableComponent... objs) {
			if (objs.length != entry().count())
				throw new IllegalArgumentException("for " + entry().id() + ": expect " + entry().count() + " parameters, got " + objs.length);
			return translate(desc(), (Object[]) objs);
		}

	}

	public record EnumEntry(String path, Info[] info) {

	}

	public record Entry(String id, String def, int count) {
	}

}
