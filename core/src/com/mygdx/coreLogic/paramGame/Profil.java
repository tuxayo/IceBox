package com.mygdx.coreLogic.paramGame;

public class Profil {

	private static Profil currentJoueur = null;
	String nom;
	int chapEnCourt;
	int nivEnCourt;
	int nbEtoile;
	String img;
	
	/**
	 * Constructeur de la classe Profil
	 * @param nom
	 * @param chap
	 * @param niv
	 * @param nb
	 * @param img
	 */
	public Profil (String nom, int chap, int niv, int nb, String img){
		this.nom = nom;
		this.chapEnCourt = chap;
		this.nivEnCourt = niv;
		this.nbEtoile = nb;
		this.img = img;
	}

	/**
	 * Retourne le nom du joueur
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Modifie le nom du joueur
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Retourne le chapitre en court
	 * @return chapEnCourt
	 */
	public int getChapEnCourt() {
		return chapEnCourt;
	}
	
	/**
	 * Modifie le chapitre en court
	 * @param chapEnCourt
	 */
	public void setChapEnCourt(int chapEnCourt) {
		this.chapEnCourt = chapEnCourt;
	}
	
	/**
	 * Retourne le niveau en court
	 * @return nivEnCourt
	 */
	public int getNivEnCourt() {
		return nivEnCourt;
	}

	/**
	 * Modifie le niveau en court
	 * @param nivEnCourt
	 */
	public void setNivEnCourt(int nivEnCourt) {
		this.nivEnCourt = nivEnCourt;
	}

	/**
	 * Retourne le nombre d'�toile
	 * @return nbEtoile
	 */
	public int getNbEtoile() {
		return nbEtoile;
	}

	/**
	 * Modifie le nombre d'�toile
	 * @param nbEtoile
	 */
	public void setNbEtoile(int nbEtoile) {
		this.nbEtoile = nbEtoile;
	}

	/**
	 * Retourne l'avatar du joueur.
	 * @return img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * Modifie l'avatar du joueur.
	 * @param img
	 */
	public void setImg(String img) {
		this.img = img;
	}


	public Chapitre getChapitre() {
		return Test.buildChapitre();
	}

	
	public static Profil getCurrentJoueur() {
		return currentJoueur;
	}

	public static void setCurrentJoueur(Profil currentJoueur) {
		Profil.currentJoueur = currentJoueur;
	}
	
	
}
