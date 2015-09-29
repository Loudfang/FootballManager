package testen;
import static org.junit.Assert.*;
import mechanicsspel.Aanvaller;
import mechanicsspel.Doelman;
import mechanicsspel.Middenvelder;
import mechanicsspel.Opstelling;
import mechanicsspel.Team;
import mechanicsspel.Verdediger;
import mechanicsspel.RNG;

import org.junit.Test;


public class TeamTest {
	Aanvaller ahenk = new Aanvaller("kjhjk",50000,"lkjklj",88,34,4,"");
	Aanvaller apiet = new Aanvaller("kjhjk",100000,"lkjklj",10,34,70,"");
	Middenvelder mpiet = new Middenvelder("kjhjk",250000,"lkjklj",21,34,70,"");
	Verdediger vhenk = new Verdediger("kjhjk",500000,"lkjklj",88,50,70,"");
	Doelman dpiet = new Doelman("kjhjk",1000000,"lkjklj",0,66,0,"");
	Team team1 = new Team("Bazenteam",0,0,0,0,0,0);
	
	@Test
	public void testSetTeamname() {
		team1.setTeamname("blob");
		assertEquals("blob",team1.getTeamname());
	}
	
	@Test
	public void testSetPunten() {
		team1.setPunten(3);
		assertEquals(3,team1.getPunten());
	}
	
	@Test
	public void testSetDoelsaldo() {
		team1.setDoelsaldo(2);
		assertEquals(2,team1.getDoelsaldo());
	}
	
	@Test
	public void testSetWinst() {
		team1.setWinst(35);
		assertEquals(35,team1.getWinst());
	}
	
	@Test
	public void testSetVerlies() {
		team1.setVerlies(8);
		assertEquals(8, team1.getVerlies());
	}
	
	@Test
	public void testSetGelijk() {
		team1.setGelijk(6);
		assertEquals(6,team1.getGelijk());
	}
	
	@Test
	public void testTeam(){
		assertNotNull(team1);		
	}
	
	@Test
	public void testLength(){
		team1.voegSpelerToe(ahenk);
		int exp = 1;
		assertEquals(exp,team1.getAantalSpelers());
	}
	
	@Test
	public void testGetSpeler(){
		team1.voegSpelerToe(mpiet);
		team1.voegSpelerToe(apiet);
		assertEquals(apiet,team1.getSpeler(1));
	}
	
	@Test
	public void testGetTeamname(){
		String exp = "Bazenteam";
		assertEquals(exp,team1.getTeamname());
	}
	
	@Test
	public void testGetPunten(){
		int exp = 0;
		assertEquals(exp,team1.getPunten());
	}
	
	@Test
	public void testOffensiefTotaal() {
		Opstelling o = new Opstelling();
		o.addAanvaller(apiet);
		o.addMiddenvelder(mpiet);
		team1.setOpstelling(o);
		assertEquals(31, team1.offensiefTotaal());
	}
	
	@Test
	public void testDefensiefTotaal() {
		Opstelling o = new Opstelling();
		o.addMiddenvelder(mpiet);
		o.addVerdediger(vhenk);
		o.addDoelman(dpiet);
		team1.setOpstelling(o);
		assertEquals(150, team1.defensiefTotaal());
	}
	
	@Test
	public void testOffensiefTotaalVerdediger(){
		Opstelling o = new Opstelling();
		o.addVerdediger(dpiet);
		team1.setOpstelling(o);
		assertEquals(0,team1.offensiefTotaal());
	}
	
	@Test
	public void testDefensiefTotaalAanvaller(){
		Opstelling o = new Opstelling();
		o.addAanvaller(ahenk);
		team1.setOpstelling(o);
		assertEquals(0,team1.defensiefTotaal());
	}
	
	@Test
	public void testStaminaTotaal(){
		team1.voegSpelerToe(mpiet);
		team1.voegSpelerToe(dpiet);
		team1.voegSpelerToe(vhenk);
		mpiet.setOpgesteld(true); dpiet.setOpgesteld(true);
		int exp = 7;
		assertEquals(exp,team1.staminaTotaal());
	}	
	
	@Test
	public void testUpdateStatistiekenAanvallerOffensief(){
		RNG.setSeed(true);
		team1.voegSpelerToe(ahenk);
		team1.updateStatistieken();
		assertEquals(90,ahenk.getOffensief());				
	}
	
