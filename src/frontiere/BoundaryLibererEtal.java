package frontiere;

import controleur.ControlLibererEtal;

public class BoundaryLibererEtal {
	private ControlLibererEtal controlLibererEtal;

	public BoundaryLibererEtal(ControlLibererEtal controlLibererEtal) {
		this.controlLibererEtal = controlLibererEtal;
	}

	public void libererEtal(String nomVendeur) {
		boolean vendeurReconnu = controlLibererEtal.isVendeur(nomVendeur);
		if (!vendeurReconnu) {
			System.out.println("Mais vous n'êtes pas inscrit sur notre marcher aujourd'hui!");
		}
		else {
			String[] donneesEtal = controlLibererEtal.libererEtal(nomVendeur);
			if (donneesEtal[0].equals(String.valueOf(true))) {
				System.out.println("Vous avez vendu " + 
			donneesEtal[3] + 
			" sur " +
			donneesEtal[4] +
			" " +
			donneesEtal[2] +
			".");
				System.out.println("Au revoir " + nomVendeur + ", passez une bonne journée.");
			}
		}
	}

}
