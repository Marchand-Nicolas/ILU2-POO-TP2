package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controleur.ControlLibererEtal;
import controleur.ControlPrendreEtal;
import controleur.ControlTrouverEtalVendeur;
import controleur.ControlVerifierIdentite;
import personnages.Chef;
import villagegaulois.Village;

class ControlLibererEtalTest {
	private ControlPrendreEtal controlPrendreEtal;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlLibererEtal controlLibererEtal;
	
	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		Village village = new Village("Le village des irréductibles", 10, 5);
		Chef abra = new Chef("abra", 10, village);
		village.setChef(abra);
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);
	}
	
	@Test
	public void testIsVendeur() {
		assertEquals(false, controlLibererEtal.isVendeur("abra"));
		controlPrendreEtal.prendreEtal("abra", "pomme", 3);
		assertEquals(true, controlLibererEtal.isVendeur("abra"));
	}
	
	@Test
	public void testLibererEtal() {
		controlPrendreEtal.prendreEtal("abra", "pomme", 3);
		assertEquals(true, controlLibererEtal.isVendeur("abra"));
		String[] res = controlLibererEtal.libererEtal("abra");
		String[] expectedRes = { 
				"true",
				"abra",
				"pomme",
				"3",
				"0"
		};
		assertArrayEquals(expectedRes, res);
		String[] res2 = controlLibererEtal.libererEtal("zzzz");
		String[] expectedRes2 = {};
		assertArrayEquals(expectedRes2, res2);
	}
}
