package com.reaction.zombiesushi;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;

import com.reaction.zombiesushi.core.Screen;
import com.reaction.zombiesushi.res.Textures;

public class GameScreen extends Screen {

	public GameScreen(SimpleBaseGameActivity game) {
		super(game);
		scene.setBackground(new Background(Color.CYAN));
		float posX = game.getEngine().getCamera().getCenterX() - Textures.bananaTextureRegion.getWidth()/2;
		float posY = game.getEngine().getCamera().getCenterY() - Textures.bananaTextureRegion.getHeight()/2;
		AnimatedSprite banana = new AnimatedSprite(	posX, posY, 
													Textures.bananaTextureRegion,
													game.getVertexBufferObjectManager());
		banana.animate(100);
		scene.attachChild(banana);
	}

	@Override
	public Scene run() {
		return scene;
	}

	@Override
	public void destroy() {
	}

}
