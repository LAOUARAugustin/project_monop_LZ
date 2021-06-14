package projet_monopoly.Case;

import java.io.IOException;

import Exception.alertException;
import projet_monopoly.joueur.Joueur;
import projet_monopoly.joueur.JoueurHumain;

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

	
	public abstract void arretSurLaCase(JoueurHumain J) throws IOException, alertException;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cases other = (Cases) obj;
		if (nomCase == null) {
			if (other.nomCase != null)
				return false;
		} else if (!nomCase.equals(other.nomCase))
			return false;
		if (numeroCase != other.numeroCase)
			return false;
		return true;
	}



	
	
	
	
}