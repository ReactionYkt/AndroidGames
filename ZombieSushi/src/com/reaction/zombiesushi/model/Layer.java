package com.reaction.zombiesushi.model;

import org.andengine.entity.Entity;
import org.andengine.opengl.texture.region.TextureRegion;

import com.reaction.zombiesushi.MainActivity;
import com.reaction.zombiesushi.util.NumberUtil;

public class Layer {

	private int maxGapWidth = 75;
	private float velocity;
	private TemplateObjectPool pool;
	private Entity layerEntity;
	private TemplateSprite lastSprite;

	public Layer(TextureRegion[] templates, float velocity,
			MainActivity activity, int maxGapWidth) {
		this.velocity = velocity;
		this.pool = new TemplateObjectPool(templates, activity);
		this.layerEntity = new Entity(0, 0);
		this.maxGapWidth = maxGapWidth;
	}

	public void spawnObject() {
		TemplateSprite sprite = pool.obtainPoolItem();
		if (lastSprite == null) {
			sprite.setPosition(0, 0);
			lastSprite = sprite;
		} else if (lastSprite.getX() < 800) {
			sprite.setX(lastSprite.getX() + lastSprite.getWidth()
					+ NumberUtil.getRandomInt(maxGapWidth));
			sprite.setVelocityX(velocity);
		}
		if (!sprite.hasParent()) {
			this.layerEntity.attachChild(sprite);
		}
	}

	public Entity getLayerEntity() {
		return layerEntity;
	}

}
