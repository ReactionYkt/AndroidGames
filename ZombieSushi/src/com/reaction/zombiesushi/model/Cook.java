package com.reaction.zombiesushi.model;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.extension.physics.box2d.util.Vector2Pool;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Cook {

	private AnimatedSprite body;
	private AnimatedSprite feet;
	private boolean inFlight;
	private boolean hits;

	public Cook(float posX, float posY, ITiledTextureRegion pBodyTextureRegion,
			ITiledTextureRegion pFeetTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		body = new AnimatedSprite(posX, posY, pBodyTextureRegion,
				pVertexBufferObjectManager) {

			@Override
			protected void onManagedUpdate(final float pSecondsElapsed) {
				this.setPosition(feet.getX() - 38, feet.getY() - 99);
				Body physicBody = (Body) feet.getUserData();
				if (physicBody.getLinearVelocity().y == 0) {
					inFlight = false;
				} else {
					inFlight = true;
				}
				int currentFrame = body.getCurrentTileIndex();
				if (currentFrame >= 3 && currentFrame <= 10) {
					hits = true;
				} else {
					hits = false;
				}
				super.onManagedUpdate(pSecondsElapsed);
			}

		};
		feet = new AnimatedSprite(posX + 38, posY + 99, pFeetTextureRegion,
				pVertexBufferObjectManager);
		inFlight = false;
		hits = false;
	}

	public AnimatedSprite getBody() {
		return body;
	}

	public AnimatedSprite getFeet() {
		return feet;
	}

	public boolean isInFlight() {
		return inFlight;
	}

	public boolean isHits() {
		return hits;
	}

	public void setHits(boolean hits) {
		this.hits = hits;
	}

	public void jump(final AnimatedSprite face) {
		final Body body = (Body) face.getUserData();
		final Vector2 velocity = Vector2Pool.obtain(0, 9.8f * -1);
		body.setLinearVelocity(velocity);
		Vector2Pool.recycle(velocity);
		inFlight = true;
	}

}
