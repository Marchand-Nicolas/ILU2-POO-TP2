package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controleur.ControlAfficherVillage;
import controleur.ControlEmmenager;
import personnages.Chef;
import villagegaulois.Village;

class ControlAfficherVillageTest {
	private ControlAfficherVillage controlAfficherVillage;
	private Village village;

	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("Le village des irréductibles", 10, 5);
		controlAfficherVillage = new ControlAfficherVillage(village);
		Chef abra = new Chef("abra", 10, village);
		village.setChef(abra);
	}
	
	@Test
	public void testDonnerNomVillage() {
		assertEquals("Le village des irréductibles", controlAfficherVillage.donnerNomVillage());
	}
	
	@Test
	public void testDonnerNomsVillageois() {
		String[] expected1 = { "abra" };
		assertArrayEquals(expected1, controlAfficherVillage.donnerNomsVillageois());
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterGaulois("john", 1);
		String[] expected2 = { "abra", "john" };
		assertArrayEquals(expected2, controlAfficherVillage.donnerNomsVillageois());
	}
	
	@Test
	public void donnerNbEtals() {
		assertEquals(5, controlAfficherVillage.donnerNbEtals());
	}
}
