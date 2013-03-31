package com.reaction.zombiesushi.core;

import org.andengine.entity.scene.Scene;
import com.reaction.zombiesushi.MainActivity;

public abstract class Screen {

	protected Scene scene;
	protected final MainActivity game;

	public Screen(MainActivity game) {
		this.game = game;
		this.scene = new Scene();
	}
	
	public abstract Scene run();
	
	public abstract void destroy();

}
