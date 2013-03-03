package com.reaction.zombiesushi.core;

import org.andengine.entity.scene.Scene;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.Texture;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;

public class ScreenManager {
	
	private static SimpleBaseGameActivity mainActivity;
	
	public static void init(SimpleBaseGameActivity base){
		mainActivity = base;
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
	}
	
	public static void setScene(Scene scene){
		mainActivity.getEngine().setScene(scene);
	}
	
	public static void loadTexture(Texture texture){
		mainActivity.getEngine().getTextureManager().loadTexture(texture);
	}
	
	public static void loadFont(Font font){
		mainActivity.getEngine().getFontManager().loadFont(font);
	}

}
