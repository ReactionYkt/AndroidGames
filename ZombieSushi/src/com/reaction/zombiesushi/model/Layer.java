package com.reaction.zombiesushi.model;

import org.andengine.entity.Entity;
import org.andengine.opengl.texture.region.TextureRegion;

import android.util.Log;

import com.reaction.zombiesushi.MainActivity;

public class Layer {

	private int gapWidth = 75;
	private float velocity;
	private TemplateObjectPool pool;
	private Entity layerEntity;
	private TemplateSprite lastSprite;

	public Layer(TextureRegion[] templates, float velocity,
			MainActivity activity, int maxGapWidth) {
		this.velocity = -velocity;
		this.pool = new TemplateObjectPool(templates, activity);
		this.layerEntity = new Entity(0, 0);
		this.gapWidth = maxGapWidth;
	}

	public void spawnObject() {
		TemplateSprite sprite = null;
		if (lastSprite == null) {
			sprite = pool.obtainPoolItem();
			sprite.setPosition(0, 0);
			Log.d("sprite", "new sprite obtained");
		} else if (lastSprite.getX() < 800) {
			sprite = pool.obtainPoolItem();
			sprite.setX(lastSprite.getX() + lastSprite.getWidth() + gapWidth);
			Log.d("sprite", "new sprite obtained");
		}
		if (sprite != null) {
			if (!sprite.hasParent()) {
				this.layerEntity.attachChild(sprite);
			}
			sprite.setVelocityX(velocity);
			sprite.enable();
			lastSprite = sprite;
		}
	}

	public Entity getLayerEntity() {
		return layerEntity;
	}

}
