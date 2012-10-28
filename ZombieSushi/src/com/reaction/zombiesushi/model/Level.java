package com.reaction.zombiesushi.model;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.batch.SpriteBatch;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;

public class Level {

	private final SpriteBatch firstLayer;
	private final SpriteBatch secondLayer;
	private final PhysicsHandler firstLayerPhysicsHandler;
	private final PhysicsHandler secondLayerPhysicsHandler;
	private final InputStream inputStream;

	public Level(BitmapTextureAtlas firstLayerTextureAtlas, BitmapTextureAtlas secondLayerTextureAtlas, SimpleBaseGameActivity game,
			String levelName) throws IOException {
		this.firstLayer = new SpriteBatch(firstLayerTextureAtlas, 20,
				game.getVertexBufferObjectManager());
		this.secondLayer = new SpriteBatch(secondLayerTextureAtlas, 20,
				game.getVertexBufferObjectManager());
		this.firstLayerPhysicsHandler = new PhysicsHandler(firstLayer);
		this.secondLayerPhysicsHandler = new PhysicsHandler(secondLayer);
		firstLayer.setPosition(0, 0);
		secondLayer.setPosition(0, 0);
		firstLayer.registerUpdateHandler(firstLayerPhysicsHandler);
		secondLayer.registerUpdateHandler(secondLayerPhysicsHandler);
		inputStream = game.getAssets().open("levels/" + levelName);
	}

	public void addSpriteToFirstLayer(ITextureRegion textureRegion,
			final float x, final float y, final float width, final float height) {
		firstLayer.drawWithoutChecks(textureRegion, x, y, width, height,
				Color.WHITE_ABGR_PACKED_FLOAT);
	}

	public void addSpriteToSecondLayer(ITextureRegion textureRegion,
			final float x, final float y, final float width, final float height) {
		secondLayer.drawWithoutChecks(textureRegion, x, y, width, height,
				Color.WHITE_ABGR_PACKED_FLOAT);
	}

	public SpriteBatch getFirstLayer() {
		return firstLayer;
	}

	public SpriteBatch getSecondLayer() {
		return secondLayer;
	}

	public void setFirstLayerSpeed(float speed) {
		this.firstLayerPhysicsHandler.setVelocityX(-speed);
	}

	public void setSecondLayerSpeed(float speed) {
		this.secondLayerPhysicsHandler.setVelocityX(-speed);
	}

	public void submit() {
		firstLayer.submit();
		secondLayer.submit();
	}

	public InputStream getInputStream() {
		return inputStream;
	}

}
