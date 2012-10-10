package com.reaction.zombiesushi;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;

import com.reaction.zombiesushi.core.Screen;
import com.reaction.zombiesushi.res.Textures;

public class GameScreen extends Screen {
	
	private int WIDTH;
	private int HEIGHT;

	public GameScreen(final SimpleBaseGameActivity game) {
		super(game);
		scene.setBackground(new Background(Color.CYAN));
		
		WIDTH = game.getEngine().getSurfaceWidth();
		HEIGHT = game.getEngine().getSurfaceHeight();
		
		float posX = game.getEngine().getCamera().getCenterX()
				- Textures.cookTextureRegion.getWidth() / 2;
		float posY = game.getEngine().getCamera().getCenterY()
				- Textures.cookTextureRegion.getHeight() / 2;
		
		AnimatedSprite cook = new AnimatedSprite(posX, posY,
				Textures.cookTextureRegion, game.getVertexBufferObjectManager());
		AnimatedSprite feet = new AnimatedSprite(posX + 38, posY + 99,
				Textures.feetTextureRegion, game.getVertexBufferObjectManager());
		AnimatedSprite toggleButton = new AnimatedSprite(WIDTH - 128, HEIGHT - 128,
				Textures.toggleButtonTextureRegion, game.getVertexBufferObjectManager()){
			
			@Override
			public boolean onAreaTouched(TouchEvent event, float x, float y){
				this.animate(100);
				return true;
			}
			
		};
		
		cook.animate(100);
		feet.animate(100);
		//toggleButton.animate(100);
		scene.attachChild(cook);
		scene.attachChild(feet);
		scene.attachChild(toggleButton);
		scene.registerTouchArea(toggleButton);
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
