package com.xiaoyue.celestial_artifacts.content.curios.ring;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class ThunderRing implements SingleLineText, CAAttackToken {

    @Override
    public Component getLine() {
        return Component.translatable( "tooltip.celestial_artifacts.thunder_ring.shift1");
    }

    @Override
    public boolean onPlayerAttacked(Player player, AttackCache cache) {
        var source = getSource(cache);
        return source.is(DamageTypeTags.IS_LIGHTNING) &&
                !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY) &&
                !source.is(DamageTypeTags.BYPASSES_EFFECTS);
    }

}
