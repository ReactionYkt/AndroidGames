package com.reaction.zombiesushi.model;

import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.reaction.zombiesushi.util.NumberUtil;
import com.reaction.zombiesushi.util.ObstaclePool;

public class Obstacle extends Sprite {

	private static TextureRegion[] STATIC_REGIONS;
	private Body physicBody;
	private ObstaclePool pool;

	public Obstacle(float x, float y, TextureRegion textureRegion,
			PhysicsWorld physicsWorld,
			VertexBufferObjectManager vertexBufferObjectManager,
			ObstaclePool obstaclePool) {
		super(x, y, textureRegion, vertexBufferObjectManager);
		this.physicBody = PhysicsFactory.createBoxBody(physicsWorld, this,
				BodyType.KinematicBody,
				PhysicsFactory.createFixtureDef(0, 0, 0));
		physicsWorld.registerPhysicsConnector(new PhysicsConnector(this,
				physicBody, true, true));
		this.physicBody.setLinearVelocity(-50, 0);
		this.pool = obstaclePool;
	}
	
	public static void init(TextureRegion[] regions){
		STATIC_REGIONS = regions;
	}
	
	public static TextureRegion getRandomRegion(){
		int len = STATIC_REGIONS.length;
		return STATIC_REGIONS[NumberUtil.getRandomInt(len)];
	}
	
	public void enable(){
		this.setVisible(true);
		this.setIgnoreUpdate(false);
	}
	
	public void disable(){
		this.setVisible(false);
		this.setIgnoreUpdate(true);
	}

	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		if(this.getX() < 0){
			this.pool.recyclePoolItem(this);
		}
		super.onManagedUpdate(pSecondsElapsed);
	}

}
