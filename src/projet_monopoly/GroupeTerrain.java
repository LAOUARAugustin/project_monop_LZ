package projet_monopoly;

public class GroupeTerrain {
	private String couleurTerrain;
	private int prixMaison;
	private int prixHotel;
	
	
	public String getCouleurTerrain() {
		return couleurTerrain;
	}
	public void setCouleurTerrain(String couleurTerrain) {
		if(couleurTerrain.trim().isEmpty()) {
			//exception
		}
		this.couleurTerrain = couleurTerrain;
	}
	public int getPrixMaison() {
		return prixMaison;
	}
	public void setPrixMaison(int prixMaison) {
		if(prixMaison<=0) {
			//exception
		}
		this.prixMaison = prixMaison;
	}
	public int getPrixHotel() {
		return prixHotel;
	}
	public void setPrixHotel(int prixHotel) {
		if(prixHotel<=0) {
			//exception
		}
		this.prixHotel = prixHotel;
	}
	public GroupeTerrain(String couleurTerrain, int prixMaison, int prixHotel) {
		this.setCouleurTerrain(couleurTerrain);
		this.setPrixMaison(prixMaison);
		this.setPrixHotel(prixHotel);
	}
	
	
}
