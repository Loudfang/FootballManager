package testen;
import static org.junit.Assert.*;
import mechanicsspel.Competitie;
import mechanicsspel.Speelronde;
import mechanicsspel.Speelschema;
import mechanicsspel.Team;
import mechanicsspel.Uitslag;
import mechanicsspel.Wedstrijd;

import org.junit.Test;


public class SpeelschemaTest {
	Team t1 = new Team("Bossteam", 0, 0, 0, 0, 0,0);
	Team t2 = new Team("Baasteam", 0, 0, 0, 0, 0,0);
	Team t3 = new Team("Beastteam", 0, 0, 0, 0, 0,0);
	Team t4 = new Team("Beestteam", 0, 0, 0, 0, 0,0);
	Team t5 = new Team("1team", 0, 0, 0, 0, 0,0);
	Team t6 = new Team("2team", 0, 0, 0, 0, 0,0);
	Team t7 = new Team("3team", 0, 0, 0, 0, 0,0);
	Team t8 = new Team("4team", 0, 0, 0, 0, 0,0);
	
	Speelschema sp1 = new Speelschema();
	Speelschema sp2 = new Speelschema();
	Speelschema sp3 = new Speelschema();

	Speelronde s1 = new Speelronde(1);
	Speelronde s2 = new Speelronde(2);
	Speelronde s3 = new Speelronde(3);
	int[] s = {1,2};
	


	Uitslag u1 = new Uitslag(t1,t2,s);
	Uitslag u2 = new Uitslag(t2,t1,s);

	

	@Test
	public void testGet(){
		sp1.add(s1);
		assertEquals(s1,sp1.get(0));
	}
	
	@Test
	public void testLength(){
		sp1.add(s1);
		sp1.add(s2);
		assertEquals(2,sp1.length());
	}
	
	@Test
	public void testGetKomendeWedstrijd(){
		Wedstrijd w1= new Wedstrijd(t1,t2);
		Wedstrijd w2 = new Wedstrijd(t2,t1);
		Wedstrijd w3 = new Wedstrijd(t2,t2);
		s1.addWedstrijd(w1); s2.addWedstrijd(w3); s3.addWedstrijd(w2);
		sp1.add(s1); sp1.add(s2); sp1.add(s3);		
		Speelronde exp = new Speelronde(0);
		exp.addWedstrijd(w1); exp.addWedstrijd(w2);
		assertEquals(exp, sp1.getKomendeWedstrijden(t1));
	}
	
	@Test
	public void testToString(){
		Team t1 = new Team("Bossteam", 0, 0, 0, 0, 0,0);
		Team t2 = new Team("Baasteam", 0, 0, 0, 0, 0,0);
		Wedstrijd w1 = new Wedstrijd(t1,t2);
		s1.addWedstrijd(w1);
		sp1.add(s1); 
		String exp = "Competitie: \n\nSpeelronde 1: Bossteam speelt tegen Baasteam\n\n\n";
		assertEquals(exp,sp1.toString());
	}
	
	@Test
	public void testToXML(){
		Team t1 = new Team("Bossteam", 0, 0, 0, 0, 0,0);
		Team t2 = new Team("Baasteam", 0, 0, 0, 0, 0,0);
		Wedstrijd w1 = new Wedstrijd(t1,t2);
		s1.addWedstrijd(w1);
		sp1.add(s1);
		String exp ="\r\n<huidigspel>\r\n<speelronde id=\"1\">\r\n<wedstrijd>Bossteam-"+
					"Baasteam</wedstrijd>\r\n</speelronde>\r\n</huidigspel>";
		assertEquals(exp,sp1.toXML());
	}
	
	@Test
	public void testEquals(){
		sp1.add(s1); sp2.add(s1);
		assertEquals(sp1,sp2);
	}
	
	@Test
	public void testNotEqualsLength(){
		sp1.add(s1); sp2.add(s1); sp2.add(s2);
		assertNotEquals(sp1,sp2);
	}
	
