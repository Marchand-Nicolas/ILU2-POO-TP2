package frontiere;

import controleur.ControlAcheterProduit;
import personnages.Gaulois;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		if (!controlAcheterProduit.estHabitant(nomAcheteur)) {
			System.out.println("Je suis désolée "+ nomAcheteur +" mais il faut être un habitant de notre village pour commercer ici.");
			return;
		}
		String nomProduit = null;
		do {
			nomProduit = Clavier.entrerChaine("Quel produit voulez-vous acheter?\n");
			if (!controlAcheterProduit.estProduitVendu(nomProduit)) {
				System.out.println("Désolé, personne ne vend ce produit au marché.\n");
			}
		} while (nomProduit == null || !controlAcheterProduit.estProduitVendu(nomProduit));
		int numVendeur = 0;
		Gaulois[] vendeurs = controlAcheterProduit.listerVendeursProduit(nomProduit);
		do {
			numVendeur = Clavier.entrerEntier("Chez quel commerçant voulez-vous acheter des fleurs ?\n" + controlAcheterProduit.afficherVendeursProduit(nomProduit));
			if (0 >= numVendeur || numVendeur > vendeurs.length) {
				System.out.println("Ce vendeur n'existe pas.");
			}
		} while (0 >= numVendeur || numVendeur > vendeurs.length);
		Gaulois vendeur = vendeurs[numVendeur - 1];
		System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + vendeur.getNom() + ".\n");
		int nbProduits = -1;
		do {
			nbProduits = Clavier.entrerEntier("Bonjour " + nomAcheteur + ", combien de " + nomProduit + " voulez-vous acheter ?\n");
			if (nbProduits < 0) {
				System.out.println("Quantité invalide!\n");
			}
		} while (0 > nbProduits);
		int quantiteFinale = controlAcheterProduit.acheterProduit(vendeur, nbProduits);
		if (quantiteFinale == nbProduits)
			System.out.println(nomAcheteur + " achète " + nbProduits + " " + nomProduit + " à " + vendeur.getNom());
		else if (quantiteFinale == 0) 
			System.out.println(nomAcheteur + " veut acheter " + nbProduits + " "
					+ nomProduit
					+ " malheureusement il n’y en a plus !");
			else System.out.println(nomAcheteur + " veut acheter "
					+ nbProduits + " "
					+ nomProduit
					+ " malheureusement "
					+ vendeur.getNom()
					+ " n’en a plus que "
					+ quantiteFinale
					+ ". "
					+ nomAcheteur
					+ " achète tout le stock de "
					+ vendeur.getNom() + ".");
		
	}
}
