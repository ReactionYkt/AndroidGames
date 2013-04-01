package com.reaction.zombiesushi.util;

import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.pool.GenericPool;

import com.reaction.zombiesushi.model.Obstacle;

public class ObstaclePool extends GenericPool<Obstacle> {

	private static final int DEFAULT_POS_X = 1000;
	private static final int DEFAULT_POS_Y = 365;
	// private int INITIAL_COUNT = 0;
	private PhysicsWorld physicsWorld;
	private VertexBufferObjectManager vertexObjectBufferManager;

	public ObstaclePool(PhysicsWorld physicsWorld,
			VertexBufferObjectManager vertexObjectBufferManager) {
		super(0);
		this.physicsWorld = physicsWorld;
		this.vertexObjectBufferManager = vertexObjectBufferManager;
	}

	@Override
	protected Obstacle onAllocatePoolItem() {
		/*
		 * if (++INITIAL_COUNT < regions.length) { return new
		 * Obstacle(DEFAULT_POS_X, DEFAULT_POS_Y, regions[INITIAL_COUNT],
		 * physicsWorld, vertexObjectBufferManager, this); } else {
		 */
		return new Obstacle(DEFAULT_POS_X, DEFAULT_POS_Y,
				Obstacle.getRandomRegion(), physicsWorld,
				vertexObjectBufferManager, this);
		// }
	}

	@Override
	public synchronized Obstacle obtainPoolItem() {
		this.shufflePoolItems();
		return super.obtainPoolItem();
	}

}
