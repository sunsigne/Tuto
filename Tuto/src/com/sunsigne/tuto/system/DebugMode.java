package com.sunsigne.tuto.system;

import com.sunsigne.tuto.util.Cycloid;

public class DebugMode {

	private final Cycloid<Boolean> multiToolMode = new Cycloid<>(false, true);
	private final Cycloid<Boolean> wallPassMode = new Cycloid<>(false, true);
	private final Cycloid<Boolean> skipMode = new Cycloid<>(false, true);
	private final Cycloid<Boolean> hitboxMode = new Cycloid<>(false, true);
	private final Cycloid<Boolean> fastMode = new Cycloid<>(false, true);

	public Cycloid<Boolean> getMultiToolMode() {
		return multiToolMode;
	}

	public Cycloid<Boolean> getWallPassMode() {
		return wallPassMode;
	}

	public Cycloid<Boolean> getSkipMode() {
		return skipMode;
	}

	public Cycloid<Boolean> getHitboxMode() {
		return hitboxMode;
	}

	public Cycloid<Boolean> getFastMode() {
		return fastMode;
	}
}
