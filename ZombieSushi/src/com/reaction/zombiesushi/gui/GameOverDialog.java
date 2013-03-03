package com.reaction.zombiesushi.gui;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

import com.reaction.zombiesushi.GameScreen;
import com.reaction.zombiesushi.MenuScreen;
import com.reaction.zombiesushi.core.ScreenManager;
import com.reaction.zombiesushi.res.Textures;

public class GameOverDialog extends Sprite {
	
	private GameScreen screen;
	
	public GameOverDialog(float posX, float posY, GameScreen screen) {
		super(posX, posY, Textures.gameOverTextureRegion, screen
				.getVertexBufferObjectManager());
		this.screen = screen;
	}
	
	@Override
	public boolean onAreaTouched(TouchEvent event, float x, float y){
		screen.hideGUI();
		ScreenManager.setScene((new MenuScreen(screen.getGame())).run());
		return true;
	}

}
