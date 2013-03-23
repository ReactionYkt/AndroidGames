package com.reaction.zombiesushi.model;

import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.opengl.texture.region.TextureRegion;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.reaction.zombiesushi.GameScreen;
import com.reaction.zombiesushi.res.Textures;
import com.reaction.zombiesushi.util.RandomUtil;

public class Obstacle extends Sprite {

	public enum ObstacleType {
		THRASH_CAN(0, Textures.OBSTACLE_REGION);

		private int code;
		private TextureRegion texture;

		private ObstacleType(int code, TextureRegion texture) {
			this.code = code;
			this.texture = texture;
		}

		public static ObstacleType getRandom() {
			return valueOf(RandomUtil.getInt(values().length));
		}

		public int getCode() {
			return code;
		}

		public TextureRegion getTexture() {
			return texture;
		}

		public static ObstacleType valueOf(int code) {
			ObstacleType result = null;
			for (ObstacleType type : values()) {
				if (type.code == code) {
					result = type;
					break;
				}
			}
			return result;
		}

	}

	private Body physicBody;

	public Obstacle(float x, float y, ObstacleType type, GameScreen screen) {
		super(x, y, type.getTexture(), screen.getVertexBufferObjectManager());
		this.physicBody = PhysicsFactory.createBoxBody(
				screen.getPhysicsWorld(), this, BodyType.KinematicBody,
				PhysicsFactory.createFixtureDef(0, 0, 0));
		screen.getPhysicsWorld().registerPhysicsConnector(
				new PhysicsConnector(this, physicBody, true, true));
		this.physicBody.setLinearVelocity(-50, 0);
	}

}
