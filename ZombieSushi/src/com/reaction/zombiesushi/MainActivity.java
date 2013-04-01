package com.reaction.zombiesushi;

import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.reaction.zombiesushi.core.ScreenManager;
import com.reaction.zombiesushi.res.Textures;
import com.reaction.zombiesushi.util.ResourceManager;

public class MainActivity extends SimpleBaseGameActivity {
	
	private static final int CAMERA_WIDTH = 800;
	private static final int CAMERA_HEIGHT = 480;
	
	private MenuScreen menuScreen;
	private Camera camera;
	
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		camera = new BoundCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		EngineOptions options = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, 
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
		options.getTouchOptions().setNeedsMultiTouch(true);
		options.getAudioOptions().getMusicOptions().setNeedsMusic(true);
		return options;
	}

	@Override
	protected void onCreateResources() {
		ScreenManager.init(this);
		Textures.init(this);
		Textures.load();
		ResourceManager.init(this);
		menuScreen = new MenuScreen(this);
	}

	@Override
	protected Scene onCreateScene() {
		return menuScreen.run();
	}
}
