package testen;

import static org.junit.Assert.*;

import java.util.ArrayList;

import mechanicsspel.Aanvaller;
import mechanicsspel.Doelman;
import mechanicsspel.Middenvelder;
import mechanicsspel.Opstelling;
import mechanicsspel.Speler;
import mechanicsspel.Team;
import mechanicsspel.Verdediger;

import org.junit.Test;

public class OpstellingTest {

	Team t1 = new Team("Baasteam",0,0,0,0,0,0);
	Opstelling o1 = new Opstelling();
	Opstelling o2 = new Opstelling();

	Aanvaller a1 = new Aanvaller("Piet", 50000, "Aanvaller", 70, 20, 60, "");
	Aanvaller a2 = new Aanvaller("Piet", 50000, "Aanvaller", 60, 20, 60, "");

	Middenvelder m1 = new Middenvelder("Piet", 50000, "Middenvelder", 50, 50, 60, "");
	Middenvelder m2 = new Middenvelder("Piet", 50000, "Middenvelder", 40, 50, 60, "");
	Middenvelder m3 = new Middenvelder("Piet", 50000, "Middenvelder", 50, 40, 60, "");
	Middenvelder m4 = new Middenvelder("Piet", 50000, "Middenvelder", 45, 45, 60, "");

	Verdediger v1 = new Verdediger("Piet", 50000, "Verdediger", 20, 70, 60, "");
	Verdediger v2 = new Verdediger("Piet", 50000, "Verdediger", 20, 60, 60, "");
	Verdediger v3 = new Verdediger("Piet", 50000, "Verdediger", 20, 65, 60, "");
	Verdediger v4 = new Verdediger("Piet", 50000, "Verdediger", 20, 73, 60, "");

	Doelman d1 = new Doelman("Piet", 50000, "Doelman", 0, 70, 0, "");
	Doelman d2 = new Doelman("Piet", 50000, "Doelman", 0, 60, 0, "");
	
	@Test
	public void testOpstelling() {
		assertNotNull(o1);
	}
	
	@Test
	public void testGetAanvallers(){
		o1.addAanvaller(a1);
		o1.addMiddenvelder(m1);
		o1.addVerdediger(v1);
		o1.addDoelman(d1);
		ArrayList<Speler> exp = new ArrayList<Speler>();
		exp.add(a1);
		assertEquals(exp,o1.getAanvallers());
	}

	@Test
	public void testGetMiddenvelders(){
		o1.addAanvaller(a1);
		o1.addMiddenvelder(m1);
		o1.addVerdediger(v1);
		o1.addDoelman(d1);
		ArrayList<Speler> exp = new ArrayList<Speler>();
		exp.add(m1);
		assertEquals(exp,o1.getMiddenvelders());
	}
	
	@Test
	public void testGetVerdedigers(){
		o1.addAanvaller(a1);
		o1.addMiddenvelder(m1);
		o1.addVerdediger(v1);
		o1.addDoelman(d1);
		ArrayList<Speler> exp = new ArrayList<Speler>();
		exp.add(v1);
		assertEquals(exp,o1.getVerdedigers());
	}
	
	@Test
	public void testGetDoelman(){
		o1.addAanvaller(a1);
		o1.addMiddenvelder(m1);
		o1.addVerdediger(v1);
		o1.addDoelman(d1);
		ArrayList<Speler> exp = new ArrayList<Speler>();
		exp.add(d1);
		assertEquals(exp,o1.getDoelman());
	}
	
	@Test
	public void testBesteAanvaller(){
		ArrayList<Aanvaller> exp = new ArrayList<Aanvaller>();
		exp.add(a1); exp.add(a2);
		assertEquals(a1,Opstelling.besteAanvaller(exp));		
	}
	
	@Test
	public void testBesteMiddenvelder(){
		ArrayList<Middenvelder> exp = new ArrayList<Middenvelder>();
		exp.add(m1); exp.add(m2);
		assertEquals(m1,Opstelling.besteMiddenvelder(exp));			
	}
	
	@Test
	public void testBesteVerdediger(){
		ArrayList<Verdediger> exp = new ArrayList<Verdediger>();
		exp.add(v1); exp.add(v2);
		assertEquals(v1,Opstelling.besteVerdediger(exp));	
	}
	
