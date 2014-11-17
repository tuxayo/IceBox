package com.mygdx.coreLogic.paramGame;

import java.util.LinkedList;
import java.util.List;

/**
 * Modélise un chapitre avec les niveaux qu'il contient, son image, 
 * et son nombre d'etoile
 * 
 */
public class Chapitre {

	private LinkedList<Niveau> niv; 
	private String img;
	private int nbEtoile;

	/**
	 * Le constructeur qui prend deux paramètres et qui initialise la liste.
	 * @param img
	 * @param nbEtoile
	 */
	public Chapitre(String img, int nbEtoile) {
		this.niv = new LinkedList<Niveau>();
		this.img = img;
		this.nbEtoile = nbEtoile;
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
	 * Charge tout les chapitre depuis le fichier XML contenant la description 
	 * des chapitres et renvoie la liste de tout les chapitre <p/>
	 * 
	 * /!\  Pour l'instant un seul chapitre est géré et aucun fichier XML n'est 
	 * 		lu, l'unique chapitre renvoyé est généré grâce au 10 premiers niveaux
	 * 		charger depuis les fichiers XML des niveaux
	 * @return La liste de tous les chapitres
	 */
	@Deprecated
	public static List<Chapitre> loadAllChapitre() {

		List<Chapitre> allChapitre = new LinkedList<Chapitre>();
		Chapitre chapitre = new Chapitre();
		
		for (int i = 1; i <= 10; i++) {
//			System.out.println("/*/*/*/*/*/*/*/*/*Niveau " + i + "/*/*/*/*/*/*/*/*/*");
			Niveau niv = new Niveau();
			niv.charger(paramGame.PATH_LVL + "niveau" + i + ".xml");
			niv.affiche();
//			System.out.println("");
			
			chapitre.addNiveau(niv);
			allChapitre.add(chapitre);
		}
		
		return allChapitre;
	}
	

	/**
	 * Un getter qui retourne les niveaux de ce chapitre
	 * @return niv
	 */
	public LinkedList<Niveau> getNiv() {
		return niv;
	}

	/**
	 * Modifie la liste des niveaux de ce chapitre
	 * @param niv
	 */
	public void setNiv(LinkedList<Niveau> niv) {
		this.niv = niv;
	}

	/**
	 * Retourne le nom de l'image qui représente 
	 * @return Retourne l'image de ce chapitre
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
	 * Ajoute un niveau au chapitre
	 * @param niv le niveau a ajouter
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
		for(int i = 0; i < niv.size(); i++){
			System.out.println("/*/*/*/*/*/*/*/*/*Niveau "+(i+1)+"/*/*/*/*/*/*/*/*/*");
			niv.get(i).affiche();
		}
	}

	/**
	 * Renvoie le niveau spécifié en paramètre
	 * @param numNiv
	 * @return le niveau numero numNiv
	 */
	public Niveau getNiveau(int numNiv) {
		return niv.get(numNiv-1);
	}

}
