package testen;
import static org.junit.Assert.*;
import mechanicsspel.Aanvaller;
import mechanicsspel.Competitie;
import mechanicsspel.RNG;
import mechanicsspel.Team;

import org.junit.Test;


public class CompetitieTest {

	Competitie c1 = new Competitie();
	Competitie c2 = new Competitie();

	Team t1 = new Team("Bazenteam",0,0,0,0,0,0);
	Team t2 = new Team("Baasteam",0,0,0,0,0,0);
	Aanvaller a1 = new Aanvaller("Piet", 3000, "Aanvaller", 70, 20, 60,"");

	@Test
	public void testCompetitie() {
		assertNotNull(c1);
	}
	
	@Test
	public void testLength(){
		c1.voegTeamToe(t1);
		c1.voegTeamToe(t2);
		assertEquals(2,c1.length());
	}
	
	@Test
	public void testGetTeamInt(){
		c1.voegTeamToe(t1);
		c1.voegTeamToe(t2);
		assertEquals(t2,c1.getTeam(1));
	}
	
	@Test
	public void testGetTeamString1(){
		c1.voegTeamToe(t1);
		c1.voegTeamToe(t2);
		assertEquals(t1,c1.getTeam("Bazenteam"));
	}
	
	@Test
	public void testGetTeamString2(){
		c1.voegTeamToe(t1);
		c1.voegTeamToe(t2);
		assertEquals(null,c1.getTeam("Bossteam"));
	}
	
	@Test
	public void testDoelpunten1(){
		RNG.setSeed(true);
		int x = Competitie.doelpunten(200);
		assertEquals(1,x);
	}
	
	@Test
	public void testDoelpunten2(){
		RNG.setSeed(true);
		int x = Competitie.doelpunten(100);
		assertEquals(2,x);
	}
	
	@Test
	public void testDoelpunten3(){
		RNG.setSeed(true);
		int x = Competitie.doelpunten(-100);
		assertEquals(3,x);
	}
	
	@Test
	public void testDoelpunten4(){
		RNG.setSeed(true);
		int x = Competitie.doelpunten(-300);
		assertEquals(4,x);
	}
	
	@Test
	public void testDoelpunten5(){
		RNG.setSeed(true);
		int x = Competitie.doelpunten(-600);
		assertEquals(6,x);
	}
	
	@Test
	public void testUitslag(){
		RNG.setSeed(true);
		int[] x = Competitie.uitslag(t1,t2);
		assertEquals(3,x[0]);
		assertEquals(3,x[1]);
	}
	
	@Test
	public void testToString(){
		Aanvaller a1 = new Aanvaller("henk",50000,"Aanvaller",88,34,70,"");
		t1.voegSpelerToe(a1);
		c1.voegTeamToe(t1);
		String exp ="Bazenteam p=0 d=0 w=0 v=0 g=0 dv=0\r"+
					"naam: henk, prijs: 50000, type: Aanvaller, offensief: 88, defensief: 34, uithoudingsvermogen: 70, status: \r\r";
		assertEquals(exp,c1.toString());
	}
	
	@Test
	public void testToXML(){
		Aanvaller a1 = new Aanvaller("henk",50000,"Aanvaller",88,34,70,"geel");
		t1.voegSpelerToe(a1);
		c1.voegTeamToe(t1);
		String exp ="<teams>\r\n<teamname id=\"Bazenteam\" p=\"0\" d=\"0\" w=\"0\" v=\"0\" g=\"0\" dv=\"0\">\r\n"+
					"<speler>\r\n<naam>henk</naam>\r\n<prijs>50000</prijs>\r\n"+
					"<type>Aanvaller</type>\r\n<offensief>88</offensief>\r\n<defensief>34</defensief>\r\n"+
					"<uithoudingsvermogen>70</uithoudingsvermogen>\r\n<status>geel</status>\r\n"+
					"</speler>\r\n</teamname>\r\n</teams>";
		assertEquals(exp,c1.toXML());
	}
	
	@Test
	public void testEquals(){
		c1.voegTeamToe(t1);
		c2.voegTeamToe(t1);
		assertEquals(c1,c2);
	}
	
	@Test
	public void testNotEquals(){
		c1.voegTeamToe(t1);
		c2.voegTeamToe(t2);
		assertNotEquals(c1,c2);
	}
	
	@Test
	public void testNotEqualsLength(){
		c1.voegTeamToe(t1); 
		assertNotEquals(c1,c2);
	}
	
	@Test
	public void testSorteer(){
		Competitie c1 = new Competitie();
		Competitie c2 = new Competitie();
		Team t1 = new Team("Team 1",0,4,0,1,0,7);
		Team t2 = new Team("Team 2",0,1,0,1,0,5);
		Team t3 = new Team("Team 3",0,1,0,1,0,10);
		Team t4 = new Team("Team 4",0,1,0,0,0,3);
		Team t5 = new Team("Team 5",3,0,0,0,0,2);
		Team t6 = new Team("Team 6",0,0,0,2,0,0);
		Team t7 = new Team("Team 7",0,1,0,1,0,6);
		Team t8 = new Team("Team 8",0,1,0,1,0,4);
		
		c1.voegTeamToe(t1); c1.voegTeamToe(t2); c1.voegTeamToe(t3); c1.voegTeamToe(t4);
		c1.voegTeamToe(t5); c1.voegTeamToe(t6); c1.voegTeamToe(t7); c1.voegTeamToe(t8);
		
		c2.voegTeamToe(t5); c2.voegTeamToe(t4); c2.voegTeamToe(t1); c2.voegTeamToe(t3);
		c2.voegTeamToe(t7); c2.voegTeamToe(t2); c2.voegTeamToe(t8); c2.voegTeamToe(t6);
		assertEquals(c2, c1.sorteer());
	}
}
