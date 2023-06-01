package databases;

public class Fichier extends Element{
	private int taille;

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public Fichier(int taille) {
		super();
		this.taille = taille;
	}

	@Override
	public String toString() {
		return "Fichier [ nom"+ getName()+ "taille =" + taille + "]";
	}
	
	
}
