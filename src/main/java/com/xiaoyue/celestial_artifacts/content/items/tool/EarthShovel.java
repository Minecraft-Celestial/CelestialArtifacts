package com.xiaoyue.celestial_artifacts.content.items.tool;

import com.xiaoyue.celestial_artifacts.content.tiers.EarthTierUtils;
import net.minecraft.world.item.ShovelItem;

public class EarthShovel extends ShovelItem {
    public EarthShovel() {
        super(new EarthTierUtils(), 1.5f, -3, new Properties());
    }
}
