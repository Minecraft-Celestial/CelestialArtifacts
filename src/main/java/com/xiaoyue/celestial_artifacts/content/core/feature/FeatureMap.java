package com.xiaoyue.celestial_artifacts.content.core.feature;

import dev.xkmc.l2serial.util.Wrappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FeatureMap {

	private final HashMap<FeatureType<?>, List<?>> map = new HashMap<>();
	private boolean empty = false;

	public <T extends IFeature> List<T> get(FeatureType<T> type) {
		return Wrappers.cast(map.computeIfAbsent(type, t -> new ArrayList<>()));
	}

	public <T extends IFeature> void add(FeatureType<T> type, T val) {
		get(type).add(val);
		empty = false;
	}

	public void add(Object obj) {
		for (var e : FeatureType.LIST) {
			if (e.cls.isInstance(obj)) {
				get(e).add(Wrappers.cast(obj));
				empty = false;
			}
		}
	}

	public void clear() {
		map.clear();
		empty = true;
	}

	public void addAll(FeatureMap features) {
		if (features.empty) return;
		for (var e : features.map.keySet()) {
			merge(e, features);
		}
		empty = false;
	}

	private <T extends IFeature> void merge(FeatureType<T> type, FeatureMap features) {
		get(type).addAll(features.get(type));
	}

}
