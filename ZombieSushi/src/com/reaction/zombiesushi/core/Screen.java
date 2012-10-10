package com.reaction.zombiesushi.core;

import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.SimpleBaseGameActivity;

public abstract class Screen {

	protected Scene scene;
	protected final SimpleBaseGameActivity game;

	public Screen(SimpleBaseGameActivity game) {
		this.game = game;
		this.scene = new Scene();
	}
	
	public abstract Scene run();
	
	public abstract void destroy();

}
