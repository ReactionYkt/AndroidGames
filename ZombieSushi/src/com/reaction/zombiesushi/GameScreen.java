package com.reaction.zombiesushi;

import org.andengine.engine.camera.BoundCamera;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;

import android.graphics.Typeface;
import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.reaction.zombiesushi.core.Screen;
import com.reaction.zombiesushi.gui.GUI;
import com.reaction.zombiesushi.model.Cook;
import com.reaction.zombiesushi.model.Level;
import com.reaction.zombiesushi.model.Zombie;
import com.reaction.zombiesushi.res.Textures;
import com.reaction.zombiesushi.util.GameScreenUpdateHandler;
import com.reaction.zombiesushi.util.LevelLoader;
import com.reaction.zombiesushi.util.ZombiePool;

public class GameScreen extends Screen {

	private float CAMERA_WIDTH;
	private float CAMERA_HEIGHT;
	private PhysicsWorld physicsWorld;
	private Cook cook;
	private static ZombiePool zombiePool;
	private Font font;
	private GUI gui;

	public GameScreen(SimpleBaseGameActivity game) {
		super(game);
		scene.registerUpdateHandler(new GameScreenUpdateHandler(this));
		
		this.font = FontFactory.create(this.game.getFontManager(),
				this.game.getTextureManager(), 256, 128,
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32);
		this.font.load();

		CAMERA_WIDTH = game.getEngine().getCamera().getWidth();
		CAMERA_HEIGHT = game.getEngine().getCamera().getHeight();
		physicsWorld = new PhysicsWorld(new Vector2(0, 15f), false);

		float posX = game.getEngine().getCamera().getCenterX() - Textures.cookTextureRegion.getWidth()/2;
		float posY = game.getEngine().getCamera().getCenterY();

		// static background
		SpriteBackground bg = new SpriteBackground(new Sprite(0, 0,
				Textures.backgroundTextureRegion,
				game.getVertexBufferObjectManager()));

		// cook sprites
		cook = new Cook(posX, posY, this);
		zombiePool = new ZombiePool(game, cook);
		AnimatedSprite cookFeet = cook.getFeet();

		Rectangle ground = new Rectangle(0, CAMERA_HEIGHT - 20, CAMERA_WIDTH,
				20, game.getVertexBufferObjectManager());
		ground.setColor(Color.BLUE);

		FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0, 0, 0);


		PhysicsFactory.createBoxBody(this.physicsWorld, ground,
				BodyType.StaticBody, wallFixtureDef);

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
		scene.setBackground(bg);
		scene.attachChild(level.getFirstLayer());
		scene.attachChild(level.getSecondLayer());
		scene.attachChild(ground);
		scene.attachChild(cookFeet);
		scene.registerUpdateHandler(physicsWorld);
		gui = new GUI(this);
		gui.drawLives(cook.getHealth());
		BoundCamera camera = (BoundCamera) game.getEngine().getCamera();
		camera.setHUD(gui.getHud());
		
	}

	@Override
	public Scene run() {
		return this.scene;
	}

	@Override
	public void destroy() {
	}

	public SimpleBaseGameActivity getGame() {
		return this.game;
	}
	
	public VertexBufferObjectManager getVertexBufferObjectManager(){
		return this.game.getVertexBufferObjectManager();
	}
	
	public PhysicsWorld getPhysicsWorld(){
		return this.physicsWorld;
	}
	
	public Font getScoreFont(){
		return this.font;
	}
	
	public void updateScore(int newScore){
		this.gui.updateScores(newScore);
	}
	
	public void updateHealth(int health){
		this.gui.drawLives(health);
	}
	
	public void showGameOver(){
		this.gui.gameOver();
	}

	public void hideGUI(){
		this.gui.hide();
	}
	
	public float getCameraHeight(){
		return this.CAMERA_HEIGHT;
	}
	
	public float getCameraWidth(){
		return this.CAMERA_WIDTH;
	}

	public Cook getCook() {
		return this.cook;
	}

	public void addZombie() {
		Zombie zombie = zombiePool.obtainPoolItem();
		zombie.setPosition(900, 365);
		zombie.setVelocityX(-55);
		zombie.playWalk();
		if (!zombie.hasParent()) {
			this.scene.attachChild(zombie);
		}
		zombie.setDead(false);
		zombie.setVisible(true);
		zombie.setIgnoreUpdate(false);
	}
	
	public void gameOver(){
		this.scene.setIgnoreUpdate(true);
	}

}
