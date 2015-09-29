package testen;
import static org.junit.Assert.*;

import mechanicsspel.Aanvaller;
import mechanicsspel.Competitie;
import mechanicsspel.Doelman;
import mechanicsspel.Manager;
import mechanicsspel.Middenvelder;
import mechanicsspel.ReaderWriter;
import mechanicsspel.Speelronde;
import mechanicsspel.Speelschema;
import mechanicsspel.Team;
import mechanicsspel.Verdediger;
import mechanicsspel.Wedstrijd;

import org.junit.Test;
import org.w3c.dom.Document;


public class ReaderWriterTest {
	
	Team t1 = new Team("Ajax",0,0,0,0,0,0);
	Team t2 = new Team("Feyenoord",0,0,0,0,0,0);
	Aanvaller a1 = new Aanvaller("Anwar El Ghazi", 1500000, "aanvaller", 69, 31, 58, "");
	Verdediger v1 = new Verdediger("Nicolai Boilesen", 2000000, "verdediger", 56, 62, 77, "");
	Middenvelder m1 = new Middenvelder("Davy Klaassen", 4500000, "middenvelder", 75, 63, 79, "");
	Doelman d1 = new Doelman("Jasper Cillessen", 8000000, "doelman", 0, 79, 0, "");
	Manager mng = new Manager(t1, 100000, "Klaas");
	Wedstrijd w1 = new Wedstrijd(t1, t2);
	Wedstrijd w2 = new Wedstrijd(t2, t1);
	Speelronde s1 = new Speelronde(1);
	Speelronde s2 = new Speelronde(2);
	Competitie c1 = new Competitie();
	Speelschema schema1 = new Speelschema();
	int r = 1;
	
	@Test
	public void testCreateCompetitie() throws Exception{
		Document doc = ReaderWriter.read("src//xmltest1.xml");
		Competitie c1 = ReaderWriter.createCompetitie(doc);		
		Competitie exp = new Competitie();
		t1.voegSpelerToe(a1); t1.voegSpelerToe(m1); t1.voegSpelerToe(v1); t1.voegSpelerToe(d1);
		exp.voegTeamToe(t1);
		assertEquals(exp,c1);
	}
	
	@Test
	public void testCreateManager() throws Exception{
		Document doc = ReaderWriter.read("src//xmltest1.xml");
		Competitie c1 = ReaderWriter.createCompetitie(doc);
		Manager m = ReaderWriter.createManager(doc, c1);
		t1.voegSpelerToe(a1); t1.voegSpelerToe(m1); t1.voegSpelerToe(v1); t1.voegSpelerToe(d1);
		assertEquals(mng,m);
	}
	
	@Test
	public void testReadSpeelronde() throws Exception{
		Document doc = ReaderWriter.read("src//xmltest1.xml");
		int r = ReaderWriter.readSpeelronde(doc);
		assertEquals(1,r);
	}
	
	@Test
	public void testCreateNaamSlotBestaat() throws Exception{
		Document doc = ReaderWriter.read("src//xmltest1.xml");
		Competitie comp = ReaderWriter.createCompetitie(doc);
		String s = ReaderWriter.createNaamSlot("src//xmltest1.xml", comp);
		String exp = "Manager: Klaas Team: Ajax Ronde: 2";
		assertEquals(exp,s);
		
	}
	
	@Test
	public void testCreateNaamSlotLeeg() throws Exception{
		Document doc = ReaderWriter.read("src//xmltest1.xml");
		Competitie comp = ReaderWriter.createCompetitie(doc);
		String s = ReaderWriter.createNaamSlot("src//xmltest10.xml", comp);
		String exp = "Leeg";
		assertEquals(exp,s);		
	}
	
	@Test
	public void testCreateSpeelschema() throws Exception{
		s1.addWedstrijd(w1); s2.addWedstrijd(w2);
		Speelschema exp = new Speelschema();
		exp.add(s1); exp.add(s2);		
		
		Document doc = ReaderWriter.read("src//xmltest2.xml");
		Competitie comp = ReaderWriter.createCompetitie(doc);
		Speelschema schema = ReaderWriter.createSpeelschema(doc, comp);
		assertEquals(exp,schema);
	}
	
	@Test
	public void testWriterCompetitie() throws Exception{
		t1.voegSpelerToe(a1); t2.voegSpelerToe(d1);
		c1.voegTeamToe(t1);	c1.voegTeamToe(t2);		

		ReaderWriter.Writer("src//xmltest3.xml", c1, schema1, mng, r);
		Document doc = ReaderWriter.read("src//xmltest3.xml");
		Competitie expc = ReaderWriter.createCompetitie(doc);
		assertEquals(expc,c1);
	}
	
	@Test
	public void testWriterSpeelchema() throws Exception{
		c1.voegTeamToe(t1);	c1.voegTeamToe(t2);		
		s1.addWedstrijd(w1); s2.addWedstrijd(w2);
		schema1.add(s1); schema1.add(s2);			

		ReaderWriter.Writer("src//xmltest3.xml", c1, schema1, mng, r);
		Document doc = ReaderWriter.read("src//xmltest3.xml");
		Competitie expc = ReaderWriter.createCompetitie(doc);
		Speelschema exps = ReaderWriter.createSpeelschema(doc, expc);
		assertEquals(exps,schema1);
	}
	
	@Test
	public void testWriterManager() throws Exception{
		c1.voegTeamToe(t1);	c1.voegTeamToe(t2);		

		ReaderWriter.Writer("src//xmltest3.xml", c1, schema1, mng, r);
		Document doc = ReaderWriter.read("src//xmltest3.xml");
		Competitie expc = ReaderWriter.createCompetitie(doc);
		Manager expm = ReaderWriter.createManager(doc, expc);
		assertEquals(expm,mng);
	}
	
	@Test
	public void testWriterSpeelronde() throws Exception{		

		ReaderWriter.Writer("src//xmltest3.xml", c1, schema1, mng, r);
		Document doc = ReaderWriter.read("src//xmltest3.xml");
		int expr = ReaderWriter.readSpeelronde(doc);
		assertEquals(expr,r);
	}
}
