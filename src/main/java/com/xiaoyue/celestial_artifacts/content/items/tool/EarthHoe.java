package com.xiaoyue.celestial_artifacts.content.items.tool;

import com.xiaoyue.celestial_artifacts.content.tiers.EarthTierUtils;
import net.minecraft.world.item.HoeItem;

public class EarthHoe extends HoeItem {
    public EarthHoe() {
        super(new EarthTierUtils(), -4, 0, new Properties());
    }
}