	@Test
	public void testUpdateStatistiekenAanvallerUithoudingsvermogen(){
		RNG.setSeed(true);
		team1.voegSpelerToe(ahenk);
		team1.updateStatistieken();
		assertEquals(5,ahenk.getStamina());		
	}
	
	@Test
	public void testUpdateStatistiekenPrijs(){
		RNG.setSeed(true);
		team1.voegSpelerToe(ahenk);
		team1.updateStatistieken();
		assertEquals(52500,ahenk.getPrijs());
	}
	
	@Test
	public void testUpdateStatistiekenMiddenvelderOffensief(){
		RNG.setSeed(true);
		team1.voegSpelerToe(mpiet);
		team1.updateStatistieken();
		assertEquals(22,mpiet.getOffensief());				
	}
	
	@Test
	public void testUpdateStatistiekenMiddenvelderDefensief(){
		RNG.setSeed(true);
		team1.voegSpelerToe(mpiet);
		team1.updateStatistieken();
		assertEquals(35,mpiet.getDefensief());		
	}
	
	@Test
	public void testUpdateStatistiekenMiddenvelderUithoudingsvermogen(){
		RNG.setSeed(true);
		team1.voegSpelerToe(mpiet);
		team1.updateStatistieken();
		assertEquals(71,mpiet.getStamina());
	}
	
	@Test
	public void testUpdateStatistiekenVerdedigerDefensief(){
		RNG.setSeed(true);
		team1.voegSpelerToe(vhenk);
		team1.updateStatistieken();
		assertEquals(52,vhenk.getDefensief());		
	}
	
	@Test
	public void testUpdateStatistiekenVerdedigerUithoudingsvermogen(){
		RNG.setSeed(true);
		team1.voegSpelerToe(vhenk);
		team1.updateStatistieken();
		assertEquals(71,vhenk.getStamina());
	}
	
	@Test
	public void testUpdateStatistiekenDoelman(){
		RNG.setSeed(true);
		team1.voegSpelerToe(dpiet);
		team1.updateStatistieken();
		assertEquals(68,dpiet.getDefensief());
	}
	
/*	@Test
	public void testVoegKaartenToeRood(){
		RNG.setSeed(true);
		ahenk.setStatus("geel");
		team1.voegSpelerToe(ahenk);
		ahenk.setOpgesteld(true);
		team1.voegKaartenToe();
		assertEquals("rood-3",ahenk.getStatus());
	}*/
	
/*	@Test
	public void testVoegKaartenToeGeel(){
		RNG.setSeed(true);
		team1.voegSpelerToe(ahenk);
		ahenk.setOpgesteld(true);
		team1.voegKaartenToe();
		assertEquals("geel",ahenk.getStatus());
	}*/
	
/*	@Test
	public void testVoegKaartenToeRoodGelijk(){
		RNG.setSeed(true);
		team1.voegSpelerToe(ahenk);
		ahenk.setOpgesteld(true);
		team1.voegKaartenToe();
		assertEquals("rood-2",ahenk.getStatus());
	}*/
	
	@Test
	public void testVoegKaartenToeFalse(){
		RNG.setSeed(true);
		team1.voegSpelerToe(ahenk);
		team1.voegKaartenToe();
		assertEquals("",ahenk.getStatus());
	}
	
	@Test
	public void testVoegBlessureToe(){
		RNG.setSeed(true);
		team1.voegSpelerToe(ahenk);
		ahenk.setOpgesteld(true);
		team1.voegBlessureToe();
		assertEquals("blessure-1",ahenk.getStatus());
	}
	
	@Test
	public void testVoegBlessureToeGeel(){
		RNG.setSeed(true);
		team1.voegSpelerToe(ahenk);
		ahenk.setOpgesteld(true);
		ahenk.setStatus("geel");
		team1.voegBlessureToe();
		assertEquals("geel",ahenk.getStatus());
	}
	
	@Test
	public void testStatusNaSpeelrondeOpgesteld(){
		team1.voegSpelerToe(ahenk);
		ahenk.setOpgesteld(true);		
		team1.statusNaSpeelronde();
		assertEquals("",ahenk.getStatus());
	}
	
	@Test
	public void testStatusNaSpeelrondeBlessure(){
		team1.voegSpelerToe(mpiet);
		mpiet.setStatus("blessure-1");
		team1.statusNaSpeelronde();
		assertEquals("",mpiet.getStatus());
	}
	
