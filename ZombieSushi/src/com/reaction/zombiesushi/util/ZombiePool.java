package com.reaction.zombiesushi.util;

import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.pool.GenericPool;

import com.reaction.zombiesushi.model.Cook;
import com.reaction.zombiesushi.model.Zombie;

public class ZombiePool extends GenericPool<Zombie> {
	
	private SimpleBaseGameActivity game;
	private Cook cook;
	
	public ZombiePool(SimpleBaseGameActivity game, final Cook cook){
		super();
		this.game = game;
		this.cook = cook;
	}

	@Override
	protected Zombie onAllocatePoolItem() {
		return new Zombie(900, 365,
				game.getVertexBufferObjectManager(), cook, this);
	}

}
