package com.reaction.zombiesushi.model;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.reaction.zombiesushi.res.Textures;

public class Zombie {

	private AnimatedSprite walkingZombie;
	private AnimatedSprite bleedingZombie;
	private PhysicsHandler physicsHandler;

	public Zombie(float x, float y,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		walkingZombie = new AnimatedSprite(x, y, Textures.walkingZombieRegion,
				pVertexBufferObjectManager);
		bleedingZombie = new AnimatedSprite(x, y,
				Textures.bleedingZombieRegion, pVertexBufferObjectManager){
			
			@Override
			protected void onManagedUpdate(final float pSecondsElapsed) {
				this.setPosition(walkingZombie.getX(), walkingZombie.getY());
				super.onManagedUpdate(pSecondsElapsed);
			}
			
		};
		bleedingZombie.setVisible(false);
		physicsHandler = new PhysicsHandler(walkingZombie);
		walkingZombie.registerUpdateHandler(physicsHandler);
		// physicsHandler.setAccelerationY(9.8f);
	}

	public AnimatedSprite getWalkingZombie() {
		return walkingZombie;
	}
	
	public AnimatedSprite getBleedingZombie() {
		return bleedingZombie;
	}

	public void setVelocityY(float yVelocity) {
		this.physicsHandler.setVelocityY(yVelocity);
	}

	public void setVelocityX(float yVelocity) {
		this.physicsHandler.setVelocityX(yVelocity);
	}

}
