package com.reaction.zombiesushi.util;

import org.andengine.util.adt.pool.GenericPool;

import com.reaction.zombiesushi.GameScreen;
import com.reaction.zombiesushi.model.Obstacle;
import com.reaction.zombiesushi.model.Obstacle.ObstacleType;

public class ObstaclePool extends GenericPool<Obstacle>{
	
	private GameScreen screen;
	private static final int DEFAULT_POS_X = 1000;
	private static final int DEFAULT_POS_Y = 365;
	
	public ObstaclePool(GameScreen screen){
		super(3);
		this.screen = screen;
	}

	@Override
	protected Obstacle onAllocatePoolItem() {
		return new Obstacle(DEFAULT_POS_X, DEFAULT_POS_Y, ObstacleType.getRandom(), screen);
	}

	@Override
	public synchronized Obstacle obtainPoolItem() {
		this.shufflePoolItems();
		return super.obtainPoolItem();
	}
	
	

}
