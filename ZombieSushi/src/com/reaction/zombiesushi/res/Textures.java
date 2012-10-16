package com.reaction.zombiesushi.res;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.util.Log;

public class Textures {

	public static BitmapTextureAtlas startButtonTextureAtlas;
	public static TextureRegion startButtonTextureRegion;
	
	public static BitmapTextureAtlas cookTextureAtlas;
	public static TiledTextureRegion cookTextureRegion;
	
	public static BitmapTextureAtlas feetTextureAtlas;
	public static TiledTextureRegion feetTextureRegion;
	
	public static BitmapTextureAtlas toggleButtonTextureAtlas;
	public static TiledTextureRegion toggleButtonTextureRegion;
	
	public static BitmapTextureAtlas backgroundTextureAtlas;
	public static TextureRegion backgroundTextureRegion;

	public static void init(final SimpleBaseGameActivity activity) {
		Log.i("ZombieSushi", "onLoadResources");
		
		startButtonTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 220, 30);
	    startButtonTextureRegion = BitmapTextureAtlasTextureRegionFactory
	    		.createFromAsset(startButtonTextureAtlas, activity,
	    				"start.png", 0, 0);
	    
		cookTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 552, 354, TextureOptions.BILINEAR);
		cookTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(cookTextureAtlas, activity, 
						"cook_tiled.png", 0, 0, 3, 3);
		
		feetTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 200, 150, TextureOptions.BILINEAR);
		feetTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(feetTextureAtlas, activity, 
						"feet_tiled.png", 0, 0, 2, 5);
		
		toggleButtonTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		toggleButtonTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(toggleButtonTextureAtlas, activity, 
						"toggle_button.png", 0, 0, 2, 1);
		
		backgroundTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 800, 480, TextureOptions.BILINEAR);
		backgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(backgroundTextureAtlas, activity, 
						"bg.png", 0, 0);
		
	}
	
	public static void load(){
	    startButtonTextureAtlas.load();
	    cookTextureAtlas.load();
	    feetTextureAtlas.load();
	    toggleButtonTextureAtlas.load();
	    backgroundTextureAtlas.load();
	}
	
	public static ITextureRegion getTextureRegionByNamType(int type){
		return null;
	}

}
