package testen;
import static org.junit.Assert.*;
import mechanicsspel.Verdediger;

import org.junit.Test;


public class VerdedigerTest {

	Verdediger v1 = new Verdediger("Piet",100000,"Verdediger",15,70,70,"");
	Verdediger v2 = new Verdediger("Piet",100000,"Verdediger",15,70,70,"");
	Verdediger v3 = new Verdediger("Klaas",200000,"Verdediger",20,75,75,"");

	@Test
	public void testAanvaller() {
		assertNotNull(v1);
	}
	
	@Test
	public void testToString(){
		String exp = "naam: Piet, prijs: 100000, type: Verdediger, offensief: 15, defensief: 70, uithoudingsvermogen: 70, status: ";
		assertEquals(exp,v1.toString());
	}
	
	@Test
	public void testToXML(){
		String exp ="<speler>\r\n<naam>Piet</naam>\r\n<prijs>100000</prijs>\r\n"+
					"<type>Verdediger</type>\r\n<offensief>15</offensief>\r\n<defensief>70</defensief>\r\n"+
					"<uithoudingsvermogen>70</uithoudingsvermogen>\r\n<status></status>\r\n</speler>";
		assertEquals(exp,v1.toXML());
	}
	
	@Test
	public void testEquals(){
		assertEquals(v1,v2);
	}
	
	@Test
	public void testNotEquals(){
		assertNotEquals(v2,v3);
	}

}
