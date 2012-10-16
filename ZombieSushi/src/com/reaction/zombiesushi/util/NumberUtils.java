package com.reaction.zombiesushi.util;

public class NumberUtils {

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

}
