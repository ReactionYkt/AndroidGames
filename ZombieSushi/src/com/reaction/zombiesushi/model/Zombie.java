package com.reaction.zombiesushi.model;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.pool.GenericPool;

import com.reaction.zombiesushi.res.Textures;

public class Zombie {

	private AnimatedSprite walkingZombie;
	private AnimatedSprite bleedingZombie;
	private PhysicsHandler physicsHandler;
	private boolean isDead;

	public Zombie(float x, float y,
			VertexBufferObjectManager pVertexBufferObjectManager,
			final Cook cook, final GenericPool<Zombie> pool) {
		this.isDead = false;
		walkingZombie = new AnimatedSprite(x, y, Textures.walkingZombieRegion,
				pVertexBufferObjectManager) {

			@Override
			protected void onManagedUpdate(final float pSecondsElapsed) {
				if (walkingZombie.collidesWith(cook.getBody()) && cook.isHits()) {
					if (!Zombie.this.isDead) {
						walkingZombie.stopAnimation();
						walkingZombie.setVisible(false);
						bleedingZombie.setVisible(true);
						bleedingZombie.animate(100, false);
						Zombie.this.isDead = true;
					}
				}
				if(pool!=null){
					if(walkingZombie.getX() + walkingZombie.getWidth() < 0 || walkingZombie.getY() > 480){
						walkingZombie.setVisible(false);
						bleedingZombie.setVisible(false);
						walkingZombie.setIgnoreUpdate(true);
						pool.recyclePoolItem(Zombie.this);
					}
				}
				super.onManagedUpdate(pSecondsElapsed);
			}

		};
		
		bleedingZombie = new AnimatedSprite(x, y,
				Textures.bleedingZombieRegion, pVertexBufferObjectManager) {

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
	
	public void setPosition(float x, float y){
		this.walkingZombie.setPosition(x, y);
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

}
