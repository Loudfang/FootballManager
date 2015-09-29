package testen;
import static org.junit.Assert.*;
import mechanicsspel.Aanvaller;

import org.junit.Test;

public class AanvallerTest {

	Aanvaller a1 = new Aanvaller("Piet",100000,"Aanvaller",80,20,70,"");
	Aanvaller a2 = new Aanvaller("Piet",100000,"Aanvaller",80,20,70,"");
	Aanvaller a3 = new Aanvaller("Klaas",200000,"Aanvaller",85,25,75,"");

	@Test
	public void testAanvaller() {
		assertNotNull(a1);
	}
	
	@Test
	public void testToString(){
		String exp = "naam: Piet, prijs: 100000, type: Aanvaller, offensief: 80, defensief: 20, uithoudingsvermogen: 70, status: ";
		assertEquals(exp,a1.toString());
	}
	
	@Test
	public void testToXML(){
		String exp ="<speler>\r\n<naam>Piet</naam>\r\n<prijs>100000</prijs>\r\n"+
					"<type>Aanvaller</type>\r\n<offensief>80</offensief>\r\n<defensief>20</defensief>\r\n"+
					"<uithoudingsvermogen>70</uithoudingsvermogen>\r\n<status></status>\r\n</speler>";
		assertEquals(exp,a1.toXML());
	}
	
	@Test
	public void testEquals(){
		assertEquals(a1,a2);
	}
	
	@Test
	public void testNotEquals(){
		assertNotEquals(a2,a3);
	}
}
