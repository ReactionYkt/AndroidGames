package com.reaction.zombiesushi;

import java.util.Random;

import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.extension.physics.box2d.util.Vector2Pool;
import org.andengine.input.touch.TouchEvent;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.pool.GenericPool;
import org.andengine.util.color.Color;

import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.reaction.zombiesushi.core.Screen;
import com.reaction.zombiesushi.model.Cook;
import com.reaction.zombiesushi.model.Level;
import com.reaction.zombiesushi.model.Zombie;
import com.reaction.zombiesushi.res.Textures;
import com.reaction.zombiesushi.util.LevelLoader;

public class GameScreen extends Screen {

	private float CAMERA_WIDTH;
	private float CAMERA_HEIGHT;
	private PhysicsWorld mPhysicsWorld;
	private Cook cook;
	private Body cookPhysicBody;
	// private Body zombiePhysicBody;
	private static GenericPool<Zombie> zombiePool;
	private Random rg;
	private float timer;

	public GameScreen(final SimpleBaseGameActivity game) {
		super(game);
		scene.setBackground(new Background(Color.CYAN));
		rg = new Random();
		zombiePool = new GenericPool<Zombie>() {

			@Override
			protected Zombie onAllocatePoolItem() {
				return new Zombie(900, 400,
						game.getVertexBufferObjectManager(), cook, this);
			}

		};

		timer = 0;

		scene.registerUpdateHandler(new IUpdateHandler() {

			@Override
			public void onUpdate(float pSecondsElapsed) {
				timer += pSecondsElapsed;
				if (timer > 1) {
					if (rg.nextInt(100) < 75) {
						addZombie();
					}
					timer = 0;
				}
			}

			@Override
			public void reset() {
			}

		});

		CAMERA_WIDTH = game.getEngine().getCamera().getWidth();
		CAMERA_HEIGHT = game.getEngine().getCamera().getHeight();

		float posX = 0;
		float posY = game.getEngine().getCamera().getCenterY()
				- Textures.cookTextureRegion.getHeight() / 2 + 5;

		// static background
		SpriteBackground bg = new SpriteBackground(new Sprite(0, 0,
				Textures.backgroundTextureRegion,
				game.getVertexBufferObjectManager()));

		// cook sprites
		cook = new Cook(posX, posY, Textures.cookTextureRegion,
				Textures.feetTextureRegion, game.getVertexBufferObjectManager());
		final AnimatedSprite cookBody = cook.getBody();
		final AnimatedSprite cookFeet = cook.getFeet();

		// zombie
		/*
		 * final AnimatedSprite zombie = new AnimatedSprite(400, 400,
		 * Textures.walkingZombieRegion, game.getVertexBufferObjectManager());
		 */

		Zombie zombie = new Zombie(400, 400,
				game.getVertexBufferObjectManager(), cook, null);
		zombie.setVelocityX(-55);
		final AnimatedSprite walkingZombie = zombie.getWalkingZombie();
		final AnimatedSprite bleedingZombie = zombie.getBleedingZombie();

		// controls
		AnimatedSprite toggleButton = new AnimatedSprite(CAMERA_WIDTH - 128,
				CAMERA_HEIGHT - 128, Textures.toggleButtonTextureRegion,
				game.getVertexBufferObjectManager()) {

			@Override
			public boolean onAreaTouched(TouchEvent event, float x, float y) {
				this.animate(100, false);
				if (!cook.isHits()) {
					cookBody.animate(75, false);
					cook.setHits(true);
				}
				return true;
			}

		};

		AnimatedSprite jumpButton = new AnimatedSprite(CAMERA_WIDTH - 256,
				CAMERA_HEIGHT - 128, Textures.toggleButtonTextureRegion,
				game.getVertexBufferObjectManager()) {

			@Override
			public boolean onAreaTouched(TouchEvent event, float x, float y) {
				this.animate(100, false);
				if (!cook.isInFlight()) {
					cook.jump(cookFeet);
				}
				return true;
			}

		};

		this.mPhysicsWorld = new PhysicsWorld(new Vector2(0, 9.8f), false);

		final Rectangle ground = new Rectangle(0, CAMERA_HEIGHT - 20,
				CAMERA_WIDTH, 20, game.getVertexBufferObjectManager());
		ground.setColor(Color.BLUE);

		final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0, 0,
				0);

		final FixtureDef objectFixtureDef = PhysicsFactory.createFixtureDef(0,
				0, 0);

		PhysicsFactory.createBoxBody(this.mPhysicsWorld, ground,
				BodyType.StaticBody, wallFixtureDef);

		cookPhysicBody = PhysicsFactory.createBoxBody(this.mPhysicsWorld,
				cookFeet, BodyType.DynamicBody, objectFixtureDef);

		// zombiePhysicBody = PhysicsFactory.createBoxBody(mPhysicsWorld,
		// zombie, BodyType.KinematicBody, objectFixtureDef);

		this.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(
				cookFeet, cookPhysicBody, true, true));
		// this.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(
		// zombie, zombiePhysicBody, true, true));
		// zombiePhysicBody.setLinearVelocity(-2, 0);
		this.mPhysicsWorld.setGravity(Vector2Pool.obtain(0, 9.8f));

		cookFeet.setUserData(cookPhysicBody);

		BoundCamera camera = (BoundCamera) game.getEngine().getCamera();

		LevelLoader levelLoader = null;
		Level level = null;

		try {
			level = new Level(Textures.level1Layer1Atlas,
					Textures.level1Layer2Atlas, game, "level.xml");
			levelLoader = new LevelLoader(game.getVertexBufferObjectManager(),
					level);
			levelLoader.loadLevel();
			level.setFirstLayerSpeed(25);
			level.setSecondLayerSpeed(50);
		} catch (Exception ex) {
			Log.d("AndEngine", ex.getMessage());
		}

		HUD hud = new HUD();
		hud.attachChild(toggleButton);
		hud.attachChild(jumpButton);
		camera.setHUD(hud);
		scene.setBackground(bg);
		scene.attachChild(level.getFirstLayer());
		scene.attachChild(level.getSecondLayer());
		cookFeet.animate(100);
		walkingZombie.animate(100);
		scene.attachChild(ground);
		scene.attachChild(cookBody);
		scene.attachChild(cookFeet);
		scene.attachChild(walkingZombie);
		scene.attachChild(bleedingZombie);
		scene.registerUpdateHandler(mPhysicsWorld);
		hud.registerTouchArea(toggleButton);
		hud.registerTouchArea(jumpButton);
		hud.setTouchAreaBindingOnActionDownEnabled(true);
	}

	@Override
	public Scene run() {
		return scene;
	}

	@Override
	public void destroy() {
	}

	private void addZombie() {
		Zombie zombie = zombiePool.obtainPoolItem();
		zombie.setPosition(900, 400);
		zombie.setVelocityX(-55);
		zombie.getWalkingZombie().animate(100);
		if (!zombie.getWalkingZombie().hasParent()) {
			scene.attachChild(zombie.getWalkingZombie());
			scene.attachChild(zombie.getBleedingZombie());
		}
		zombie.setDead(false);
		zombie.getWalkingZombie().setVisible(true);
		zombie.getWalkingZombie().setIgnoreUpdate(false);
	}

}
