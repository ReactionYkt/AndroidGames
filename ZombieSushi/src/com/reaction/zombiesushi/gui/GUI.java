package com.reaction.zombiesushi.gui;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.sprite.batch.SpriteBatch;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

import com.reaction.zombiesushi.GameScreen;
import com.reaction.zombiesushi.res.Textures;

public class GUI {
	
	private HUD hud;
	private AttackButton attackButton;
	private JumpButton jumpButton;
	private SpriteBatch batch;
	private GameOverDialog gameOver;
	private GameScreen screen;
	private Font font;
	private Text text;
	
	public GUI(GameScreen screen){
		this.screen = screen;
		this.hud = new HUD();
		this.batch = new SpriteBatch(Textures.HEART_ATLAS, 12, screen.getVertexBufferObjectManager());
		this.attackButton = new AttackButton(screen.getCameraWidth() - 128,
				screen.getCameraHeight() - 128, screen);
		this.jumpButton = new JumpButton(0, screen.getCameraHeight() - 128, screen);
		this.gameOver = new GameOverDialog(240, 180, screen);
		this.gameOver.setVisible(false);
		this.font = screen.getScoreFont();
		this.text = new Text(100, 40, this.font, "0000000", new TextOptions(
				HorizontalAlign.CENTER),
				this.screen.getVertexBufferObjectManager());
		this.hud.attachChild(attackButton);
		this.hud.attachChild(jumpButton);
		this.hud.attachChild(batch);
		this.hud.attachChild(text);
		this.hud.attachChild(gameOver);
		this.hud.registerTouchArea(attackButton);
		this.hud.registerTouchArea(jumpButton);
		hud.setTouchAreaBindingOnActionDownEnabled(true);
	}
	
	public HUD getHud(){
		return this.hud;
	}
	
	public void drawLives(int health){
		batch.reset();
		float x = this.screen.getCameraWidth() - 100;
		float y = 10;
		TextureRegion heart = Textures.HEART_TEXTURE_REGION;
		float width = heart.getWidth();
		for(int i = 0; i < health; i++){
			batch.drawWithoutChecks(heart, x, y, width, heart.getHeight(),
					Color.WHITE_ABGR_PACKED_FLOAT);
			width = -width;
			if(width < 0){
				x += 16;
			} else {
				x += 8;
			}
		}
		batch.submit();
	}
	
	public void updateScores(int newScore){
		this.text.setText(String.valueOf(newScore));
	}
	
	public void gameOver(){
		this.gameOver.setVisible(true);
		this.hud.registerTouchArea(gameOver);
	}
	
	public void hide(){
		this.hud.setVisible(false);
	}

}
