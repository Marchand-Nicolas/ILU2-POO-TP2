package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controleur.ControlVerifierIdentite;
import personnages.Chef;
import villagegaulois.Village;

class ControlVerifierIdentiteTest {
	private Village village;
	
	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("Le village des irréductibles", 10, 5);
		Chef abra = new Chef("abra", 10, village);
		village.setChef(abra);
	}
	
	@Test
	public void testVerifierIdentite() {
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		assertEquals(true, controlVerifierIdentite.verifierIdentite("abra"));
		assertNotEquals(true, controlVerifierIdentite.verifierIdentite("jeff"));
	}

}
