package com.reaction.zombiesushi.util;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.opengl.texture.TextureManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;

public class ResourceManager {
	
	private SimpleBaseGameActivity mainActivity;
	private static ResourceManager instance;
	
	private ResourceManager(SimpleBaseGameActivity engine){
		this.mainActivity = engine;
	}
	
	public static void init(SimpleBaseGameActivity engine){
		instance = new ResourceManager(engine);
	}
	
	public static ResourceManager getInstance(){
		return instance;
	}
	
	public static InputStream getResource(String filename) throws IOException{
		return instance.mainActivity.getAssets().open(filename);
	}
	
	public static TextureManager getTextureManager(){
		return instance.mainActivity.getTextureManager();
	}
	
	public static SimpleBaseGameActivity getMainActivity(){
		return instance.mainActivity;
	}

}
