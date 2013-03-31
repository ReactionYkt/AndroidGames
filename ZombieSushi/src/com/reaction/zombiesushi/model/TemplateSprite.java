package com.reaction.zombiesushi.model;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class TemplateSprite extends Sprite {
	
	private TemplateObjectPool pool;
	private PhysicsHandler physicsHandler;

	public TemplateSprite(float x, float y, ITextureRegion textureRegion,
			VertexBufferObjectManager vertexBufferObjectManager, TemplateObjectPool pool) {
		super(x, y, textureRegion, vertexBufferObjectManager);
		this.pool = pool;
		this.physicsHandler = new PhysicsHandler(this);
	}

	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		if(this.getX() + this.getWidth() < 0){
			this.pool.recyclePoolItem(this);
		}
		super.onManagedUpdate(pSecondsElapsed);
	}
	
	public void enable(){
		this.setIgnoreUpdate(false);
		this.setVisible(true);
	}
	
	public void disable(){
		this.setIgnoreUpdate(true);
		this.setVisible(false);
	}
	
	public void setVelocityX(float velocityX){
		this.physicsHandler.setVelocityX(velocityX);
	}

}
