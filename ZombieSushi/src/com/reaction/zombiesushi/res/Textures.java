package com.reaction.zombiesushi.res;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
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

	public static BitmapTextureAtlas level1Layer1Atlas;
	public static TextureRegion building1;
	public static TextureRegion building2;
	public static TextureRegion building3;

	public static BitmapTextureAtlas level1Layer2Atlas;
	public static TextureRegion decor1;
	public static TextureRegion decor2;
	public static TextureRegion decor3;

	public static BitmapTextureAtlas zombieAtlas;
	public static TiledTextureRegion zombieRegion;

	public static BitmapTextureAtlas bleedingZombieAtlas;
	public static TiledTextureRegion bleedingZombieRegion;

	public static BitmapTextureAtlas HEART_ATLAS;
	public static TextureRegion HEART_TEXTURE_REGION;

	public static BitmapTextureAtlas gameOverAtlas;
	public static TextureRegion gameOverTextureRegion;

	public static void init(final SimpleBaseGameActivity activity) {
		Log.i("ZombieSushi", "onLoadResources");

		startButtonTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 220, 30);
		startButtonTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(startButtonTextureAtlas, activity,
						"start.png", 0, 0);

		cookTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(),
				372, 833, TextureOptions.BILINEAR);
		cookTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(cookTextureAtlas, activity, "slash.png",
						0, 0, 2, 7);

		feetTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(),
				200, 150, TextureOptions.BILINEAR);
		feetTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(feetTextureAtlas, activity,
						"feet_tiled.png", 0, 0, 2, 5);

		toggleButtonTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		toggleButtonTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(toggleButtonTextureAtlas, activity,
						"toggle_button.png", 0, 0, 2, 1);

		zombieAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 540,
				672, TextureOptions.BILINEAR);
		zombieRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(zombieAtlas, activity, "zombie.png", 0,
						0, 4, 7);

		backgroundTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 800, 480, TextureOptions.BILINEAR);
		backgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(backgroundTextureAtlas, activity, "bg.png", 0,
						0);

		gameOverAtlas = new BitmapTextureAtlas(activity.getTextureManager(),
				320, 160, TextureOptions.BILINEAR);
		gameOverTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(gameOverAtlas, activity, "game-over.png", 0, 0);

		level1Layer1Atlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 238, 273, TextureOptions.BILINEAR);
		BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				level1Layer1Atlas, activity, "1_layer_atlas.png", 0, 0);
		building1 = TextureRegionFactory.extractFromTexture(level1Layer1Atlas,
				0, 0, 99, 147, false);
		building2 = TextureRegionFactory.extractFromTexture(level1Layer1Atlas,
				100, 0, 99, 147, false);
		building3 = TextureRegionFactory.extractFromTexture(level1Layer1Atlas,
				0, 147, 238, 126, false);

		level1Layer2Atlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 1187, 167,
				TextureOptions.BILINEAR);
		BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				level1Layer2Atlas, activity, "2_layer_atlas.png", 0, 0);
		decor1 = TextureRegionFactory.extractFromTexture(level1Layer2Atlas, 0,
				0, 235, 167, false);
		decor2 = TextureRegionFactory.extractFromTexture(level1Layer2Atlas,
				235, 0, 499, 167, false);
		decor3 = TextureRegionFactory.extractFromTexture(level1Layer2Atlas,
				734, 0, 453, 167, false);

		HEART_ATLAS = new BitmapTextureAtlas(activity.getTextureManager(), 8,
				16, TextureOptions.BILINEAR);
		BitmapTextureAtlasTextureRegionFactory.createFromAsset(HEART_ATLAS,
				activity, "heart.png", 0, 0);
		HEART_TEXTURE_REGION = TextureRegionFactory.extractFromTexture(
				HEART_ATLAS, 0, 0, 8, 16);

	}

	public static void load() {
		startButtonTextureAtlas.load();
		cookTextureAtlas.load();
		feetTextureAtlas.load();
		toggleButtonTextureAtlas.load();
		backgroundTextureAtlas.load();
		level1Layer1Atlas.load();
		level1Layer2Atlas.load();
		zombieAtlas.load();
		HEART_ATLAS.load();
		gameOverAtlas.load();
	}

	public static ITextureRegion getTextureRegionByType(int layer, int type) {
		if (layer == 1) {
			switch (type) {
			case 1:
				return building1;
			case 2:
				return building2;
			case 3:
				return building3;
			}
		} else if (layer == 2) {
			switch (type) {
			case 1:
				return decor1;
			case 2:
				return decor2;
			case 3:
				return decor3;
			}
		}
		return null;
	}

}
