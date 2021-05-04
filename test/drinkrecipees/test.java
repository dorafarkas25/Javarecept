package drinkrecipees;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class test {

	Material m1 = new Material("voda", 5);
	Material m2 = new Material("citromlé", 2);
	Material m3 = new Material("gránátalmalé", 4);
	Material m4 = new Material("granide", 3);
	ArrayList<Material> m = new ArrayList<Material>();
	ArrayList<Material> mn = new ArrayList<Material>();
	Drink d1 = new Drink("Limonádé", m);
	Drink d2 = new Drink("Gránátkoktél", mn);
	Recipebook r = new Recipebook();

	@Before
	public void setUp() {
		m.add(m1);
		m.add(m2);
		m.add(m3);
		r.add(m4);
		r.add(m1);
		r.addDrink(d1);
	}

	@Test
	public void Drinklistempty() {
		assertFalse(r.getDrinkslist().isEmpty());
		assertFalse(r.getDrinkslist().size() == 0);
	}

	@Test
	public void Materiallistempty() {
		assertFalse(r.getDrinkslist().isEmpty());
		assertFalse(r.getDrinkslist().size() == 0);
	}

	@Test
	public void Recipebookdrinksremove() {
		r.removeDrink(d1.getName());
		assertFalse(r.getDrinkslist().contains(d1));
		assertNotEquals(d1, r.getDrinkslist().contains(d1));
	}

	@Test
	public void addRecipebookmaterials() {
		assertTrue(r.getMateriallist().contains(m4));

	}

	@Test
	public void addRecipebookdrinks() {
		r.addDrink(d2);
		assertTrue(r.getDrinkslist().contains(d2));

	}

	@Test
	public void DrinkToString() {
		assertEquals(d1.getName() + "\n " + "Hozzávalók: " + d1.getMaterialList(), d1.toString());
	}

	@Test
	public void MattoString() {
		assertEquals(m1.getName() + " " + m1.getQuantity() + " ml", m1.toString());
	}

	@Test
	public void DrinkgetName() {
		assertEquals("Limonádé", d1.getName());
	}

	@Test
	public void MaterialgetName() {
		assertEquals("voda", m1.getName());
	}

	@Test
	public void Drinkgetarraylist() {
		assertEquals(5, d1.getMaterialList().get(0).getQuantity(), 0.001);
	}

	@Test
	public void MaterialgetQuantity() {
		assertEquals(5, m1.getQuantity(), 0.001);
	}

}