	@Test
	public void testStatusNaSpeelrondeRood2(){
		team1.voegSpelerToe(dpiet);
		dpiet.setStatus("rood-2");
		team1.statusNaSpeelronde();
		assertEquals("rood-1",dpiet.getStatus());		
	}
	
	@Test
	public void testStatusNaSpeelrondeRood1(){
		team1.voegSpelerToe(vhenk);
		vhenk.setStatus("rood-1");
		team1.statusNaSpeelronde();
		assertEquals("",vhenk.getStatus());
	}
	
	@Test
	public void testCompPuntenUpdateVerlies(){
		team1.compPuntenUpdate(-1);
		assertEquals(1,team1.getVerlies());
	}
	
	@Test
	public void testCompPuntenUpdateGelijkPunten(){
		team1.compPuntenUpdate(0);
		assertEquals(1,team1.getPunten());
	}
	
	@Test
	public void testCompPuntenUpdateGelijk(){
		team1.compPuntenUpdate(0);
		assertEquals(1,team1.getGelijk());
	}
	
	@Test
	public void testCompPuntenUpdateWinstPunten(){
		team1.compPuntenUpdate(1);
		assertEquals(3,team1.getPunten());
	}
	
	@Test
	public void testCompPuntenUpdateWinst(){
		team1.compPuntenUpdate(1);
		assertEquals(1,team1.getWinst());
	}
	
	@Test
	public void testDoelsaldoPlus(){
		team1.doelSaldoPlus(4);
		assertEquals(4,team1.getDoelsaldo());
	}
	
	@Test
	public void testDoelsaldoMin(){
		team1.doelSaldoMin(2);
		assertEquals(-2,team1.getDoelsaldo());
	}
	
	@Test
	public void testToString(){
		team1.voegSpelerToe(ahenk);
		team1.voegSpelerToe(dpiet);
		String exp ="Bazenteam p=0 d=0 w=0 v=0 g=0 dv=0\r"+
					"naam: kjhjk, prijs: 50000, type: lkjklj, offensief: 88, defensief: 34, uithoudingsvermogen: 4, status: \r"+
					"naam: kjhjk, prijs: 1000000, type: lkjklj, offensief: 0, defensief: 66, uithoudingsvermogen: 0, status: \r";
		assertEquals(exp,team1.toString());
	}
	
	@Test
	public void testToXML(){
		team1.voegSpelerToe(ahenk);
		team1.voegSpelerToe(dpiet);
		String exp ="<teamname id=\"Bazenteam\" p=\"0\" d=\"0\" w=\"0\" v=\"0\" g=\"0\" dv=\"0\">\r\n"+
					"<speler>\r\n<naam>kjhjk</naam>\r\n<prijs>50000</prijs>\r\n"+
					"<type>lkjklj</type>\r\n<offensief>88</offensief>\r\n<defensief>34</defensief>\r\n"+
					"<uithoudingsvermogen>4</uithoudingsvermogen>\r\n<status></status>\r\n</speler>\r\n"+
					"<speler>\r\n<naam>kjhjk</naam>\r\n<prijs>1000000</prijs>\r\n"+
					"<type>lkjklj</type>\r\n<offensief>0</offensief>\r\n<defensief>66</defensief>\r\n"+
					"<uithoudingsvermogen>0</uithoudingsvermogen>\r\n<status></status>\r\n</speler>\r\n</teamname>";
	assertEquals(exp,team1.toXML());
	}
	
	@Test
	public void testEquals(){
		Team team2 = new Team("Bazenteam",0,0,0,0,0,0);
		team1.voegSpelerToe(ahenk); team2.voegSpelerToe(ahenk);
		assertEquals(team1,team2);
	}
	
	@Test
	public void testNotEqualsNaam(){
		Team team2 = new Team("Baasteam",0,0,0,0,0,0);
		team1.voegSpelerToe(ahenk); team2.voegSpelerToe(ahenk);
		assertNotEquals(team1,team2);		
	}
	
	@Test
	public void testNotEqualsSpeler(){
		Team team2 = new Team("Bazenteam",0,0,0,0,0,0);
		team1.voegSpelerToe(ahenk); team2.voegSpelerToe(dpiet);
		assertNotEquals(team1,team2);		
	}
	
	@Test
	public void testNotEqualsAantalSpelers(){
		Team team2 = new Team("Bazenteam",0,0,0,0,0,0);
		team1.voegSpelerToe(ahenk); team2.voegSpelerToe(ahenk); team2.voegSpelerToe(dpiet);
		assertNotEquals(team1,team2);
	}
}