package com.reaction.zombiesushi.util;

import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.pool.GenericPool;

import com.reaction.zombiesushi.model.Obstacle;

public class ObstaclePool extends GenericPool<Obstacle> {

	private static final int DEFAULT_POS_X = 1000;
	private static final int DEFAULT_POS_Y = 365;
	private static final int POOL_GROWTH = 3;
	private static final int AVAILABLE_ITEMS_MAXIMUM = 20;
	private static final int INITIAL_SIZE = 0;
	private int INITIAL_COUNT = 0;
	private PhysicsWorld physicsWorld;
	private VertexBufferObjectManager vertexObjectBufferManager;
	private TextureRegion[] regions;

	public ObstaclePool(PhysicsWorld physicsWorld,
			VertexBufferObjectManager vertexObjectBufferManager,
			TextureRegion[] regions) {
		super(INITIAL_SIZE, POOL_GROWTH, AVAILABLE_ITEMS_MAXIMUM);
		this.physicsWorld = physicsWorld;
		this.vertexObjectBufferManager = vertexObjectBufferManager;
		this.regions = regions;
	}

	@Override
	protected Obstacle onAllocatePoolItem() {
		if (++INITIAL_COUNT < regions.length) {
			return new Obstacle(DEFAULT_POS_X, DEFAULT_POS_Y,
					regions[INITIAL_COUNT], physicsWorld,
					vertexObjectBufferManager, this);
		} else {
			return new Obstacle(DEFAULT_POS_X, DEFAULT_POS_Y,
					getRandomRegion(), physicsWorld, vertexObjectBufferManager,
					this);
		}
	}

	@Override
	public synchronized Obstacle obtainPoolItem() {
		this.shufflePoolItems();
		return super.obtainPoolItem();
	}

	public TextureRegion getRandomRegion() {
		int len = regions.length;
		return regions[NumberUtil.getRandomInt(len)];
	}

}
