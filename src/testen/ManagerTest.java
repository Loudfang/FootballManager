package testen;
import static org.junit.Assert.*;
import mechanicsspel.Manager;
import mechanicsspel.Team;

import org.junit.Test;


public class ManagerTest {
	
	Team t1 = new Team("Baasteam",0,0,0,0,0,0);
	Team t2 = new Team("Bossteam",0,0,0,0,0,0);
	Manager m1 = new Manager(t1,5000000,"Piet");
	Manager m2 = new Manager(t1,5000000,"Piet");
	Manager m3 = new Manager(t1,5000000,"Henk");
	Manager m4 = new Manager(t1,500000,"Piet");
	Manager m5 = new Manager(t2,5000000,"Piet");
	
	@Test
	public void testManager(){
		assertNotNull(m1);
	}
	
	@Test
	public void testSetBudget(){
		m1.setBudget(50);
		assertEquals(50,m1.getBudget());
	}

	@Test
	public void testGetTeam(){
		assertEquals(t1,m1.getTeam());
	}
	
	@Test
	public void testGetBudget(){
		assertEquals(5000000,m1.getBudget());
	}
	
	@Test
	public void testGetNaam(){
		assertEquals("Piet",m1.getNaam());
	}
	
	@Test
	public void testToString(){
		String exp = "Manager: Piet, team: Baasteam, budget: 5000000";
		assertEquals(exp,m1.toString());
	}
	
	@Test
	public void testToXML(){
		String exp = "<naam>Piet</naam>\r\n<team>Baasteam</team>\r\n<budget>5000000</budget>\r\n";
		assertEquals(exp,m1.toXML());
	}
	
	@Test
	public void testEquals(){
		assertEquals(m1,m2);
	}
	
	@Test
	public void testNotEqualsNaam(){
		assertNotEquals(m2,m3);
	}
	
	@Test
	public void testNotEqualsBudget(){
		assertNotEquals(m1,m4);
	}
	
	@Test
	public void testNotEqualsTeam(){
		assertNotEquals(m1,m5);

	}
}
