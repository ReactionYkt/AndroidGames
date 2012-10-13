package com.reaction.zombiesushi;

import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.hud.HUD;
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
import org.andengine.util.color.Color;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.reaction.zombiesushi.core.Screen;
import com.reaction.zombiesushi.model.Cook;
import com.reaction.zombiesushi.res.Textures;

public class GameScreen extends Screen {

	private int CAMERA_WIDTH;
	private int CAMERA_HEIGHT;
	private PhysicsWorld mPhysicsWorld;
	private Body body;

	public GameScreen(final SimpleBaseGameActivity game) {
		super(game);
		scene.setBackground(new Background(Color.CYAN));

		CAMERA_WIDTH = game.getEngine().getSurfaceWidth();
		CAMERA_HEIGHT = game.getEngine().getSurfaceHeight();

		float posX = 0;
		float posY = game.getEngine().getCamera().getCenterY()
				- Textures.cookTextureRegion.getHeight() / 2 + 5;

		SpriteBackground bg = new SpriteBackground(new Sprite(0, 0,
				Textures.backgroundTextureRegion,
				game.getVertexBufferObjectManager()));
		final Cook cook = new Cook(posX, posY, Textures.cookTextureRegion,
				Textures.feetTextureRegion, game.getVertexBufferObjectManager());
		final AnimatedSprite cookBody = cook.getBody();
		final AnimatedSprite cookFeet = cook.getFeet();
		AnimatedSprite toggleButton = new AnimatedSprite(CAMERA_WIDTH - 128,
				CAMERA_HEIGHT - 128, Textures.toggleButtonTextureRegion,
				game.getVertexBufferObjectManager()) {

			@Override
			public boolean onAreaTouched(TouchEvent event, float x, float y) {
				this.animate(100, false);
				cookBody.animate(100, false);
				return true;
			}

		};

		AnimatedSprite jumpButton = new AnimatedSprite(CAMERA_WIDTH - 256,
				CAMERA_HEIGHT - 128, Textures.toggleButtonTextureRegion,
				game.getVertexBufferObjectManager()) {

			@Override
			public boolean onAreaTouched(TouchEvent event, float x, float y) {
				this.animate(100, false);
				if(!cook.isInFlight()){
					cook.jump(cookFeet);
				}
				return true;
			}

		};

		this.mPhysicsWorld = new PhysicsWorld(new Vector2(0, 9.8f), false);
		
		final Rectangle ground = new Rectangle(0, CAMERA_HEIGHT - 2, CAMERA_WIDTH, 2, game.getVertexBufferObjectManager());
		ground.setColor(Color.BLUE);
		
		final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0, 0, 0);
		
		final FixtureDef objectFixtureDef = PhysicsFactory.createFixtureDef(0, 0, 0);
		
		PhysicsFactory.createBoxBody(this.mPhysicsWorld, ground, BodyType.StaticBody, wallFixtureDef);
		
		body = PhysicsFactory.createBoxBody(this.mPhysicsWorld, cookFeet, BodyType.DynamicBody, objectFixtureDef);
		
		this.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(cookFeet, body, true, true));
		this.mPhysicsWorld.setGravity(Vector2Pool.obtain(2, 9.8f));
		
		cookFeet.setUserData(body);
		
		BoundCamera camera = (BoundCamera) game.getEngine().getCamera();
		camera.setChaseEntity(cookFeet);
		HUD hud = new HUD();
		hud.attachChild(toggleButton);
		hud.attachChild(jumpButton);
		camera.setHUD(hud);
		scene.setBackground(bg);
		cookFeet.animate(100);
		scene.attachChild(ground);
		scene.attachChild(cookBody);
		scene.attachChild(cookFeet);
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

}
