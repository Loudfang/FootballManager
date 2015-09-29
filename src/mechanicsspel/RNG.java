package mechanicsspel;

import java.util.Random;

public class RNG {
	
	private static Random generator;
	private static boolean seed = false;

	/**
	 * methode om een random integer te krijgen binnen bepaalde grenzen(min,max)
	 */
	public static int randomInt(int min, int max) {
		generator = new Random();
		if (seed) {		
			generator.setSeed(20);
		}		
		int range = (max - min) + 1;
		return (int)(generator.nextDouble() * range) + min;
		
	}
	
	public static void setSeed(boolean b){
		seed = b;
	}	
}