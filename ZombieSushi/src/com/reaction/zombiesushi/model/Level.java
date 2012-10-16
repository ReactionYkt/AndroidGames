package com.reaction.zombiesushi.model;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.batch.SpriteBatch;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

public class Level {
	
	private final String absolutePath;
	private final SpriteBatch firstLayer;
	private final SpriteBatch secondLayer;
	private final PhysicsHandler firstLayerPhysicsHandler;
	private final PhysicsHandler secondLayerPhysicsHandler;
	
	public Level(BitmapTextureAtlas textureAtlas, VertexBufferObjectManager vertexObjectBufferManager, String levelName){
		this.absolutePath = levelName;
		this.firstLayer = new SpriteBatch(textureAtlas, 20, vertexObjectBufferManager);
		this.secondLayer = new SpriteBatch(textureAtlas, 20, vertexObjectBufferManager);
		this.firstLayerPhysicsHandler = new PhysicsHandler(firstLayer);
		this.secondLayerPhysicsHandler = new PhysicsHandler(secondLayer);
		firstLayer.registerUpdateHandler(firstLayerPhysicsHandler);
		secondLayer.registerUpdateHandler(secondLayerPhysicsHandler);
	}
	
	public void addSpriteToFirstLayer(ITextureRegion textureRegion, final float x, final float y, final float width, final float height){
		firstLayer.drawWithoutChecks(textureRegion, x, y, width, height, Color.WHITE_ABGR_PACKED_FLOAT);
	}
	
	public void addSpriteToSecondLayer(ITextureRegion textureRegion, final float x, final float y, final float width, final float height){
		secondLayer.drawWithoutChecks(textureRegion, x, y, width, height, Color.WHITE_ABGR_PACKED_FLOAT);
	}
	
	public SpriteBatch getFirstLayer(){
		return firstLayer;
	}
	
	public SpriteBatch getSecondLayer(){
		return secondLayer;
	}
	
	public void setFirstLevelSpeed(float speed){
		this.firstLayerPhysicsHandler.setVelocityX(-speed);
	}
	
	public void setSecondLevelSpeed(float speed){
		this.secondLayerPhysicsHandler.setVelocityX(-speed);
	}
	
	public String getAbsolutePath(){
		return this.absolutePath;
	}
	
	public void submit(){
		firstLayer.submit();
		secondLayer.submit();
	}

}
