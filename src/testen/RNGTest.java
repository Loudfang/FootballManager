package testen;
import static org.junit.Assert.*;
import mechanicsspel.RNG;

import org.junit.Test;


public class RNGTest {

	@Test
	public void testRandomIntWithSeed(){
		RNG.setSeed(true);
		int x = RNG.randomInt(1, 50);
		assertEquals(37,x);
	}
	
	@Test
	public void testRandomIntNoSeed(){
		RNG.setSeed(false);
		int x = RNG.randomInt(1, 100);
		int z = RNG.randomInt(1, 100);
		assertNotEquals(x,z);
	}
}
