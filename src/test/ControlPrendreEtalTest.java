package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controleur.ControlPrendreEtal;
import controleur.ControlTrouverEtalVendeur;
import controleur.ControlVerifierIdentite;
import personnages.Chef;
import villagegaulois.Village;

class ControlPrendreEtalTest {
	private Village village;
	private ControlPrendreEtal controlPrendreEtal;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	
	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("Le village des irréductibles", 10, 5);
		Chef abra = new Chef("abra", 10, village);
		village.setChef(abra);
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
	}
	
	@Test
	public void testPrendreEtal() {
		controlPrendreEtal.prendreEtal("abra", "pomme", 3);
		assertNotEquals(null, controlTrouverEtalVendeur.trouverEtalVendeur("abra"));
		assertEquals(null, controlTrouverEtalVendeur.trouverEtalVendeur("zzz"));
	}
	
	@Test
	public void testResteEtals() {
		assertEquals(true, controlPrendreEtal.resteEtals());
	}
	
	@Test
	public void testVerifierIdentite() {
		assertEquals(true, controlPrendreEtal.verifierIdentite("abra"));
		assertEquals(false, controlPrendreEtal.verifierIdentite("zzz"));
	}
}
