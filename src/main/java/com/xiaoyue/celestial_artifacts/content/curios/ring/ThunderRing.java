package com.xiaoyue.celestial_artifacts.content.curios.ring;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.player.Player;

public class ThunderRing implements SingleLineText, CAAttackToken {

    @Override
    public MutableComponent getLine() {
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
