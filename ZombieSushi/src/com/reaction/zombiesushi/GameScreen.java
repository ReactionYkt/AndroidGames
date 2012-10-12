package com.reaction.zombiesushi;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;

import com.reaction.zombiesushi.core.Screen;
import com.reaction.zombiesushi.model.Cook;
import com.reaction.zombiesushi.res.Textures;

public class GameScreen extends Screen {

	private int WIDTH;
	private int HEIGHT;

	public GameScreen(final SimpleBaseGameActivity game) {
		super(game);
		scene.setBackground(new Background(Color.CYAN));

		WIDTH = game.getEngine().getSurfaceWidth();
		HEIGHT = game.getEngine().getSurfaceHeight();

		float posX = 0;
		float posY = game.getEngine().getCamera().getCenterY()
				- Textures.cookTextureRegion.getHeight() / 2 + 5;

		SpriteBackground bg = new SpriteBackground(new Sprite(0, 0,
				Textures.backgroundTextureRegion,
				game.getVertexBufferObjectManager()));
		Cook cook = new Cook(posX, posY,
				Textures.cookTextureRegion, Textures.feetTextureRegion,game.getVertexBufferObjectManager());
		final AnimatedSprite cookBody = cook.getBody();
		final AnimatedSprite cookFeet = cook.getFeet();
		AnimatedSprite toggleButton = new AnimatedSprite(WIDTH - 128,
				HEIGHT - 128, Textures.toggleButtonTextureRegion,
				game.getVertexBufferObjectManager()) {

			@Override
			public boolean onAreaTouched(TouchEvent event, float x, float y) {
				this.animate(100, false);
				cookBody.animate(100, false);
				return true;
			}

		};
		
		scene.setBackground(bg);
		cookFeet.animate(100);
		scene.attachChild(cookBody);
		scene.attachChild(cookFeet);
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
