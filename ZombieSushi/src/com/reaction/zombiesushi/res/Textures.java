package com.reaction.zombiesushi.res;

import org.andengine.engine.Engine;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.util.Log;

public class Textures {

	public static BitmapTextureAtlas startButtonTextureAtlas;
	public static TextureRegion startButtonTextureRegion;
	
	public static BitmapTextureAtlas bananaTextureAtlas;
	public static TiledTextureRegion bananaTextureRegion;

	public static void load(final SimpleBaseGameActivity activity, final Engine engine) {
		Log.i("ZombieSushi", "onLoadResources");
		startButtonTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 220, 30);
	    startButtonTextureRegion = BitmapTextureAtlasTextureRegionFactory.
	    		createFromAsset(startButtonTextureAtlas, activity, "start.png", 0, 0);
	    bananaTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 132, 70, TextureOptions.BILINEAR);
		bananaTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(bananaTextureAtlas, activity,
						"banana_tiled.png", 0, 0, 4, 2);
	    startButtonTextureAtlas.load();
	    bananaTextureAtlas.load();
	}

}
