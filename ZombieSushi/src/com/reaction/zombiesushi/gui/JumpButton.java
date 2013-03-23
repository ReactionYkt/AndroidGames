package com.reaction.zombiesushi.gui;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.input.touch.TouchEvent;

import com.reaction.zombiesushi.GameScreen;
import com.reaction.zombiesushi.model.Cook;
import com.reaction.zombiesushi.res.Textures;

public class JumpButton extends AnimatedSprite {

	private Cook cook;

	public JumpButton(float posX, float posY, GameScreen screen) {
		super(posX, posY, Textures.BUTTON_REGION, screen
				.getVertexBufferObjectManager());
		this.cook = screen.getCook();
	}

	@Override
	public boolean onAreaTouched(TouchEvent event, float x, float y) {
		this.animate(100, false);
		this.cook.jump();
		return true;
	}

}