	@Test
	public void testBesteDoelman(){
		ArrayList<Doelman> exp = new ArrayList<Doelman>();
		exp.add(d1); exp.add(d2);
		assertEquals(d1,Opstelling.besteDoelman(exp));	
	}
	
	@Test
	public void testopstellingNPT(){
		t1.voegSpelerToe(a1); t1.voegSpelerToe(a2);
		t1.voegSpelerToe(m1); t1.voegSpelerToe(m2); t1.voegSpelerToe(m3); t1.voegSpelerToe(m4);
		t1.voegSpelerToe(v1); t1.voegSpelerToe(v2); t1.voegSpelerToe(v3); t1.voegSpelerToe(v4);
		t1.voegSpelerToe(d1); t1.voegSpelerToe(d2);
		Opstelling.opstellingNPT(t1);
		
		o1.addAanvaller(a1); o1.addAanvaller(a2);
		o1.addMiddenvelder(m1); o1.addMiddenvelder(m2); o1.addMiddenvelder(m3); o1.addMiddenvelder(m4);
		o1.addVerdediger(v4); o1.addVerdediger(v1); o1.addVerdediger(v3); o1.addVerdediger(v2);
		o1.addDoelman(d1);
		assertEquals(o1,t1.getOpstelling());	
	}
	
	@Test
	public void testToString(){
		o1.addAanvaller(a1); o1.addAanvaller(a2);
		o1.addMiddenvelder(m1); o1.addMiddenvelder(m2);
		o1.addVerdediger(v1); o1.addVerdediger(v1);
		o1.addDoelman(d1);
		String exp ="Opstelling: Aanvallers: naam: Piet, prijs: 50000, type: Aanvaller, offensief: 70, "+
					"defensief: 20, uithoudingsvermogen: 60, status: naam: Piet, prijs: 50000, type: "+
					"Aanvaller, offensief: 60, defensief: 20, uithoudingsvermogen: 60, status: Middenvelders: "+
					"naam: Piet, prijs: 50000, type: Middenvelder, offensief: 50, defensief: 50, "+
					"uithoudingsvermogen: 60, status: naam: Piet, prijs: 50000, type: Middenvelder, offensief: "+
					"40, defensief: 50, uithoudingsvermogen: 60, status: Verdedigers: naam: Piet, prijs: 50000, "+
					"type: Verdediger, offensief: 20, defensief: 70, uithoudingsvermogen: 60, status: naam: "+
					"Piet, prijs: 50000, type: Verdediger, offensief: 20, defensief: 70, uithoudingsvermogen: "+
					"60, status: Doelman: naam: Piet, prijs: 50000, type: Doelman, offensief: 0, defensief: "+
					"70, uithoudingsvermogen: 0, status: ";
		assertEquals(exp,o1.toString());
	}
	
	@Test
	public void testEquals(){
		o1.addAanvaller(a1); o1.addMiddenvelder(m1); o1.addVerdediger(v1); o1.addDoelman(d1);
		o2.addAanvaller(a1); o2.addMiddenvelder(m1); o2.addVerdediger(v1); o2.addDoelman(d1);
		assertEquals(o1,o2);
	}
	
	@Test
	public void testNotEqualsAanvallers(){
		o1.addAanvaller(a1);
		assertNotEquals(o1,o2);
	}
	
	@Test
	public void testNotEqualsMiddenvelders(){
		o1.addMiddenvelder(m1);
		assertNotEquals(o1,o2);
	}
	
	@Test
	public void testNotEqualsVerdedigers(){
		o1.addVerdediger(v1);
		assertNotEquals(o1,o2);
	}
	
	@Test
	public void testNotEqualsDoelmannen(){
		o1.addDoelman(d1);
		assertNotEquals(o1,o2);
	}
	
	@Test
	public void testNotEqualsAanvaller(){
		o1.addAanvaller(a1); o2.addAanvaller(a2);
		assertNotEquals(o1,o2);
	}
	
	@Test
	public void testNotEqualsMiddenvelder(){
		o1.addMiddenvelder(m1); o2.addMiddenvelder(m2);
		assertNotEquals(o1,o2);	
	}
	
	@Test
	public void testNotEqualsVerdediger(){
		o1.addVerdediger(v1); o2.addVerdediger(v2);
		assertNotEquals(o1,o2);
	}
	
	@Test
	public void testNotEqualsDoelman(){
		o1.addDoelman(d1); o2.addDoelman(d2);
		assertNotEquals(o1,o2);
	}
}