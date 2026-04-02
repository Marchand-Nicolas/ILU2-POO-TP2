package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controleur.ControlEmmenager;
import personnages.Chef;
import villagegaulois.Village;

class ControlEmmenagerTest {
	private Village village;
	
	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("Le village des irréductibles", 10, 5);
		Chef abra = new Chef("abra", 10, village);
		village.setChef(abra);
	}

	@Test
	void testControlEmmenager() {
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		assertNotNull(controlEmmenager);
	}
	
	@Test
	void testIsHabitant() {
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterGaulois("Bonemine", 10);
		assertTrue(controlEmmenager.isHabitant("Bonemine"));
		assertFalse(controlEmmenager.isHabitant("personne"));
		controlEmmenager.ajouterDruide("Panoramix", 10, 1, 5);
		assertTrue(controlEmmenager.isHabitant("Panoramix"));
	}
	
	@Test
	void testAjouterDruide() {
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterDruide("Panoramix", 10, 1, 5);
		assertTrue(controlEmmenager.isHabitant("Panoramix"));
	}
}
