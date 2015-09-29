package testen;

import static org.junit.Assert.*;
import mechanicsspel.Team;
import mechanicsspel.Wedstrijd;

import org.junit.Test;


public class WedstrijdTest {
	
	Team t1 = new Team("Bossteam", 0, 0, 0, 0, 0,0);
	Team t2 = new Team("Baasteam", 0, 0, 0, 0, 0,0);
	Wedstrijd w1 = new Wedstrijd(t1,t2);
	Wedstrijd w2 = new Wedstrijd(t1,t2);
	Wedstrijd w3 = new Wedstrijd(t2,t1);
	
	@Test
	public void testWedstrijd(){
		assertNotNull(w1);
	}

	@Test
	public void testGetTeamThuis(){
		assertEquals(t1,w1.getTeamThuis());
	}
	
	@Test
	public void testGetTeamUit(){
		assertEquals(t2,w1.getTeamUit());
	}
	
	@Test
	public void testToString(){
		String exp = "Bossteam speelt tegen Baasteam";
		assertEquals(exp,w1.toString());
	}
	
	@Test
	public void testToXML(){
		String exp = "<wedstrijd>Bossteam-Baasteam</wedstrijd>";
		assertEquals(exp,w1.toXML());
	}
	
	@Test
	public void testEquals(){
		assertEquals(w1,w2);
	}
	
	@Test
	public void testNotEquals(){
		assertNotEquals(w2,w3);
	}
}
