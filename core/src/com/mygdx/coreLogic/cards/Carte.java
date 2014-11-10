package com.mygdx.coreLogic.cards;

import javax.print.attribute.standard.MediaSize.Other;



public abstract class Carte {
	protected int valeur;
	protected String textureRegion;


	/**
	 * Constructeur de la classe Carte qui prend deux paramètres.
	 * @param textureRegion
	 * @param val
	 */
	public Carte(String textureRegion, int val) {
		this.valeur = val;
		this.textureRegion = textureRegion;
	}

	
	/**
	 * Retourne le nom de l'image associé à la carte.
	 * @return img
	 */
	public String getTextureRegion() {
		return textureRegion;
	}
	

	/**
	 * Modifie le nom de l'image associé à la carte.
	 * @return img
	 */
	public void setTextureRegion(String img) {
		this.textureRegion = img;
	}
	
	
	/**
	 * Retourne la valeur de la carte.
	 * @return valeur
	 */
	public int getValeur() {
		return valeur;
	}

	/**
	 * Modifie la valeur de la carte.
	 * @param valeur
	 */
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	@Override
	public String toString() {
		return Integer.toString(valeur).replace('-', '~');
	}
	
	/**
	 * Renvoie une nouvelle carte Zero
	 */
	public static Carte getCarteZero() {
		return new CarteJeu("carte0", 0);
	}

	/**
	 * Permet d'afficher une carte.
	 */
	public abstract void afficheCarte();

	/**
	 * Retourne le nom de l'image associé à la carte opposée.
	 * @return
	 */
	public abstract String getOppositeTexture();
	
	/**
	 * Retourne une nouvelle carte qui est l'opposée de cette carte.
	 * @return
	 */
	public abstract Carte getOppositeCarte ();

	
	/**
	 * Prédicat pour tester si deux cartes sont opposées
	 * @param other
	 * @return True si {@link Other} est la carte opposée, False si non.
	 */
	public abstract boolean isOpposite(Carte other);


	/**
	 * Predicat pour tester si la carte est la carte Box
	 * @return True si la carte est la carte Box, False si non.
	 */
	public boolean isCarteBox() {
		return textureRegion.equals("carte-box");
	}

}
