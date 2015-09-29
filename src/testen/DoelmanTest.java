package testen;
import static org.junit.Assert.*;
import mechanicsspel.Doelman;

import org.junit.Test;


public class DoelmanTest {

	Doelman d1 = new Doelman("Piet",100000,"Doelman",0,70,0,"");
	Doelman d2 = new Doelman("Piet",100000,"Doelman",0,70,0,"");
	Doelman d3 = new Doelman("Klaas",200000,"Doelman",0,85,0,"");
	
	@Test
	public void testDoelman() {
		assertNotNull(d1);
	}
	
	@Test
	public void testToString(){
		String exp = "naam: Piet, prijs: 100000, type: Doelman, offensief: 0, defensief: 70, uithoudingsvermogen: 0, status: ";
		assertEquals(exp,d1.toString());
	}
	
	@Test
	public void testToXML(){
		String exp ="<speler>\r\n<naam>Piet</naam>\r\n<prijs>100000</prijs>\r\n"+
					"<type>Doelman</type>\r\n<offensief>0</offensief>\r\n<defensief>70</defensief>\r\n"+
					"<uithoudingsvermogen>0</uithoudingsvermogen>\r\n<status></status>\r\n</speler>";
		assertEquals(exp,d1.toXML());
	}

	@Test
	public void testEquals(){
		assertEquals(d1,d2);
	}
	
	@Test
	public void testNotEquals(){
		assertNotEquals(d2,d3);
	}
}
