package testen;
import static org.junit.Assert.*;
import mechanicsspel.Team;
import mechanicsspel.Uitslag;

import org.junit.Test;


public class UitslagTest {

	int[] s = {1,2};
	int[] q = {3,2};
	Team t1 = new Team("Bossteam", 0, 0, 0, 0, 0,0);
	Team t2 = new Team("Baasteam", 0, 0, 0, 0, 0,0);
	Uitslag u1 = new Uitslag(t1,t2,s);
	Uitslag u2 = new Uitslag(t1,t2,s);
	Uitslag u3 = new Uitslag(t1,t2,q);

	
	@Test
	public void testUitslag(){
		assertNotNull(u1);
	}

	@Test
	public void testGetUitslag(){
		assertEquals(s,u1.getUitslag());
	}
	
	@Test
	public void testEquals(){
		assertEquals(u1,u2);
	}
	
	@Test
	public void testNotEquals(){
		assertNotEquals(u2,u3);
	}
}
