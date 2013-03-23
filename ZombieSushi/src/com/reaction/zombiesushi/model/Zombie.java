package com.reaction.zombiesushi.model;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.pool.GenericPool;

import com.reaction.zombiesushi.res.Textures;

public class Zombie extends AnimatedSprite {

	private static final int BYTE_START_FRAME = 4;
	private static final int BYTE_END_FRAME = 10;
	private static final int SCORE = 1;
	private static final long[] WALK_FRAMES_DURATIONS = { 50, 50, 50, 50, 50,
			50, 50, 50, 50, 50, 50, 50 };
	private static final long[] DEATH_FRAMES_DURATIONS = { 50, 50, 50, 50, 50,
			50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50 };

	private PhysicsHandler physicsHandler;
	private boolean dead;
	private Cook cook;
	private GenericPool<Zombie> pool;
	private boolean done = false;

	public Zombie(float x, float y,
			VertexBufferObjectManager pVertexBufferObjectManager, Cook cook,
			GenericPool<Zombie> pool) {
		super(x, y, Textures.ZOMBIE_REGION, pVertexBufferObjectManager);
		this.cook = cook;
		this.dead = false;
		this.pool = pool;
		physicsHandler = new PhysicsHandler(this);
		this.registerUpdateHandler(physicsHandler);
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
		if (this.collidesWith(cook.getBodyBound())) {
			if (cook.isAttacks()) {
				if (!this.dead) {
					this.playDeath();
					this.dead = true;
					this.cook.addScore(SCORE);
				}
			} else if (this.isBytes() && !done) {
				cook.gotBitten();
				done = true;
			}
		}
		if (pool != null) {
			if (this.getX() + this.getWidth() < 0 || this.getY() > 480) {
				this.setVisible(false);
				this.setIgnoreUpdate(true);
				this.pool.recyclePoolItem(this);
			}
		}
		super.onManagedUpdate(pSecondsElapsed);
	}

	public void setVelocityY(float yVelocity) {
		this.physicsHandler.setVelocityY(yVelocity);
	}

	public void setVelocityX(float yVelocity) {
		this.physicsHandler.setVelocityX(yVelocity);
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	private boolean isBytes() {
		int currentFrame = this.getCurrentTileIndex();
		boolean result = currentFrame >= BYTE_START_FRAME && currentFrame <= BYTE_END_FRAME;
		return result;
	}

	public void playWalk() {
		this.animate(WALK_FRAMES_DURATIONS, 0, 11, true);
	}

	public void playDeath() {
		this.animate(DEATH_FRAMES_DURATIONS, 12, 27, false);
	}

}
