package BJ;

public class Joueur {
 
	private String nom;
	private int nbVictoires;
	private int nbCoups;
	
	public Joueur (String nom, int nbVictoires, int nbCoups) {
		this.nom=nom;
		this.nbVictoires=nbVictoires;
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNbVictoires() {
		return nbVictoires;
	}

	public void setNbVictoires(int nbVictoires) {
		this.nbVictoires = nbVictoires;
	}
	

}
