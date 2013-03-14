package com.reaction.zombiesushi.util;

import java.util.Random;

public class RandomUtil {
	
	private static Random generator = new Random();
	
	public static int getInt(int limit){
		return generator.nextInt(limit);
	}

}
