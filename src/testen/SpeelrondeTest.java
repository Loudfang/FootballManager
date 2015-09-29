package testen;
import static org.junit.Assert.*;
import mechanicsspel.Speelronde;
import mechanicsspel.Speelschema;
import mechanicsspel.Team;
import mechanicsspel.Uitslag;
import mechanicsspel.Wedstrijd;

import org.junit.Test;


public class SpeelrondeTest {

	Speelschema sp1 = new Speelschema();
	
	Speelronde s1 = new Speelronde(1);
	Speelronde s2 = new Speelronde(1);
	Speelronde s3 = new Speelronde(2);
	int[] s = {1,2};

	Team t1 = new Team("Bossteam", 0, 0, 0, 0, 0,0);
	Team t2 = new Team("Baasteam", 0, 0, 0, 0, 0,0);
	Wedstrijd w1 = new Wedstrijd(t1,t2);
	Wedstrijd w2 = new Wedstrijd(t2,t1);

	Uitslag u1 = new Uitslag(t1,t2,s);
	Uitslag u2 = new Uitslag(t2,t1,s);
	
	@Test
	public void testSpeelronde(){
		assertNotNull(s1);
	}

	@Test
	public void testGetWedstrijd(){
		s1.addWedstrijd(w1);
		assertEquals(w1,s1.getWedstrijd(0));
	}
	
	@Test
	public void testGetUitslag(){
		s1.addUitslag(u1);
		assertEquals(u1,s1.getUitslag(0));
	}
	
	@Test
	public void testGetUitslagNull(){
		assertEquals(null,s1.getUitslag(0));
	}
	
	@Test
	public void testLength(){
		s1.addWedstrijd(w1);
		s1.addUitslag(u1);
		assertEquals(1,s1.length());
	}
	
	@Test
	public void testToString(){
		s1.addWedstrijd(w1);
		s1.addUitslag(u1);
		String exp = "Speelronde 1: Bossteam speelt tegen Baasteam\n";
		assertEquals(exp,s1.toString());
	}
	
	@Test
	public void testToXML(){
		s1.addWedstrijd(w1);
		s1.addUitslag(u1);
		String exp ="<speelronde id=\"1\">\r\n<wedstrijd>Bossteam-Baasteam"+
					"</wedstrijd>\r\n</speelronde>";
		assertEquals(exp,s1.toXML());
	}
	
	@Test
	public void testEquals(){
		s1.addUitslag(u1); s2.addUitslag(u1);
		s1.addWedstrijd(w1); s2.addWedstrijd(w1);
		assertEquals(s1,s2);
	}
	
	@Test
	public void testNotEqualsWedstrijden(){
		s1.addWedstrijd(w1);
		assertNotEquals(s1,s2);
	}
	
	@Test
	public void testNotEqualsUitslagen(){
		s1.addUitslag(u1);
		assertNotEquals(s1,s2);
	}
	
	@Test
	public void testNotEqualsSpeelronde(){
		assertNotEquals(s2,s3);
	}
	
	@Test
	public void testNotEqualsWedstrijd(){
		s1.addWedstrijd(w1); s2.addWedstrijd(w2);
		assertNotEquals(s1,s2);
	}
	
	@Test
	public void testNotEqualsUitslag(){
		s1.addUitslag(u1); s2.addUitslag(u2);
		assertNotEquals(s1,s2);
	}
}
