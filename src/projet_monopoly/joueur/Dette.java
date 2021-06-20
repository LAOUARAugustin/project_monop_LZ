package projet_monopoly.joueur;

import Exception.alertException;
import Exception.boiteAlerte;

public class Dette {
	private Joueur beneficiere;
	private int montantDette;

	public Dette(Joueur beneficiere ) {
		this.montantDette = 0;
		this.beneficiere = beneficiere;
	}

	public Joueur getBeneficiere() {
		return beneficiere;
	}

	public void setBeneficiere(Joueur beneficiere) {
		this.beneficiere = beneficiere;
	}

	public int getMontantDette() {
		return montantDette;
	}

	public void setMontantDette(int montantDette) {
		if(this.montantDette + montantDette < 0) {
			throw new IllegalArgumentException("montant dette negatif");
		}
		this.montantDette = montantDette;
	}
	
	
	
	public void ajouterDette(int montant) {
		this.setMontantDette(this.montantDette += montant);
	}
	
	public void payerDette(int montant) throws alertException {
		if(montant > montantDette) {
			throw new alertException("Le montant est supérieur à la somme due");
		}
		if(montant < 0) {
			throw new alertException("Le montant est négatif");
		}
		this.setMontantDette(this.montantDette -= montant);
	}
	
	public boolean aUneDette() {
		return(this.montantDette>0);
	}
}
