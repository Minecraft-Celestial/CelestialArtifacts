package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.register.CAEffects;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class DeerInscribedAmulet extends BaseTickingToken implements CAAttackToken {

    private static float bonus() {
        return CAModConfig.COMMON.charm.deerInscribedAmuletBlessBonus.get().floatValue();
    }

    private static int time() {
        return CAModConfig.COMMON.charm.deerInscribedAmuletStrengthInterval.get();
    }

    @SerialClass.SerialField
    private int chargingTime;

    @Override
    protected void removeImpl(Player player) {
        chargingTime = 0;
    }

    @Override
    protected void tickImpl(Player player) {
        if (chargingTime < time() * 20) {
            chargingTime++;
        }
    }

    @Override
    public void addText(@Nullable Level level, List<Component> list) {
        list.add(TextFacet.wrap(CALang.Charm.DEER_INSCRIBED_AMULET_1.get(TextFacet.num(time()), TextFacet.eff(CAEffects.ENFEEBLED_LACERATION.get()))));
        list.add(TextFacet.wrap(CALang.Charm.DEER_INSCRIBED_AMULET_2.get()));
        list.add(TextFacet.inner(CALang.Charm.DEER_INSCRIBED_AMULET_3.get(TextFacet.perc(bonus()), TextFacet.perc(bonus()))));
    }

    @Override
    public void onPlayerHurtTarget(Player player, AttackCache cache) {
        LivingEntity target = cache.getAttackTarget();
        if (chargingTime == time() * 20) {
            EntityUtils.addEct(target, CAEffects.ENFEEBLED_LACERATION.get(), 100);
            chargingTime = 0;
        }
        if (CurioUtils.isCsOn(player)) {
            int mulBonus = CurioUtils.getCurseAmount(player) == 0 ? 8 : 8 - CurioUtils.getCurseAmount(player);
            cache.addHurtModifier(DamageModifier.multBase(mulBonus * bonus()));
        }
    }
}
