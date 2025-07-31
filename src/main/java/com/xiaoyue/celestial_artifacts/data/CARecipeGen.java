package com.xiaoyue.celestial_artifacts.data;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.xiaoyue.celestial_artifacts.content.core.modular.ModularCurio;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_core.register.CCItems;
import dev.xkmc.l2library.serial.conditions.BooleanValueCondition;
import dev.xkmc.l2library.serial.recipe.ConditionalRecipeWrapper;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class CARecipeGen {

	private static Consumer<FinishedRecipe> saver = null;

	public static void onRecipeGen(RegistrateRecipeProvider pvd) {
		// curios
		{
			// scroll
			{
				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.SEA_GOD_SCROLL.get(), 1)::unlockedBy, Items.TRIDENT)
						.pattern("DEB").pattern("ACD").pattern("FDF")
						.define('A', Items.TRIDENT)
						.define('B', Items.PAPER)
						.define('C', Items.HEART_OF_THE_SEA)
						.define('D', Items.PRISMARINE_SHARD)
						.define('E', CCItems.OCEAN_ESSENCE.get())
						.define('F', Items.PRISMARINE_CRYSTALS)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.TWISTED_SCROLL.get(), 1)::unlockedBy, Items.PAPER)
						.pattern("XAA").pattern("ADB").pattern("AAC")
						.define('A', Items.PAPER)
						.define('B', CCItems.VOID_ESSENCE)
						.define('C', CCItems.DEATH_ESSENCE)
						.define('D', CCItems.MIDNIGHT_FRAGMENT.get())
						.define('X', CAItems.NEBULA_CUBE.get())
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.SKYWALKER_SCROLL.get(), 1)::unlockedBy, Items.PAPER)
						.pattern("CBA").pattern("BAB").pattern("ABD")
						.define('A', Items.PAPER)
						.define('B', Items.ENDER_PEARL)
						.define('C', Items.GLOW_INK_SAC)
						.define('D', CCItems.LIGHT_FRAGMENT.get())
						.save(saver);
			}

			// back
			{

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.IRON_SCABBARD.get(), 1)::unlockedBy, Items.IRON_INGOT)
						.pattern("BA ").pattern("ATA").pattern("AAB")
						.define('A', Items.IRON_INGOT)
						.define('B', Items.LEATHER)
						.define('T', CCItems.TREASURE_FRAGMENT.get())
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.TWISTED_SCABBARD.get(), 1)::unlockedBy, CAItems.IRON_SCABBARD.get())
						.pattern(" CB").pattern("CAC").pattern("DC ")
						.define('A', CAItems.IRON_SCABBARD.get())
						.define('B', Items.END_CRYSTAL)
						.define('C', Items.NETHERITE_INGOT)
						.define('D', CCItems.DEATH_ESSENCE.get())
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.TITAN_SCABBARD.get(), 1)::unlockedBy, CAItems.IRON_SCABBARD.get())
						.pattern(" DB").pattern("EAD").pattern("CE ")
						.define('A', CAItems.IRON_SCABBARD.get())
						.define('B', CCItems.LIGHT_FRAGMENT.get())
						.define('C', Items.EXPERIENCE_BOTTLE)
						.define('D', Items.GOLD_INGOT)
						.define('E', Items.LIME_DYE)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.MAGIC_ARROW_BAG.get(), 1)::unlockedBy, Items.AMETHYST_SHARD)
						.pattern("ABB").pattern("CTB").pattern("ACA")
						.define('A', Items.AMETHYST_SHARD)
						.define('B', Items.ARROW)
						.define('C', Items.LEATHER)
						.define('T', CCItems.TREASURE_FRAGMENT.get())
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.FLAME_ARROW_BAG.get(), 1)::unlockedBy, Items.BLAZE_POWDER)
						.pattern("BAB").pattern("ACA").pattern("BAB")
						.define('A', Items.BLAZE_POWDER)
						.define('B', Items.REDSTONE)
						.define('C', CAItems.MAGIC_ARROW_BAG.get())
						.save(saver);

			}

			// pendant
			{
				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.CHAOTIC_PENDANT.get(), 1)::unlockedBy, Items.ENCHANTED_BOOK)
						.pattern(" C ").pattern("BDB").pattern(" A ")
						.define('A', Items.ENCHANTED_BOOK)
						.define('B', Items.BLAZE_POWDER)
						.define('C', CAItems.THE_END_DUST.get())
						.define('D', CAItems.UNOWNED_PENDANT.get())
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.SHADOW_PENDANT.get(), 1)::unlockedBy, CAItems.NEBULA_CUBE.get())
						.pattern("XAF").pattern("CED").pattern("MBX")
						.define('A', Items.AMETHYST_SHARD)
						.define('B', Items.NETHERITE_INGOT)
						.define('C', CCItems.VOID_ESSENCE.get())
						.define('D', CAItems.THE_END_DUST.get())
						.define('E', CAItems.UNOWNED_PENDANT.get())
						.define('X', CAItems.NEBULA_CUBE.get())
						.define('M', CCItems.MIDNIGHT_FRAGMENT.get())
						.define('F', CCItems.DEATH_ESSENCE.get())
						.save(saver);
			}

			// ring
			{
				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.GOLD_RING.get(), 1)::unlockedBy, Items.GOLD_INGOT)
						.pattern(" A ").pattern("ATA").pattern(" A ")
						.define('A', Items.GOLD_INGOT)
						.define('T', CCItems.TREASURE_FRAGMENT.get())
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.DIAMOND_RING.get(), 1)::unlockedBy, Items.DIAMOND)
						.pattern(" A ").pattern("ATA").pattern(" A ")
						.define('A', Items.DIAMOND)
						.define('T', CCItems.TREASURE_FRAGMENT.get())
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.EMERALD_RING.get(), 1)::unlockedBy, Items.EMERALD)
						.pattern(" A ").pattern("ATA").pattern(" A ")
						.define('A', Items.EMERALD)
						.define('T', CCItems.TREASURE_FRAGMENT.get())
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.FLIGHT_RING.get(), 1)::unlockedBy, CAItems.GOLD_RING.get())
						.pattern(" C ").pattern("BAB").pattern("   ")
						.define('A', CAItems.GOLD_RING.get())
						.define('B', Items.FEATHER)
						.define('C', CCItems.SOARING_WINGS.get())
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.FREEZE_RING.get(), 1)::unlockedBy, CAItems.DIAMOND_RING.get())
						.pattern(" B ").pattern("CAC").pattern(" C ")
						.define('A', CAItems.DIAMOND_RING.get())
						.define('B', Items.BLUE_ICE)
						.define('C', Items.DIAMOND)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.RING_OF_LIFE.get(), 1)::unlockedBy, Items.CHERRY_SAPLING)
						.pattern("ACD").pattern("IBE").pattern("FGH")
						.define('A', Items.CHERRY_SAPLING)
						.define('B', CAItems.EMERALD_RING.get())
						.define('C', Items.BAMBOO)
						.define('D', Items.GREEN_DYE)
						.define('E', Items.PITCHER_PLANT)
						.define('F', Items.GLOW_BERRIES)
						.define('G', Items.WHEAT_SEEDS)
						.define('H', Items.NETHER_WART)
						.define('I', Items.MANGROVE_PROPAGULE)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.AMETHYST_RING.get(), 1)::unlockedBy, CAItems.GOLD_RING.get())
						.pattern(" B ").pattern("BAB").pattern("   ")
						.define('A', CAItems.GOLD_RING.get())
						.define('B', Items.AMETHYST_SHARD)
						.save(saver);

				upgradeEarth(pvd, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_INGOT, CAItems.DIAMOND_RING, CAItems.NETHERITE_RING);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.NETHER_FIRE.get(), 1)::unlockedBy, CAItems.NETHERITE_RING.get())
						.pattern(" C ").pattern("BAB").pattern(" D ")
						.define('A', CAItems.NETHERITE_RING.get())
						.define('B', Items.BLAZE_POWDER)
						.define('C', Items.REDSTONE)
						.define('D', CCItems.FIRE_ESSENCE.get())
						.save(saver);

			}

			// bracelet
			{

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.LIFE_BRACELET.get(), 1)::unlockedBy, Items.REDSTONE)
						.pattern(" A ").pattern("ABA").pattern(" C ")
						.define('A', Items.REDSTONE)
						.define('B', CCItems.TREASURE_FRAGMENT.get())
						.define('C', Items.GHAST_TEAR)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.PRECIOUS_BRACELET.get(), 1)::unlockedBy, Items.AMETHYST_SHARD)
						.pattern(" A ").pattern("ABA").pattern("DCD")
						.define('A', Items.AMETHYST_SHARD)
						.define('B', Items.ENDER_PEARL)
						.define('C', CCItems.TREASURE_FRAGMENT.get())
						.define('D', Items.DIAMOND).save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.EMERALD_BRACELET.get(), 1)::unlockedBy, Items.EMERALD)
						.pattern(" A ").pattern("ABA").pattern(" C ")
						.define('A', Items.EMERALD)
						.define('B', CCItems.TREASURE_FRAGMENT.get())
						.define('C', Items.RABBIT_FOOT)
						.save(saver);
			}

			// necklace
			{

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.STAR_NECKLACE.get(), 1)::unlockedBy, Items.LAPIS_LAZULI)
						.pattern("AAA").pattern("ABA").pattern("DCD")
						.define('A', Items.LAPIS_LAZULI)
						.define('B', Items.BLAZE_POWDER)
						.define('C', CCItems.MIDNIGHT_FRAGMENT.get())
						.define('D', Items.GOLD_INGOT)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.GALLOP_NECKLACE.get(), 1)::unlockedBy, CAItems.UNOWNED_PENDANT.get())
						.pattern("ATA").pattern("ABA").pattern("DCD")
						.define('A', Items.GOLD_INGOT)
						.define('T', CCItems.TREASURE_FRAGMENT)
						.define('B', CAItems.UNOWNED_PENDANT)
						.define('C', Items.RABBIT_FOOT)
						.define('D', Items.SUGAR).save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.ENDER_PROTECTOR.get(), 1)::unlockedBy, Items.AMETHYST_SHARD)
						.pattern("EAE").pattern("ABA").pattern("DCD")
						.define('A', CCItems.VOID_ESSENCE)
						.define('B', Items.ENDER_EYE)
						.define('C', CCItems.SHULKER_SCRAP.get())
						.define('D', Items.GLOW_INK_SAC)
						.define('E', Items.AMETHYST_SHARD)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.HOLY_NECKLACE.get(), 1)::unlockedBy, Items.GOLD_INGOT)
						.pattern("AAA").pattern("ABA").pattern("DCD")
						.define('A', Items.GOLD_INGOT)
						.define('B', CAItems.CROSS_NECKLACE.get())
						.define('C', CCItems.LIGHT_FRAGMENT.get())
						.define('D', Items.EXPERIENCE_BOTTLE)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.EMERALD_NECKLACE.get(), 1)::unlockedBy, Items.DIAMOND)
						.pattern("AAA").pattern("ADA").pattern("BCB")
						.define('A', Items.DIAMOND)
						.define('B', Items.IRON_INGOT)
						.define('C', Items.EMERALD)
						.define('D', CCItems.TREASURE_FRAGMENT.get())
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.PRECIOUS_NECKLACE.get(), 1)::unlockedBy, Items.DIAMOND)
						.pattern("AAA").pattern("ABA").pattern("CDC")
						.define('A', Items.DIAMOND)
						.define('B', Items.EXPERIENCE_BOTTLE)
						.define('C', Items.NETHERITE_SCRAP)
						.define('D', CCItems.TREASURE_FRAGMENT.get())
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.TREASURE_HUNTER_NECKLACE.get(), 1)::unlockedBy, Items.DIAMOND)
						.pattern("AAA").pattern("ABA").pattern("CDC")
						.define('A', Items.IRON_INGOT)
						.define('B', CAItems.THE_END_DUST)
						.define('C', Items.DIAMOND)
						.define('D', CCItems.TREASURE_FRAGMENT.get())
						.save(saver);

			}

			// head
			{
				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.PRAYER_CROWN.get(), 1)::unlockedBy, Items.REDSTONE_BLOCK)
						.pattern("B B").pattern("BAB").pattern("CDC")
						.define('A', Items.REDSTONE_BLOCK)
						.define('B', Items.GOLD_INGOT)
						.define('C', Items.IRON_INGOT)
						.define('D', CAItems.CROSS_NECKLACE.get())
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.EVIL_EYE.get(), 1)::unlockedBy, Items.ENDER_EYE)
						.pattern(" B ").pattern("BAB").pattern("DCD")
						.define('A', Items.ENDER_EYE)
						.define('B', Items.BLAZE_POWDER)
						.define('C', Items.INK_SAC)
						.define('D', CCItems.MIDNIGHT_FRAGMENT)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.SEA_GOD_CROWN.get(), 1)::unlockedBy, Items.PRISMARINE_SHARD)
						.pattern("A A").pattern("ADA").pattern("BCB")
						.define('A', Items.PRISMARINE_SHARD)
						.define('B', Items.DIAMOND)
						.define('C', Items.HEART_OF_THE_SEA)
						.define('D', CCItems.OCEAN_ESSENCE.get())
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.ABYSS_CORE.get(), 1)::unlockedBy, CCItems.VOID_ESSENCE.get())
						.pattern("EAE").pattern("CBC").pattern("DCD")
						.define('A', CAItems.NEBULA_CUBE)
						.define('B', Items.ENDER_PEARL)
						.define('C', Items.PRISMARINE_SHARD)
						.define('D', CCItems.VOID_ESSENCE)
						.define('E', CAItems.THE_END_DUST)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.SAKURA_HAIRPIN.get(), 1)::unlockedBy, CCItems.SAKURA_STEEL.get())
						.pattern("ABC").pattern("BAD").pattern(" CA")
						.define('A', Items.CHERRY_LOG)
						.define('B', CCItems.SAKURA_STEEL)
						.define('C', CCItems.SAKURA_FRAGMENT)
						.define('D', CCItems.LIGHT_FRAGMENT)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.ANGEL_DESIRE.get(), 1)::unlockedBy, CCItems.PURE_NETHER_STAR.get())
						.pattern("CDC").pattern("BAB").pattern("C C")
						.define('A', CCItems.PURE_NETHER_STAR)
						.define('B', Items.PHANTOM_MEMBRANE)
						.define('C', Items.FEATHER)
						.define('D', CCItems.LIGHT_FRAGMENT)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.YELLOW_DUCK.get(), 1)::unlockedBy, Items.YELLOW_WOOL)
						.pattern("ABA").pattern("A A").pattern("ACA")
						.define('A', Items.YELLOW_WOOL)
						.define('B', CCItems.OCEAN_ESSENCE.get())
						.define('C', CAItems.LIFE_ETCHING.get())
						.save(saver);

			}

			// body
			{
				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.FOREST_CLOAK.get(), 1)::unlockedBy, Items.GREEN_WOOL)
						.pattern("AAA").pattern("ABA").pattern("A A")
						.define('A', Items.GREEN_WOOL)
						.define('B', CCItems.LIGHT_FRAGMENT.get())
						.save(saver);
			}

			// charm
			{
				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.GLUTTONY_BADGE.get(), 1)::unlockedBy, Items.IRON_INGOT)
						.pattern("BAB").pattern("ACA").pattern("BAB")
						.define('A', Items.IRON_INGOT)
						.define('B', Items.ROTTEN_FLESH)
						.define('C', CCItems.DEATH_ESSENCE.get())
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.SOLAR_MAGNET.get(), 1)::unlockedBy, Items.IRON_INGOT)
						.pattern("B B").pattern("ADA").pattern("ACA")
						.define('A', Items.IRON_INGOT)
						.define('B', Items.REDSTONE)
						.define('C', CCItems.FIRE_ESSENCE.get())
						.define('D', Items.EXPERIENCE_BOTTLE)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.DESTROYER_BADGE.get(), 1)::unlockedBy, Items.PIGLIN_HEAD)
						.pattern("CBC").pattern("BAB").pattern("DCD")
						.define('A', Items.PIGLIN_HEAD)
						.define('B', Items.GOLD_INGOT)
						.define('C', Items.AMETHYST_SHARD)
						.define('D', CCItems.DEATH_ESSENCE)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.GREEDY_HEART.get(), 1)::unlockedBy, CCItems.HEART_FRAGMENT.get())
						.pattern("CBC").pattern("BAB").pattern("DBD")
						.define('A', CCItems.HEART_FRAGMENT.get())
						.define('B', Items.NETHER_STAR)
						.define('C', Items.NETHERITE_INGOT)
						.define('D', Items.GOLD_BLOCK)
						.save(saver);

			}

			// 1 etching
			{
				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.LOCK_OF_ABYSS.get(), 1)::unlockedBy, CAItems.NEBULA_CUBE.get())
						.pattern("AAA").pattern("ECE").pattern("DBD")
						.define('A', CAItems.NEBULA_CUBE.get())
						.define('E', CCItems.WARDEN_SCLERITE.get())
						.define('B', CAItems.CHAOTIC_ETCHING.get())
						.define('C', CCItems.HEART_FRAGMENT.get())
						.define('D', CAItems.THE_END_DUST)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.HIDDEN_BRACELET.get(), 1)::unlockedBy, CAItems.NEBULA_CUBE.get())
						.pattern("GBG").pattern("ADC").pattern("FEF")
						.define('A', Items.DISC_FRAGMENT_5)
						.define('B', Items.NETHERITE_INGOT)
						.define('C', CAItems.THE_END_DUST)
						.define('D', CAItems.TRUTH_ETCHING)
						.define('E', Items.BLAZE_POWDER)
						.define('F', CCItems.DEATH_ESSENCE.get())
						.define('G', CAItems.NEBULA_CUBE)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.KNIGHT_SHELTER.get(), 1)::unlockedBy, CAItems.NEBULA_CUBE.get())
						.pattern("ADA").pattern("BCB").pattern("EBE")
						.define('A', Items.NETHER_STAR)
						.define('B', Items.GOLD_INGOT)
						.define('C', CCItems.LIGHT_FRAGMENT.get())
						.define('D', CAItems.ORIGIN_ETCHING.get())
						.define('E', CAItems.NEBULA_CUBE.get())
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.ANGEL_HEART.get(), 1)::unlockedBy, CCItems.PURE_NETHER_STAR.get())
						.pattern(" B ").pattern("BAB").pattern("CDC")
						.define('A', CCItems.PURE_NETHER_STAR.get())
						.define('B', CCItems.SOARING_WINGS)
						.define('C', CAItems.NEBULA_CUBE)
						.define('D', CAItems.END_ETCHING)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.WAR_DEAD_BADGE.get(), 1)::unlockedBy, CAItems.NEBULA_CUBE.get())
						.pattern("DCD").pattern("BEA").pattern("DCD")
						.define('A', CCItems.FIRE_ESSENCE.get())
						.define('B', CCItems.DEATH_ESSENCE.get())
						.define('C', CAItems.THE_END_DUST.get())
						.define('D', CAItems.NEBULA_CUBE)
						.define('E', CAItems.NIHILITY_ETCHING)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.BEARING_STAMEN.get(), 1)::unlockedBy, CCItems.EARTH_CORE.get())
						.pattern("DFD").pattern("BEA").pattern("DCD")
						.define('A', CCItems.FIRE_ESSENCE.get())
						.define('B', CCItems.OCEAN_ESSENCE.get())
						.define('F', Items.SPORE_BLOSSOM)
						.define('C', Items.GHAST_TEAR)
						.define('D', Items.MOSSY_COBBLESTONE)
						.define('E', CAItems.LIFE_ETCHING)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.HOLY_SWORD.get(), 1)::unlockedBy, CAItems.NEBULA_CUBE.get())
						.pattern("DAD").pattern("CEC").pattern("DBD")
						.define('A', CCItems.FIRE_ESSENCE.get())
						.define('B', CCItems.LIGHT_FRAGMENT.get())
						.define('C', CCItems.SOARING_WINGS)
						.define('D', CAItems.NEBULA_CUBE)
						.define('E', CAItems.DESIRE_ETCHING)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.DEERS_MERCY_AMULET.get(), 1)::unlockedBy, CCItems.TREASURE_FRAGMENT.get())
						.pattern("I I").pattern("GTG").pattern("D D")
						.define('I', Items.IRON_INGOT)
						.define('G', Items.GOLD_INGOT)
						.define('T', CCItems.TREASURE_FRAGMENT)
						.define('D', Items.GLOWSTONE_DUST)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.DEER_INSCRIBED_AMULET.get(), 1)::unlockedBy, CAItems.DEERS_MERCY_AMULET.get())
						.pattern("I I").pattern("GTG").pattern("D D")
						.define('I', Items.FEATHER)
						.define('G', Items.DIAMOND)
						.define('T', CAItems.DEERS_MERCY_AMULET)
						.define('D', CCItems.LIGHT_FRAGMENT)
						.save(saver);

			}

			// 2 etchings
			{
				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.SACRIFICIAL_OBJECT.get(), 1)::unlockedBy, CAItems.NEBULA_CUBE.get())
						.pattern("ABA").pattern("1E2").pattern("CDC")
						.define('A', CAItems.THE_END_DUST)
						.define('B', CCItems.LIGHT_FRAGMENT.get())
						.define('D', CCItems.DEATH_ESSENCE)
						.define('E', CAItems.UNOWNED_PENDANT)
						.define('C', CAItems.NEBULA_CUBE)
						.define('1', CAItems.DESIRE_ETCHING)
						.define('2', CAItems.TRUTH_ETCHING)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.CURSED_TALISMAN.get(), 1)::unlockedBy, CAItems.NEBULA_CUBE.get())
						.pattern("ABA").pattern("1E2").pattern("CDC")
						.define('A', CAItems.THE_END_DUST)
						.define('B', CCItems.MIDNIGHT_FRAGMENT.get())
						.define('D', CCItems.HEART_FRAGMENT)
						.define('E', CAItems.UNOWNED_PENDANT)
						.define('C', CAItems.NEBULA_CUBE)
						.define('1', CAItems.ORIGIN_ETCHING)
						.define('2', CAItems.CHAOTIC_ETCHING)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.ANGEL_PEARL.get(), 1)::unlockedBy, CAItems.NEBULA_CUBE.get())
						.pattern("1D2").pattern("BAB").pattern("CCC")
						.define('A', Items.ENDER_PEARL)
						.define('B', CCItems.SOARING_WINGS)
						.define('C', CAItems.NEBULA_CUBE)
						.define('D', CCItems.PURE_NETHER_STAR.get())
						.define('1', CAItems.ORIGIN_ETCHING)
						.define('2', CAItems.DESIRE_ETCHING)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.CORRUPT_BADGE.get(), 1)::unlockedBy, CAItems.NEBULA_CUBE.get())
						.pattern("FEF").pattern("CBA").pattern("FDF")
						.define('A', CCItems.SHULKER_SCRAP)
						.define('B', CAItems.THE_END_DUST.get())
						.define('C', Items.ENDER_PEARL)
						.define('D', CAItems.CHAOTIC_ETCHING)
						.define('E', CAItems.END_ETCHING)
						.define('F', CAItems.NEBULA_CUBE)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.SCARLET_BRACELET.get(), 1)::unlockedBy, CAItems.TRUTH_ETCHING.get())
						.pattern("CBC").pattern("1A2").pattern("CBC")
						.define('A', CAItems.RED_RUBY_BRACELET)
						.define('1', CAItems.NIHILITY_ETCHING)
						.define('2', CAItems.TRUTH_ETCHING)
						.define('C', CAItems.NEBULA_CUBE)
						.define('B', CCItems.WARDEN_SCLERITE)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.CURSED_PROTECTOR.get(), 1)::unlockedBy, CAItems.NEBULA_CUBE.get())
						.pattern("ABA").pattern("ACA").pattern("EDE")
						.define('A', CCItems.SHULKER_SCRAP)
						.define('B', CCItems.DEATH_ESSENCE.get())
						.define('C', CAItems.CHAOTIC_ETCHING)
						.define('D', CAItems.TRUTH_ETCHING)
						.define('E', CAItems.NEBULA_CUBE)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.ABYSS_WILL_BADGE.get(), 1)::unlockedBy, CAItems.NEBULA_CUBE.get())
						.pattern("DCD").pattern("FBF").pattern("EAE")
						.define('A', Items.ECHO_SHARD)
						.define('B', CAItems.NIHILITY_ETCHING.get())
						.define('C', CAItems.END_ETCHING.get())
						.define('D', CAItems.THE_END_DUST)
						.define('F', CAItems.NEBULA_CUBE)
						.define('E', Items.DISC_FRAGMENT_5)
						.save(saver);

				// totems
				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.HOLY_TALISMAN.get(), 1)::unlockedBy, CCItems.PURE_NETHER_STAR.get())
						.pattern("FBG").pattern("CAD").pattern("ECC")
						.define('A', CCItems.PURE_NETHER_STAR.get())
						.define('B', CAItems.ORIGIN_ETCHING)
						.define('C', CAItems.NEBULA_CUBE)
						.define('D', CAItems.LIFE_ETCHING.get())
						.define('E', CCItems.LIGHT_FRAGMENT.get())
						.define('F', CCItems.MIDNIGHT_FRAGMENT.get())
						.define('G', CCItems.SOARING_WINGS)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.UNDEAD_CHARM.get(), 1)::unlockedBy, CAItems.NEBULA_CUBE.get())
						.pattern("DCD").pattern("EAE").pattern("DBD")
						.define('A', Items.SKELETON_SKULL)
						.define('B', CAItems.LIFE_ETCHING)
						.define('C', CAItems.DESIRE_ETCHING)
						.define('D', CAItems.NEBULA_CUBE)
						.define('E', CCItems.SHULKER_SCRAP)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.CURSED_TOTEM.get(), 1)::unlockedBy, CAItems.NEBULA_CUBE.get())
						.pattern("AEA").pattern("CDC").pattern("BFB")
						.define('A', Items.ECHO_SHARD)
						.define('B', CAItems.NEBULA_CUBE)
						.define('C', CCItems.DEATH_ESSENCE.get())
						.define('D', Items.TOTEM_OF_UNDYING)
						.define('E', CAItems.END_ETCHING)
						.define('F', CAItems.LIFE_ETCHING)
						.save(saver);

				unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.SOUL_BOX.get(), 1)::unlockedBy, CAItems.NEBULA_CUBE.get())
						.pattern("FEF").pattern("ABA").pattern("CDC")
						.define('A', CAItems.NEBULA_CUBE)
						.define('B', CCItems.HEART_FRAGMENT)
						.define('C', CCItems.LIGHT_FRAGMENT.get())
						.define('D', CAItems.NIHILITY_ETCHING)
						.define('E', CAItems.LIFE_ETCHING)
						.define('F', CCItems.WARDEN_SCLERITE)
						.save(saver);
			}

		}

		// tools
		{
			unlock(pvd, ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CAItems.PURIFIED_POWDER.get(), 1)::unlockedBy, CAItems.THE_END_DUST.get())
					.requires(Items.REDSTONE)
					.requires(Items.EXPERIENCE_BOTTLE)
					.requires(CAItems.THE_END_DUST.get())
					.save(pvd);

			unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.BACKTRACK_MIRROR.get(), 1)::unlockedBy, Items.ENDER_PEARL)
					.pattern(" BA").pattern("CEB").pattern("DC ")
					.define('A', Items.ENDER_PEARL)
					.define('B', Items.ECHO_SHARD)
					.define('C', CCItems.WARDEN_SCLERITE)
					.define('D', Items.NETHERITE_INGOT)
					.define('E', Items.GLASS_PANE)
					.save(pvd);

			unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.COPPER_REINFORCE_PLATE, 1)::unlockedBy, Items.COPPER_INGOT)
					.pattern("ICI").pattern("COC").pattern("ICI")
					.define('C', Items.COPPER_INGOT)
					.define('I', Items.IRON_INGOT)
					.define('O', Items.OBSIDIAN)
					.save(pvd);

			unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.AMETHYST_REINFORCE_PLATE, 1)::unlockedBy, Items.AMETHYST_SHARD)
					.pattern("ICI").pattern("COC").pattern("ICI")
					.define('C', Items.AMETHYST_SHARD)
					.define('I', Items.GOLD_INGOT)
					.define('O', CAItems.NEBULA_CUBE)
					.save(pvd);

			unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.REPENT_MIRROR.get(), 1)::unlockedBy, Items.ECHO_SHARD)
					.pattern(" BA").pattern("CEB").pattern("DC ")
					.define('A', Items.ECHO_SHARD)
					.define('B', CCItems.LIGHT_FRAGMENT.get())
					.define('C', CCItems.VOID_ESSENCE)
					.define('D', Items.NETHERITE_INGOT)
					.define('E', Items.GLASS_PANE)
					.save(pvd);

			unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.POTIONS_BAG, 1)::unlockedBy, CCItems.TREASURE_FRAGMENT.get())
					.pattern(" G ").pattern("ITI").pattern("III")
					.define('T', CCItems.TREASURE_FRAGMENT)
					.define('I', Items.LEATHER)
					.define('G', Items.GOLD_INGOT)
					.save(pvd);

			unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.GAIA_TOTEM, 1)::unlockedBy, Items.TOTEM_OF_UNDYING)
					.pattern("EGE").pattern("GOG").pattern(" L ")
					.define('E', Items.EMERALD)
					.define('G', Items.GOLD_INGOT)
					.define('O', Items.TOTEM_OF_UNDYING)
					.define('L', CCItems.HEART_FRAGMENT)
					.save(pvd);

			unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CAItems.ENDER_JUMP_SCEPTER, 1)::unlockedBy, CCItems.WARDEN_SCLERITE.get())
					.pattern("EGE").pattern(" T ").pattern("L  ")
					.define('E', Items.ENDER_PEARL)
					.define('G', Items.OBSIDIAN)
					.define('T', CCItems.WARDEN_SCLERITE)
					.define('L', CCItems.MIDNIGHT_FRAGMENT)
					.save(pvd);

			upgradeEarth(pvd, CAItems.NEBULA_CUBE, CCItems.EARTH_CORE, Items.NETHERITE_AXE, CAItems.EARTH_AXE);
			upgradeEarth(pvd, CAItems.NEBULA_CUBE, CCItems.EARTH_CORE, Items.NETHERITE_HOE, CAItems.EARTH_HOE);
			upgradeEarth(pvd, CAItems.NEBULA_CUBE, CCItems.EARTH_CORE, Items.NETHERITE_SHOVEL, CAItems.EARTH_SHOVEL);
			upgradeEarth(pvd, CAItems.NEBULA_CUBE, CCItems.EARTH_CORE, Items.NETHERITE_PICKAXE, CAItems.EARTH_PICKAXE);
		}
	}

	private static void upgradeEarth(RegistrateRecipeProvider pvd, ItemLike template, ItemLike add, ItemLike in, ItemEntry<?> out) {
		unlock(pvd, SmithingTransformRecipeBuilder.smithing(
				Ingredient.of(template),
				Ingredient.of(in), Ingredient.of(add),
				RecipeCategory.MISC, out.get())::unlocks, out.get())
				.save(pvd, out.getId());
	}

	public static <T> T unlock(RegistrateRecipeProvider pvd, BiFunction<String, InventoryChangeTrigger.TriggerInstance, T> func, Item item) {
		T ans = func.apply("has_" + pvd.safeName(item), DataIngredient.items(item).getCritereon(pvd));
		if (ans instanceof RecipeBuilder rec && rec.getResult() instanceof ModularCurio curio) {
			saver = ConditionalRecipeWrapper.of(pvd, BooleanValueCondition.of(CAModConfig.COMMON_PATH, curio.enableConfig(), true));
		}
		return ans;
	}

}
