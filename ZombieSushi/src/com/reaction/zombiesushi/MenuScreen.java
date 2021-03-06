package com.reaction.zombiesushi;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;

import com.reaction.zombiesushi.core.Screen;
import com.reaction.zombiesushi.core.ScreenManager;
import com.reaction.zombiesushi.res.Textures;

public class MenuScreen extends Screen {

	public MenuScreen(final MainActivity game) {
		super(game);
		scene.setBackground(new Background(Color.CYAN));
		float posX = game.getEngine().getCamera().getCenterX() - Textures.START_BUTTON_REGION.getWidth()/2;
		float posY = game.getEngine().getCamera().getCenterY() - Textures.START_BUTTON_REGION.getHeight()/2;
		Sprite startButton = new Sprite(posX, posY, 
										Textures.START_BUTTON_REGION,
										game.getVertexBufferObjectManager()){
			
			@Override
			public boolean onAreaTouched(TouchEvent event, float x, float y){
				ScreenManager.setScene((new GameScreen(game)).run());
				return true;
			}
			
		};
		scene.attachChild(startButton);
		scene.registerTouchArea(startButton);
		scene.setTouchAreaBindingOnActionDownEnabled(true);
	}

	@Override
	public Scene run() {
		return scene;
	}

	@Override
	public void destroy() {
		
	}

}
