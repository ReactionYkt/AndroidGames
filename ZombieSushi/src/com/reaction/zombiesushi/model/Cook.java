package com.reaction.zombiesushi.model;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Cook {
	
	private final PhysicsHandler bodyPhysicsHandler;
	private final PhysicsHandler feetPhysicsHandler;
	private AnimatedSprite body;
	private AnimatedSprite feet;

	public Cook(float posX, float posY, ITiledTextureRegion pBodyTextureRegion, ITiledTextureRegion pFeetTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		body = new AnimatedSprite(posX, posY, pBodyTextureRegion, pVertexBufferObjectManager);
		feet = new AnimatedSprite(posX + 38, posY + 99, pFeetTextureRegion, pVertexBufferObjectManager);
		this.bodyPhysicsHandler = new PhysicsHandler(body);
		this.feetPhysicsHandler = new PhysicsHandler(feet);
		this.body.registerUpdateHandler(bodyPhysicsHandler);
		this.feet.registerUpdateHandler(feetPhysicsHandler);
		this.bodyPhysicsHandler.setVelocityX(50);
		this.feetPhysicsHandler.setVelocityX(50);
	}
	
	public AnimatedSprite getBody() {
		return body;
	}

	public AnimatedSprite getFeet() {
		return feet;
	}

}
