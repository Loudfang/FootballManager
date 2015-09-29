package testen;

import static org.junit.Assert.*;
import mechanicsspel.Mechanics;

import org.junit.Test;

public class MechanicsTest {

	@Test
	public void testGetSpeelronde(){
		assertEquals(0,Mechanics.getSpeelRonde());
	}

	@Test
	public void testSetSpeelronde(){
		Mechanics.setSpeelRonde(34);
		assertEquals(34,Mechanics.getSpeelRonde());
	}
}
