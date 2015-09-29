package testen;
import static org.junit.Assert.*;
import mechanicsspel.Middenvelder;

import org.junit.Test;


public class MiddenvelderTest {

	Middenvelder m1 = new Middenvelder("Piet",100000,"Middenvelder",50,50,70,"");
	Middenvelder m2 = new Middenvelder("Piet",100000,"Middenvelder",50,50,70,"");
	Middenvelder m3 = new Middenvelder("Klaas",200000,"Middenvelder",40,60,75,"");

	@Test
	public void testAanvaller() {
		assertNotNull(m1);
	}
	
	@Test
	public void testToString(){
		String exp = "naam: Piet, prijs: 100000, type: Middenvelder, offensief: 50, defensief: 50, uithoudingsvermogen: 70, status: ";
		assertEquals(exp,m1.toString());
	}
	
	@Test
	public void testToXML(){
		String exp ="<speler>\r\n<naam>Piet</naam>\r\n<prijs>100000</prijs>\r\n"+
					"<type>Middenvelder</type>\r\n<offensief>50</offensief>\r\n<defensief>50</defensief>\r\n"+
					"<uithoudingsvermogen>70</uithoudingsvermogen>\r\n<status></status>\r\n</speler>";
		assertEquals(exp,m1.toXML());
	}
	
	@Test
	public void testEquals(){
		assertEquals(m1,m2);
	}
	
	@Test
	public void testNotEquals(){
		assertNotEquals(m2,m3);
	}
}
