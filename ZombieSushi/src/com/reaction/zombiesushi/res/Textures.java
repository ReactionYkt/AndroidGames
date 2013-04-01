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

	public static BitmapTextureAtlas START_BUTTON_ATLAS;
	public static TextureRegion START_BUTTON_REGION;

	public static BitmapTextureAtlas COOK_BODY_ATLAS;
	public static TiledTextureRegion COOK_BODY_REGION;

	public static BitmapTextureAtlas COOK_FEET_ATLAS;
	public static TiledTextureRegion COOK_FEET_REGION;

	public static BitmapTextureAtlas BUTTON_ATLAS;
	public static TiledTextureRegion BUTTON_REGION;

	public static BitmapTextureAtlas BACKGROUND_ATLAS;
	public static TextureRegion BACKGROUND_REGION;

	public static BitmapTextureAtlas LEVEL1_LAYER1_ATLAS;
	public static TextureRegion BUILDING1;
	public static TextureRegion BUILDING2;
	public static TextureRegion BUILDING3;

	public static BitmapTextureAtlas LEVEL1_LAYER2_ATLAS;
	public static TextureRegion DECOR1;
	public static TextureRegion DECOR2;
	public static TextureRegion DECOR3;

	public static BitmapTextureAtlas ZOMBIE_ATLAS;
	public static TiledTextureRegion ZOMBIE_REGION;

	public static BitmapTextureAtlas BLEEDING_ZOMBIE_ATLAS;
	public static TiledTextureRegion BLEEDING_ZOMBIE_REGION;

	public static BitmapTextureAtlas HEART_ATLAS;
	public static TextureRegion HEART_TEXTURE_REGION;

	public static BitmapTextureAtlas GAME_OVER_ATLAS;
	public static TextureRegion GAME_OVER_REGION;

	public static BitmapTextureAtlas OBSTACLE_ATLAS;
	public static TextureRegion OBSTACLE_REGION;

	public static void init(final SimpleBaseGameActivity activity) {
		Log.i("ZombieSushi", "onLoadResources");
		
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		
		START_BUTTON_ATLAS = new BitmapTextureAtlas(
				activity.getTextureManager(), 220, 30);
		START_BUTTON_REGION = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(START_BUTTON_ATLAS, activity, "start.png", 0,
						0);

		COOK_BODY_ATLAS = new BitmapTextureAtlas(activity.getTextureManager(),
				372, 833, TextureOptions.BILINEAR);
		COOK_BODY_REGION = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(COOK_BODY_ATLAS, activity, "slash.png",
						0, 0, 2, 7);

		COOK_FEET_ATLAS = new BitmapTextureAtlas(activity.getTextureManager(),
				200, 150, TextureOptions.BILINEAR);
		COOK_FEET_REGION = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(COOK_FEET_ATLAS, activity,
						"feet_tiled.png", 0, 0, 2, 5);

		BUTTON_ATLAS = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 128, TextureOptions.BILINEAR);
		BUTTON_REGION = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(BUTTON_ATLAS, activity,
						"toggle_button.png", 0, 0, 2, 1);

		ZOMBIE_ATLAS = new BitmapTextureAtlas(activity.getTextureManager(),
				540, 672, TextureOptions.BILINEAR);
		ZOMBIE_REGION = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(ZOMBIE_ATLAS, activity, "zombie.png", 0,
						0, 4, 7);

		BACKGROUND_ATLAS = new BitmapTextureAtlas(activity.getTextureManager(),
				800, 480, TextureOptions.BILINEAR);
		BACKGROUND_REGION = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(BACKGROUND_ATLAS, activity, "bg.png", 0, 0);

		GAME_OVER_ATLAS = new BitmapTextureAtlas(activity.getTextureManager(),
				320, 160, TextureOptions.BILINEAR);
		GAME_OVER_REGION = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(GAME_OVER_ATLAS, activity, "game-over.png", 0,
						0);

		LEVEL1_LAYER1_ATLAS = new BitmapTextureAtlas(
				activity.getTextureManager(), 238, 273, TextureOptions.BILINEAR);
		BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				LEVEL1_LAYER1_ATLAS, activity, "1_layer_atlas.png", 0, 0);
		BUILDING1 = TextureRegionFactory.extractFromTexture(
				LEVEL1_LAYER1_ATLAS, 0, 0, 99, 147, false);
		BUILDING2 = TextureRegionFactory.extractFromTexture(
				LEVEL1_LAYER1_ATLAS, 100, 0, 99, 147, false);
		BUILDING3 = TextureRegionFactory.extractFromTexture(
				LEVEL1_LAYER1_ATLAS, 0, 147, 238, 126, false);

		LEVEL1_LAYER2_ATLAS = new BitmapTextureAtlas(
				activity.getTextureManager(), 1187, 167,
				TextureOptions.BILINEAR);
		BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				LEVEL1_LAYER2_ATLAS, activity, "2_layer_atlas.png", 0, 0);
		DECOR1 = TextureRegionFactory.extractFromTexture(LEVEL1_LAYER2_ATLAS,
				0, 0, 235, 167, false);
		DECOR2 = TextureRegionFactory.extractFromTexture(LEVEL1_LAYER2_ATLAS,
				235, 0, 499, 167, false);
		DECOR3 = TextureRegionFactory.extractFromTexture(LEVEL1_LAYER2_ATLAS,
				734, 0, 453, 167, false);

		HEART_ATLAS = new BitmapTextureAtlas(activity.getTextureManager(), 8,
				16, TextureOptions.BILINEAR);
		BitmapTextureAtlasTextureRegionFactory.createFromAsset(HEART_ATLAS,
				activity, "heart.png", 0, 0);
		HEART_TEXTURE_REGION = TextureRegionFactory.extractFromTexture(
				HEART_ATLAS, 0, 0, 8, 16);

		/*OBSTACLE_ATLAS = new BitmapTextureAtlas(activity.getTextureManager(),
				200, 200, TextureOptions.BILINEAR);
		BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				OBSTACLE_ATLAS, activity, "obstacle.png", 0, 0);
		OBSTACLE_REGION = TextureRegionFactory.extractFromTexture(
				OBSTACLE_ATLAS, 0, 0, 200, 200);*/
		}

	public static void load() {
		START_BUTTON_ATLAS.load();
		COOK_BODY_ATLAS.load();
		COOK_FEET_ATLAS.load();
		BUTTON_ATLAS.load();
		BACKGROUND_ATLAS.load();
		LEVEL1_LAYER1_ATLAS.load();
		LEVEL1_LAYER2_ATLAS.load();
		ZOMBIE_ATLAS.load();
		HEART_ATLAS.load();
		GAME_OVER_ATLAS.load();
//		OBSTACLE_ATLAS.load();
	}

	public static ITextureRegion getTextureRegionByType(int layer, int type) {
		if (layer == 1) {
			switch (type) {
			case 1:
				return BUILDING1;
			case 2:
				return BUILDING2;
			case 3:
				return BUILDING3;
			}
		} else if (layer == 2) {
			switch (type) {
			case 1:
				return DECOR1;
			case 2:
				return DECOR2;
			case 3:
				return DECOR3;
			}
		}
		return null;
	}

}
