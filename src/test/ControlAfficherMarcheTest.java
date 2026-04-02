package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controleur.ControlAfficherMarche;
import controleur.ControlPrendreEtal;
import controleur.ControlVerifierIdentite;
import personnages.Chef;
import villagegaulois.Village;

class ControlAfficherMarcheTest {
	private ControlAfficherMarche controlAfficherMarche;
	private ControlPrendreEtal controlPrendreEtal;
	private Village village;

	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("Le village des irréductibles", 10, 5);
		controlAfficherMarche = new ControlAfficherMarche(village);
		Chef abra = new Chef("abra", 10, village);
		village.setChef(abra);
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
	}
	
	@Test
	public void testControlAfficherMarche() {
		String[] res = controlAfficherMarche.donnerInfosMarche();
		String[] expected = {};
		assertArrayEquals(expected, res);
		controlPrendreEtal.prendreEtal("abra", "pomme", 3);
		String[] res2 = controlAfficherMarche.donnerInfosMarche();
		String[] expected2 = { "abra", "3", "pomme" };
		assertArrayEquals(res2, expected2);
	}
}
