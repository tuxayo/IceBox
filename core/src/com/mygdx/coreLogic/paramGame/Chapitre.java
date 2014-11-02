package com.mygdx.coreLogic.paramGame;

import java.util.LinkedList;

public class Chapitre {
	LinkedList<Niveau> niv;
	String img;
	int nbEtoile;
	
	/**
	 * Le constructeur qui prend deux paramètres et qui initialise la liste.
	 * @param img
	 * @param nb
	 */
	public Chapitre(String img, int nb){
		this.niv = new LinkedList<Niveau>();
		this.img = img;
		this.nbEtoile = nb;
	}
	
	/**
	 * Le constructeur vide de la classe Chapitre.
	 */
	public Chapitre(){
		this.niv = new LinkedList<Niveau>();
		this.img = null;
		this.nbEtoile = 0;
	}
	
	/**
	 * Un getter qui retourne le niveau
	 * @return niv
	 */
	public LinkedList<Niveau> getNiv() {
		return niv;
	}

	/**
	 * Modifie la valeur de niveau
	 * @param niv
	 */
	public void setNiv(LinkedList<Niveau> niv) {
		this.niv = niv;
	}

	/**
	 * Retourne la valeur d'img
	 * @return img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * Modifie la valeur d'img
	 * @param img
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * Retourne la valeur de nbEtoile
	 * @return nbEtoile
	 */
	public int getNbEtoile() {
		return nbEtoile;
	}

	/**
	 * Modifie la valeur de nbEtoile
	 * @param nbEtoile
	 */
	public void setNbEtoile(int nbEtoile) {
		this.nbEtoile += nbEtoile;
	}

	
	/**
	 * Ajoute un niveau 
	 */
	public void addNiveau (Niveau niv) {
		this.niv.add(niv);
	}
	
	/**
	 * Affiche le chapitre
	 */
	public void afficheChap(){
		System.out.println("l'image du niveau : " + this.img);
		System.out.println("le nombre d'étoile du niveau : " + this.nbEtoile);
		for(int i = 0; i<niv.size(); i++){
			System.out.println("/*/*/*/*/*/*/*/*/*Niveau "+(i+1)+"/*/*/*/*/*/*/*/*/*");
			niv.get(i).affiche();
		}
	}
}
