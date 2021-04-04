package projet_monopoly;

public class CasesSpeciales extends Cases {
	private int montant;

	
	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}
	
	
	
	public CasesSpeciales(int numeroCase, String nomCase, int montant) {
		super(numeroCase, nomCase);
		this.setMontant(montant);
	}

	
	
	

}
