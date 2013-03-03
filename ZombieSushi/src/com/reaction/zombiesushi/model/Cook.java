package com.reaction.zombiesushi.model;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.util.Vector2Pool;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.reaction.zombiesushi.GameScreen;
import com.reaction.zombiesushi.res.Textures;

public class Cook {

	private static final int HEALTH_INIT_VALUE = 6;
	private static final int SLASH_START_FRAME = 1;
	private static final int SLASH_END_FRAME = 5;
	private AnimatedSprite body;
	private AnimatedSprite feet;
	private int health;
	private int scores;
	private Rectangle bodyBound;
	private Body physicBody;
	private GameScreen screen;

	public Cook(float posX, float posY, GameScreen screen) {
		this.screen = screen;
		body = new AnimatedSprite(-38, -99, Textures.cookTextureRegion,
				screen.getVertexBufferObjectManager());
		this.feet = new AnimatedSprite(posX, posY, Textures.feetTextureRegion,
				screen.getVertexBufferObjectManager());
		feet.attachChild(body);
		this.bodyBound = new Rectangle(50, 62, 73, 57,
				screen.getVertexBufferObjectManager());
		this.bodyBound.setVisible(false);
		this.physicBody = PhysicsFactory.createBoxBody(
				screen.getPhysicsWorld(), feet, BodyType.DynamicBody,
				PhysicsFactory.createFixtureDef(0, 0, 0));
		this.screen.getPhysicsWorld().registerPhysicsConnector(
				new PhysicsConnector(feet, physicBody, true, true));
		body.attachChild(bodyBound);
		this.health = HEALTH_INIT_VALUE;
		this.scores = 0;
		this.feet.animate(100);
	}

	public AnimatedSprite getBody() {
		return body;
	}

	public AnimatedSprite getFeet() {
		return feet;
	}

	public Rectangle getBodyBound() {
		return bodyBound;
	}

	public boolean isInFlight() {
		if (physicBody.getLinearVelocity().y == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isAttacks() {
		boolean attacks = false;
		int currentFrame = body.getCurrentTileIndex();
		if (currentFrame >= SLASH_START_FRAME
				&& currentFrame <= SLASH_END_FRAME) {
			attacks = true;
		} else {
			attacks = false;
		}
		return attacks;
	}

	public boolean isDead() {
		return health > 0 ? false : true;
	}

	public void slash() {
		if (!this.isAttacks()) {
			this.getBody().animate(75, false);
		}
	}

	public int getHealth() {
		return this.health;
	}

	public void gotBitten() {
		this.health--;
		this.screen.updateHealth(health);
		if (this.health == 0) {
			this.screen.showGameOver();
		}
	}

	public void addScore(int score) {
		this.scores++;
		this.screen.updateScore(scores);
	}

	public void heal() {
		if (this.health < HEALTH_INIT_VALUE) {
			this.health++;
		}
	}

	public void jump() {
		if (!this.isInFlight()) {
			Vector2 velocity = Vector2Pool.obtain(0, -13f);
			physicBody.setLinearVelocity(velocity);
			Vector2Pool.recycle(velocity);
		}
	}

}
