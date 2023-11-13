package me.maanex.schmude.lib;

import java.util.List;
import java.util.Random;

public class ArrayUtils {
	
	private static Random random = new Random();

	private ArrayUtils() {
	}
	
	//
	
	public static <T> T random(T[] in) {
		return in[random.nextInt(in.length)];
	}

	public static <T> T random(List<T> in) {
		return in.get(random.nextInt(in.size()));
	}

}
