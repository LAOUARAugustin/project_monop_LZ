package projet_monopoly;

public abstract class Cases {
	protected int numeroCase;
	protected String nomCase;
	
	
	public int getNumeroCase() {
		return numeroCase;
	}
	
	public void setNumeroCase(int numeroCase) {
		this.numeroCase = numeroCase;
	}
	
	public String getNomCase() {
		return nomCase;
	}
	
	public void setNomCase(String nomCase) {
		if(nomCase.trim().isEmpty()) {
			//exception
		}
		this.nomCase = nomCase;
	}
	
	public Cases(int numeroCase, String nomCase) {
		this.setNomCase(nomCase);
		this.setNumeroCase(numeroCase);
	}
	
	
	
	
}