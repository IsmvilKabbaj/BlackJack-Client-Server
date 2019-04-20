package com.aither.serveur;

public class Participant {
	
	private String nom;
	private String mise;
	private String informations;
	
	public Participant (String informations) {
		this.informations=informations;
	}
	
	public Participant (String nom, String mise) {
		this.nom=nom;
		this.mise=mise;	
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMise() {
		return mise;
	}
	public void setMise(String mise) {
		this.mise = mise;
	}

}
