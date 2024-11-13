package com.xiaoyue.celestial_artifacts.content.core.feature;

import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;

import java.util.ArrayList;
import java.util.List;

public class FeatureType<T extends IFeature> {

	static final List<FeatureType<?>> LIST = new ArrayList<>();

	public static final FeatureType<CAAttackToken> ATK = new FeatureType<>(CAAttackToken.class);
	public static final FeatureType<BreakSpeedFeature> MINING = new FeatureType<>(BreakSpeedFeature.class);
	public static final FeatureType<XpBonusFeature> EXP = new FeatureType<>(XpBonusFeature.class);
	public static final FeatureType<SkillFeature> SKILL = new FeatureType<>(SkillFeature.class);
	public static final FeatureType<HealFeature> HEAL = new FeatureType<>(HealFeature.class);
	public static final FeatureType<ShieldingFeature> SHIELD = new FeatureType<>(ShieldingFeature.class);
	public static final FeatureType<JumpFeature> JUMP = new FeatureType<>(JumpFeature.class);

	final Class<T> cls;

	public FeatureType(Class<T> cls) {
		this.cls = cls;
		LIST.add(this);
	}

}