	@Test
	public void testNotEquals(){
		sp1.add(s1); sp2.add(s2);
		assertNotEquals(sp1,sp2);
	}
	@Test 
	public void testrotate(){
		Competitie c1 = new Competitie();
		Competitie c2 = new Competitie();
		Competitie c3 = new Competitie();
		c1.voegTeamToe(t1); c1.voegTeamToe(t2); c1.voegTeamToe(t3); c1.voegTeamToe(t4);
		c2.voegTeamToe(t5); c2.voegTeamToe(t6); c2.voegTeamToe(t7); c2.voegTeamToe(t8);
		c3.voegTeamToe(t1); c3.voegTeamToe(t5); c3.voegTeamToe(t2); c3.voegTeamToe(t3); c3.voegTeamToe(t6); c3.voegTeamToe(t7); c3.voegTeamToe(t8); c3.voegTeamToe(t4);  
		assertEquals(Speelschema.rotate(c1, c2), c3 );
		
	}
	@Test
	public void testveranderthuisuit(){
		sp1 = new Speelschema();
		sp2 = new Speelschema();
		Wedstrijd w1 = new Wedstrijd(t1, t2);
		Wedstrijd w2 = new Wedstrijd(t3, t4);
		Wedstrijd w3 = new Wedstrijd(t5, t6);
		Wedstrijd w4 = new Wedstrijd(t7, t8);
		Wedstrijd w5 = new Wedstrijd(t1, t3);
		Wedstrijd w6 = new Wedstrijd(t2, t4);
		Wedstrijd w7 = new Wedstrijd(t5, t7);
		Wedstrijd w8 = new Wedstrijd(t6, t8);
		Wedstrijd w9 = new Wedstrijd(t2, t1);
		Wedstrijd w10 = new Wedstrijd(t4, t3);
		Wedstrijd w11 = new Wedstrijd(t6, t5);
		Wedstrijd w12 = new Wedstrijd(t8, t7);
		Wedstrijd w13 = new Wedstrijd(t3, t1);
		Wedstrijd w14 = new Wedstrijd(t4, t2);
		Wedstrijd w15 = new Wedstrijd(t7, t5);
		Wedstrijd w16 = new Wedstrijd(t8, t6);
		s1 = new Speelronde(1);
		s2 = new Speelronde(2);
		s3 = new Speelronde(3);
		Speelronde s4 = new Speelronde(4);
		Speelronde s5 = new Speelronde(1);
		Speelronde s6 = new Speelronde(2);
		Speelronde s7 = new Speelronde(3);
		Speelronde s8 = new Speelronde(4);
		s1.addWedstrijd(w1); s1.addWedstrijd(w2); s1.addWedstrijd(w3); s1.addWedstrijd(w4);
		s2.addWedstrijd(w5); s2.addWedstrijd(w6); s2.addWedstrijd(w7); s2.addWedstrijd(w8);
		s3.addWedstrijd(w9); s3.addWedstrijd(w10); s3.addWedstrijd(w11); s3.addWedstrijd(w12);
		
		
		s5.addWedstrijd(w9); s5.addWedstrijd(w10); s5.addWedstrijd(w11); s5.addWedstrijd(w12);
		s6.addWedstrijd(w5); s6.addWedstrijd(w6); s6.addWedstrijd(w7); s6.addWedstrijd(w8);
		s7.addWedstrijd(w1); s7.addWedstrijd(w2); s7.addWedstrijd(w3); s7.addWedstrijd(w4);
		
		
		sp1.add(s1); sp1.add(s2); sp1.add(s3); 
		sp2.add(s5); sp2.add(s6); sp2.add(s7);
	
		sp1 = Speelschema.veranderthuisuit(sp1);
		
		assertEquals(sp1, sp2);
		
	}
	
	@Test
	public void splits1(){
		Competitie c1 = new Competitie();
		Competitie c2 = new Competitie();
		c1.voegTeamToe(t1); c1.voegTeamToe(t2); c1.voegTeamToe(t3); c1.voegTeamToe(t4);
		c1.voegTeamToe(t5); c1.voegTeamToe(t6); c1.voegTeamToe(t7); c1.voegTeamToe(t8);
		c2.voegTeamToe(t1); c2.voegTeamToe(t2); c2.voegTeamToe(t3); c2.voegTeamToe(t4);
		
		assertEquals(Speelschema.splits1(c1), c2);
		
		
		
	}
	
	@Test
	public void splits2(){
		Competitie c1 = new Competitie();
		Competitie c2 = new Competitie();
		c1.voegTeamToe(t1); c1.voegTeamToe(t2); c1.voegTeamToe(t3); c1.voegTeamToe(t4);
		c1.voegTeamToe(t5); c1.voegTeamToe(t6); c1.voegTeamToe(t7); c1.voegTeamToe(t8);
		c2.voegTeamToe(t5); c2.voegTeamToe(t6); c2.voegTeamToe(t7); c2.voegTeamToe(t8);
		
		assertEquals(Speelschema.splits2(c1), c2);
		
		
		
	}
	
