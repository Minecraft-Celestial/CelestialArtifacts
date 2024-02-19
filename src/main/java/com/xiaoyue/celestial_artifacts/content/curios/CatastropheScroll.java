package com.xiaoyue.celestial_artifacts.content.curios;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.config.CommonConfig;
import com.xiaoyue.celestial_artifacts.content.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.content.generic.Interface.GetCurioBreak;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_artifacts.utils.CurioUtiks;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class CatastropheScroll extends AttackICurio implements GetCurioBreak {
    public CatastropheScroll() {
        super(new Item.Properties().fireResistant().rarity(IRarityUtils.DARK_PURPLE));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.catastrophe_scroll.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.catastrophe_scroll.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.catastrophe_scroll.shift3");
        if (i_chaotic == 2) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_chaotic.shift1", ChatFormatting.YELLOW);
           ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_liberate.shift1");
            ToolTipUtils.addLocalTooltip(list,"tooltip.celestial_artifacts.cs_has_chaotic.shift1");
        } else if (i_chaotic == 1) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_chaotic.shift1", ChatFormatting.DARK_AQUA);
            ToolTipUtils.addLocalTooltip(list,"tooltip.celestial_artifacts.cs_no_curse_effect.shift1");
            ToolTipUtils.addLocalTooltip(list,"tooltip.celestial_artifacts.cs_no_chaotic.shift1");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_chaotic.shift1", ChatFormatting.DARK_PURPLE);
            ToolTipUtils.addLocalTooltip(list,"tooltip.celestial_artifacts.cs_no_liberate.shift1");
            ToolTipUtils.addLocalTooltip(list,"tooltip.celestial_artifacts.cs_no_chaotic.shift1",
                    ChatFormatting.DARK_RED, (CommonConfig.CATASTROPHE_SCROLL_EXPLOSION_DAMAGE.get() * 100) + "%");
            ToolTipUtils.addLocalTooltip(list,"tooltip.celestial_artifacts.cs_no_chaotic.shift2",
                    ChatFormatting.DARK_RED, (CommonConfig.CATASTROPHE_SCROLL_OTHER_DAMAGE.get() * 100) + "%");
        }
        if (i_origin == 2) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_origin.shift1", ChatFormatting.YELLOW);
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_liberate.shift1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_origin.shift1");
        } else if (i_origin == 1) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_origin.shift1", ChatFormatting.DARK_AQUA);
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_curse_effect.shift1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_origin.shift1");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_origin.shift1", ChatFormatting.DARK_PURPLE);
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_liberate.shift1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_origin.shift1",
                    ChatFormatting.DARK_RED, (CommonConfig.CATASTROPHE_SCROLL_ORIGIN_CURSE_DAMAGE.get()) * 100 + "%");
        }
        if (i_life == 2) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_life.shift1", ChatFormatting.YELLOW);
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_liberate.shift1");
            ToolTipUtils.addLocalTooltip(list,  "tooltip.celestial_artifacts.cs_has_life.shift1");
        } else if (i_life == 1) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_life.shift1", ChatFormatting.DARK_AQUA);
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_curse_effect.shift1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_life.shift1");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_life.shift1", ChatFormatting.DARK_PURPLE);
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_liberate.shift1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_life.shift1");
        }
        if (i_truth == 2) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_truth.shift1", ChatFormatting.YELLOW);
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_liberate.shift1");
            ToolTipUtils.addLocalTooltip(list,  "tooltip.celestial_artifacts.cs_has_truth.shift2");
            ToolTipUtils.addLocalTooltip(list,  "tooltip.celestial_artifacts.cs_no_truth.shift2");
        } else if (i_truth == 1) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_truth.shift1", ChatFormatting.DARK_AQUA);
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_curse_effect.shift1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_truth.shift2");
            ToolTipUtils.addLocalTooltip(list,  "tooltip.celestial_artifacts.cs_no_truth.shift2");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_truth.shift1", ChatFormatting.DARK_PURPLE);
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_liberate.shift1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_truth.shift1");
            ToolTipUtils.addLocalTooltip(list,  "tooltip.celestial_artifacts.cs_no_truth.shift1");
        }
        if (i_desire == 2) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_desire.shift1", ChatFormatting.YELLOW);
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_liberate.shift1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_desire.shift1");
        } else if (i_desire == 1) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_desire.shift1", ChatFormatting.DARK_AQUA);
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_curse_effect.shift1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_desire.shift1");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_desire.shift1", ChatFormatting.DARK_PURPLE);
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_liberate.shift1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_desire.shift1");
        }
        if (i_nihility == 2) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_nihility.shift1", ChatFormatting.YELLOW);
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_liberate.shift1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_nihility.shift1");
        } else if (i_nihility == 1) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_nihility.shift1", ChatFormatting.DARK_AQUA);
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_curse_effect.shift1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_nihility.shift1");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_nihility.shift1", ChatFormatting.DARK_PURPLE);
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_liberate.shift1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_nihility.shift1");
        }
        if (i_end == 2) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_end.shift1", ChatFormatting.YELLOW);
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_liberate.shift1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_end.shift1");
        } else if (i_end == 1) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_end.shift1", ChatFormatting.DARK_AQUA);
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_curse_effect.shift1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_end.shift1");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_end.shift1", ChatFormatting.DARK_PURPLE);
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_liberate.shift1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_end.shift1",
                    ChatFormatting.DARK_RED, (CommonConfig.CATASTROPHE_SCROLL_END_CURSE_DAMAGE.get() * 100) + "%");
        }
    }

    @Override
    public boolean canBeHurtBy(DamageSource source) {
        return false;
    }

    public static int i_chaotic;
    public static int i_origin;
    public static int i_life;
    public static int i_truth;
    public static int i_desire;
    public static int i_nihility;
    public static int i_end;

    public void check(Player player) {
        if (CurioUtiks.hasCurio(player, this)) {
            if (CurioUtiks.hasCurio(player, CAItems.CHAOTIC_ETCHING.get())) {
                i_chaotic = 2;
            } else {
                i_chaotic = 0;
            }
            if (CurioUtiks.hasCurio(player, CAItems.ORIGIN_ETCHING.get())) {
                i_origin = 2;
            } else {
                i_origin = 0;
            }
            if (CurioUtiks.hasCurio(player, CAItems.ETCHING_OF_LIFE.get())) {
                i_life = 2;
            } else {
                i_life = 0;
            }
            if (CurioUtiks.hasCurio(player, CAItems.TRUTH_ETCHING.get())) {
                i_truth = 2;
            } else {
                i_truth = 0;
            }
            if (CurioUtiks.hasCurio(player, CAItems.DESIRE_ETCHING.get())) {
                i_desire = 2;
            } else {
                i_desire = 0;
            }
            if (CurioUtiks.hasCurio(player, CAItems.NIHILITY_ETCHING.get())) {
                i_nihility = 2;
            } else {
                i_nihility = 0;
            }
            if (CurioUtiks.hasCurio(player, CAItems.END_ETCHING.get())) {
                i_end = 2;
            } else {
                i_end = 0;
            }
        }
    }

    private Multimap<Attribute, AttributeModifier> createAttributeMap(Player player) {
        Multimap<Attribute, AttributeModifier> attributesDefault = HashMultimap.create();
        if (i_chaotic == 2) {
            float i = (100 - (player.getHealth() / player.getMaxHealth() * 100)) / 500;
            attributesDefault.put(CCAttributes.DAMAGE_REDUCTION.get(), new AttributeModifier(ToolTipUtils.getFUuid(), "csedr", ToolTipUtils.getNoZeroValue(i), AttributeModifier.Operation.ADDITION));
        }
        if (i_life == 2) {
            attributesDefault.put(CCAttributes.REPLY_POWER.get(), new AttributeModifier(ToolTipUtils.getFUuid(), "csnre", 0.1, AttributeModifier.Operation.MULTIPLY_BASE));
            attributesDefault.put(Attributes.MAX_HEALTH, new AttributeModifier(ToolTipUtils.getFUuid(), "csemh", 0.2, AttributeModifier.Operation.MULTIPLY_BASE));
        } else if (i_life == 0) {
            attributesDefault.put(CCAttributes.REPLY_POWER.get(), new AttributeModifier(ToolTipUtils.getFUuid(), "csnre", -0.5, AttributeModifier.Operation.MULTIPLY_BASE));
            attributesDefault.put(Attributes.MAX_HEALTH, new AttributeModifier(ToolTipUtils.getFUuid(), "csnmh", -0.25, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        return attributesDefault;
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        check(player);
        player.getAttributes().addTransientAttributeModifiers(createAttributeMap(player));
        if (i_desire == 0) {
            List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, EntityUtils.getAABB(player, 8, 2));
            for (LivingEntity list : entities) {
                list.setLastHurtMob(player);
                list.setLastHurtByMob(player);
            }
        }
    }

    @Override
    public void takeTick(SlotContext context, Player player) {
        player.getAttributes().removeAttributeModifiers(createAttributeMap(player));
    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            return player.isCreative();
        }
        return false;
    }

    @NotNull
    @Override
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }

    @Override
    public int getLootingLevel(SlotContext slotContext, DamageSource source, LivingEntity target, int baseLooting, ItemStack stack) {
        if (slotContext.entity() instanceof Player) {
            if (i_desire == 2) {
                return super.getLootingLevel(slotContext, source, target, baseLooting, stack) + 1;
            }
        }
        return super.getLootingLevel(slotContext, source, target, baseLooting, stack);
    }

    @Override
    public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player) {
            if (i_desire == 2) {
                return super.getFortuneLevel(slotContext, lootContext, stack) + 1;
            }
        }
        return super.getFortuneLevel(slotContext, lootContext, stack);
    }

    @Override
    public void onPlayerHurtEntity(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtiks.hasCurio(player, this)) {
            if (i_origin == 0) {
                event.setAmount((float) (event.getAmount() * (1 - CommonConfig.CATASTROPHE_SCROLL_ORIGIN_CURSE_DAMAGE.get())));
            }
            if (i_origin == 2) {
                event.setAmount(event.getAmount() * 1.25f);
            }
            if (i_end == 2) {
                player.heal(player.getMaxHealth() - player.getHealth() * 0.12f);
            }
        }
    }

    @Override
    public void onUnderHurt(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtiks.hasCurio(player, this)) {
            if (i_chaotic == 0) {
                if (event.getSource().is(DamageTypes.EXPLOSION)) {
                    event.setAmount((float) (event.getAmount() * (1 + CommonConfig.CATASTROPHE_SCROLL_EXPLOSION_DAMAGE.get())));
                } else {
                    event.setAmount((float) (event.getAmount() * (1 + CommonConfig.CATASTROPHE_SCROLL_OTHER_DAMAGE.get())));
                }
            }
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
                if (i_nihility == 2) {
                    if (event.getSource().is(DamageTypes.FELL_OUT_OF_WORLD)) {
                        event.setAmount(event.getAmount() * 0.25f);
                    }
                    EntityUtils.addEct(livingEntity, MobEffects.POISON, 200, 2);
                }
            }
        }
    }

    @Override
    public void onUnderDamage(SlotContext context, Player player, LivingDamageEvent event) {
        if (i_end == 0) {
            if (event.getAmount() > player.getHealth() * CommonConfig.CATASTROPHE_SCROLL_END_CURSE_DAMAGE.get()) {
                EntityUtils.addEct(player, MobEffects.MOVEMENT_SLOWDOWN, 600, 1);
                EntityUtils.addEct(player, MobEffects.WEAKNESS, 600, 1);
            }
        }
        if (i_truth == 0) {
            if (player.getMaxHealth() < 40) {
                float min = Math.min(event.getAmount(), player.getMaxHealth() * 0.4f);
                event.setAmount(min);
            } else {
                float max = Math.max(event.getAmount(), player.getMaxHealth() * 0.4f);
                event.setAmount(max);
            }
        }
        if (i_truth == 2) {
            if (player.getMaxHealth() < 40) {
                float max = Math.max(event.getAmount(), player.getMaxHealth() * 0.4f);
                event.setAmount(max);
            } else {
                float min = Math.min(event.getAmount(), player.getMaxHealth() * 0.4f);
                event.setAmount(min);
            }
        }
    }

    @Override
    public void onPlayerBreak(SlotContext context, Player player, PlayerEvent.BreakSpeed event) {
        if (CurioUtiks.hasCurio(player, this)) {
            if (i_origin == 2) {
                event.setNewSpeed(event.getNewSpeed() * 1.25f);
            }
        }
    }
}
