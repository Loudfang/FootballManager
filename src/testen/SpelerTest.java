package testen;
import static org.junit.Assert.*;
import mechanicsspel.Aanvaller;

import org.junit.Test;


public class SpelerTest {

	Aanvaller a1 = new Aanvaller("Piet",100000,"Aanvaller",80,20,70,"");

	@Test
	public void testGetPrijs(){
		assertEquals(100000,a1.getPrijs());	
	}
	
	@Test
	public void testSetPrijs(){
		a1.setPrijs(5000);
		assertEquals(5000,a1.getPrijs());
	}
	
	@Test
	public void testGetType(){
		assertEquals("Aanvaller",a1.getType());
	}

	@Test
	public void testGetUithoudingsvermogen(){
		assertEquals(70,a1.getStamina());
	}
	
	@Test
	public void testSetUithoudingsvermogen(){
		a1.setStamina(34);
		assertEquals(34,a1.getStamina());
	}
	
	@Test
	public void testGetOpgesteld(){
		boolean exp = false;
		assertEquals(exp,a1.getOpgesteld());
	}
	
	@Test
	public void testSetOpgesteldTrue(){
		boolean exp = true;
		a1.setOpgesteld(true);
		assertEquals(exp,a1.getOpgesteld());
	}

	@Test
	public void testSetOpgesteldFalse(){
		boolean exp = false;
		a1.setOpgesteld(true);
		a1.setOpgesteld(false);
		assertEquals(exp,a1.getOpgesteld());
	}
	
	@Test
	public void testGetStatus(){
		String exp = "";
		assertEquals(exp,a1.getStatus());
	}
	
	@Test
	public void testSetStatus(){
		String exp = "geel";
		a1.setStatus("geel");
		assertEquals(exp,a1.getStatus());
	}
	
	@Test
	public void testGetStamina(){
		int exp = 70;
		assertEquals(exp,a1.getStamina());
	}
	
	@Test
	public void testGetNaam(){
		assertEquals("Piet",a1.getNaam());
	}
}
