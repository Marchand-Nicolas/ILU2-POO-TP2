package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controleur.ControlAcheterProduit;
import controleur.ControlPrendreEtal;
import controleur.ControlTrouverEtalVendeur;
import controleur.ControlVerifierIdentite;
import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAcheterProduitTest {
	private ControlAcheterProduit controlAcheterProduit;
	private ControlPrendreEtal controlPrendreEtal;
	private Village village;
	private Chef abra;

	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("Le village des irréductibles", 10, 5);
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		abra = new Chef("abra", 10, village);
		village.setChef(abra);
		controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
	}
	
	@Test
	public void testEstHabitant() {
		assertEquals(false, controlAcheterProduit.estHabitant("zzz"));
		assertEquals(true, controlAcheterProduit.estHabitant("abra"));
	}
	
	@Test
	public void testEstProduitVendu() {
		assertEquals(false, controlAcheterProduit.estProduitVendu("pomme"));
		controlPrendreEtal.prendreEtal("abra", "pomme", 5);
		assertEquals(true, controlAcheterProduit.estProduitVendu("pomme"));
	}
	
	@Test
	public void testAcheterProduit() {
		controlPrendreEtal.prendreEtal("abra", "pomme", 5);
		assertEquals(3, controlAcheterProduit.acheterProduit(abra, 3));
		assertEquals(2, controlAcheterProduit.acheterProduit(abra, 3));
	}
	
	@Test
	public void testListerVendeursProduit() {
		controlPrendreEtal.prendreEtal("abra", "pomme", 5);
		Gaulois[] attendus = { abra };
		assertArrayEquals(attendus, controlAcheterProduit.listerVendeursProduit("pomme"));
	}
	
	@Test
	public void testAfficherVendeursProduit() {
		assertEquals("", controlAcheterProduit.afficherVendeursProduit("pomme"));
		controlPrendreEtal.prendreEtal("abra", "pomme", 5);
		assertEquals(" - 1 abra\n", controlAcheterProduit.afficherVendeursProduit("pomme"));
	}
}
