package com.reaction.zombiesushi.util;

import java.util.Random;

public class NumberUtil {
	
	private static Random generator = new Random();

	public static int stringToInt(String string) {
		int value;
		try {
			value = Integer.parseInt(string);
		} catch (NumberFormatException ex) {
			value = 1;
		}
		return value;
	}
	
	public static float stringToFloat(String string) {
		float value;
		try {
			value = Float.parseFloat(string);
		} catch (NumberFormatException ex) {
			value = 1;
		}
		return value;
	}
	
	public static int getRandomInt(int limit){
		return generator.nextInt(limit);
	}


}