	@Test
	public void testSpeelschema1(){
		Competitie c1 = new Competitie();
		c1.voegTeamToe(t1); c1.voegTeamToe(t2); c1.voegTeamToe(t3); c1.voegTeamToe(t4);
		Wedstrijd w1 = new Wedstrijd(t1, t3);
		Wedstrijd w2 = new Wedstrijd(t2, t4);
		Wedstrijd w3 = new Wedstrijd(t1, t4);
		Wedstrijd w4 = new Wedstrijd(t3, t2);
		Wedstrijd w5 = new Wedstrijd(t1, t2);
		Wedstrijd w6 = new Wedstrijd(t4, t3);
		
		
		s1.addWedstrijd(w1); s1.addWedstrijd(w2);
		s2.addWedstrijd(w3); s2.addWedstrijd(w4);
		s3.addWedstrijd(w5); s3.addWedstrijd(w6);
		
		sp1.add(s1); sp1.add(s2); sp1.add(s3);
		assertEquals(Speelschema.Speelschema1(c1), sp1);
		
	}



@Test
public void testSpeelschema2(){
	Competitie c1 = new Competitie();
	c1.voegTeamToe(t1); c1.voegTeamToe(t2); c1.voegTeamToe(t3); c1.voegTeamToe(t4);
	
	s1 = new Speelronde(4);
	s2 = new Speelronde(5);
	s3 = new Speelronde(6);
	Wedstrijd w1 = new Wedstrijd(t3, t1);
	Wedstrijd w2 = new Wedstrijd(t4, t2);
	Wedstrijd w3 = new Wedstrijd(t4, t1);
	Wedstrijd w4 = new Wedstrijd(t2, t3);
	Wedstrijd w5 = new Wedstrijd(t2, t1);
	Wedstrijd w6 = new Wedstrijd(t3, t4);
	
	
	s1.addWedstrijd(w1); s1.addWedstrijd(w2);
	s2.addWedstrijd(w3); s2.addWedstrijd(w4);
	s3.addWedstrijd(w5); s3.addWedstrijd(w6);
	
	
	sp1.add(s1); sp1.add(s2); sp1.add(s3);
	assertEquals(Speelschema.Speelschema2(c1), sp1);
	
	
	
}

@Test
public void testSpeelschemaGenereer(){
	Competitie c1 = new Competitie();
	c1.voegTeamToe(t1); c1.voegTeamToe(t2); c1.voegTeamToe(t3); c1.voegTeamToe(t4);
	Speelschema sp1 = new Speelschema();
	Speelronde s1 = new Speelronde(1);
	Speelronde s2 = new Speelronde(2);
	Speelronde s3 = new Speelronde(3);
	Speelronde s4 = new Speelronde(4);
	Speelronde s5 = new Speelronde(5);
	Speelronde s6 = new Speelronde(6);
	
	Wedstrijd w1 = new Wedstrijd(t3, t1);
	Wedstrijd w2 = new Wedstrijd(t4, t2);
	Wedstrijd w3 = new Wedstrijd(t1, t4);
	Wedstrijd w4 = new Wedstrijd(t3, t2);
	Wedstrijd w5 = new Wedstrijd(t2, t1);
	Wedstrijd w6 = new Wedstrijd(t3, t4);
	Wedstrijd w7 = new Wedstrijd(t1, t3);
	Wedstrijd w8 = new Wedstrijd(t2, t4);
	Wedstrijd w9 = new Wedstrijd(t4, t1);
	Wedstrijd w10 = new Wedstrijd(t2, t3);
	Wedstrijd w11 = new Wedstrijd(t1, t2);
	Wedstrijd w12 = new Wedstrijd(t4, t3);
	
	
	s1.addWedstrijd(w1); s1.addWedstrijd(w2);
	s2.addWedstrijd(w3); s2.addWedstrijd(w4);
	s3.addWedstrijd(w5); s3.addWedstrijd(w6);
	s4.addWedstrijd(w7); s4.addWedstrijd(w8);
	s5.addWedstrijd(w9); s5.addWedstrijd(w10);
	s6.addWedstrijd(w11); s6.addWedstrijd(w12);
	
	sp1.add(s1); sp1.add(s2); sp1.add(s3);
	sp1.add(s4); sp1.add(s5); sp1.add(s6);
	assertEquals(Speelschema.SpeelschemaGenereer(c1), sp1);
	
	Competitie c2 = new Competitie();
	c2.voegTeamToe(t1);
	assertEquals(Speelschema.SpeelschemaGenereer(c2), null);
	
}
}