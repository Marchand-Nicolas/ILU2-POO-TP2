package controleur;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur,
			Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}

	public Etal[] listerEtalsVendantProduit(String nomProduit) {
		Gaulois[] vendeurs = village.rechercherVendeursProduit(nomProduit);
		if (vendeurs == null) 
			return new Etal[0];
		Etal[] etals = new Etal[vendeurs.length];
		for (int i = 0; i < vendeurs.length; i++) {
			Gaulois gaulois = vendeurs[i];
			Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur(gaulois.getNom());
			etals[i] = etal;
		}
		return etals;
	}
	
	public Gaulois[] listerVendeursProduit(String nomProduit) {
		Etal[] etals = listerEtalsVendantProduit(nomProduit);
		Gaulois[] res = new Gaulois[etals.length];
		for (int i = 0; i < etals.length; i++) {
			Etal etal = etals[i];
			res[i] = etal.getVendeur();
		}
		return res;
	}
	
	public String afficherVendeursProduit(String nomProduit) {
		Gaulois[] vendeurs = listerVendeursProduit(nomProduit);
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < vendeurs.length; i++) {
			Gaulois vendeur = vendeurs[i];
			res.append(" - ");
			res.append(i + 1);
			res.append(" ");
			res.append(vendeur.getNom());
			res.append("\n");
		}
		return res.toString();
	}
	
	public int acheterProduit(Gaulois vendeur, int quantite) {
		Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur(vendeur.getNom());
		return etal.acheterProduit(quantite);
	}
	
	public boolean estProduitVendu(String nomProduit) {
		return listerEtalsVendantProduit(nomProduit).length > 0;
	}
	
	public boolean estHabitant(String nomGaulois) {
		return controlVerifierIdentite.verifierIdentite(nomGaulois);
	}
}
