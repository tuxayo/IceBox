package com.mygdx.coreLogic.paramGame;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.mygdx.coreLogic.cards.Carte;
import com.mygdx.coreLogic.cards.CarteBox;
import com.mygdx.coreLogic.cards.CarteDeck;
import com.mygdx.coreLogic.cards.CarteJeu;

/**
 *	Modélise un niveau du jeu avec le nombre d'etoiles et l'image associée a 
 *	ce niveau, ainsi que les listes des cartes contenu dans chaque panneau 
 *	et deck
 */
public class Niveau {

	private int nbEtoile;
	private String img;
	
	private ArrayList<Carte> cartedroite;
	private ArrayList<Carte> cartegauche;
	private ArrayList<Carte> cartedecks;
	
	private static Document read = null;

	/**
	 * Constructeur vide de la classe Niveau
	 */
	public Niveau() {
		this.nbEtoile = 0;
		this.img = null;
		this.cartedroite = new ArrayList<Carte>();
		this.cartegauche = new ArrayList<Carte>();
		this.cartedecks = new ArrayList<Carte>();
	}

	/**
	 * Permet de lire un fichier xml
	 * 
	 * @param file
	 */
	private static void lirefichier(String file) {

		SAXBuilder sax = new SAXBuilder();
		try {
			read = sax.build(new File(file));
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Charge les informations du fichier xml
	 * 
	 * @param file
	 */
	void charger(String file) {

		Niveau.lirefichier(file);// on met le fichier dans read

		// on separe le contenu du fichier sur 3 elements

		Element pd = read.getRootElement().getChild("pd");
		Element pg = read.getRootElement().getChild("pg");
		Element deck = read.getRootElement().getChild("deck");

		// on recupere la liste des cartes de chaque element

		List<Element> cartepd = pd.getChildren("carte");
		List<Element> cartepg = pg.getChildren("carte");
		List<Element> cartedeck = deck.getChildren("carte");

		// on parcourt la liste des cartes

		Iterator<Element> i = cartepd.iterator();
		while (i.hasNext()) {
			Element carte = i.next();

			if (carte.getAttribute("value").getValue().equals("x")) {
				cartedroite.add(new CarteBox(carte.getAttribute("url").getValue()));
			} else {
				cartedroite.add(new CarteJeu(carte.getAttribute("url").getValue(), Integer.parseInt(carte.getAttribute("value").getValue())));
			}
		}

		Iterator<Element> j = cartepg.iterator();
		while (j.hasNext()) {
			Element carte = j.next();

			if (carte.getAttribute("value").getValue().equals("x")) {
				cartegauche.add(new CarteBox(carte.getAttribute("url").getValue()));
			} else {
				cartegauche.add(new CarteJeu(carte.getAttribute("url").getValue(), Integer.parseInt(carte.getAttribute("value").getValue())));
			}
		}

		Iterator<Element> k = cartedeck.iterator();
		while (k.hasNext()) {
			Element carte = k.next();
			cartedecks.add(new CarteDeck(carte.getAttribute("url").getValue(),Integer.parseInt(carte.getAttribute("value").getValue())));
		}
	}

	/**
	 * Fait appel à la méthode affichecarte de la classe Carte pour afficher
	 * les cartes de la liste
	 */
	public void affiche() {
		Carte tmp;

		System.out.println("*********** Affichage de la partie gauche ***********");
		for (int i = 0; i < cartegauche.size(); i++) {
			tmp= cartegauche.get(i);
			tmp.afficheCarte();
		}

		System.out.println("*********** Affichage de la partie droite ***********");
		for (int i = 0; i < cartedroite.size(); i++) {
			tmp= cartedroite.get(i);
			tmp.afficheCarte();
		}

		System.out.println("*********** Affichage du deck ***********");
		for (int i = 0; i < cartedecks.size(); i++) {
			tmp=cartedecks.get(i);
			tmp.afficheCarte();
		}

	}
	
	/**
	 * 
	 * @return Le nombre d'etoiles pour ce niveau
	 */
	public int getNbEtoile() {
		return nbEtoile;
	}

	/**
	 * Modifie le nombre d'étoiles de ce niveau
	 * @param nbEtoile
	 */
	public void setNbEtoile(int nbEtoile) {
		this.nbEtoile = nbEtoile;
	}

	/**
	 * 
	 * @return l'image associée a ce niveau
	 */
	public String getImg() {
		return img;
	}

	/**
	 * Modifie l'image associée a ce niveau
	 * @param img
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * 
	 * @return La liste des cartes contenue dans le panneau droit
	 */
	public ArrayList<Carte> getCartedroite() {
		return cartedroite;
	}

	/**
	 * Modifie la liste des cartes contenue dans le panneau droit
	 * @param cartedroite
	 */
	public void setCartedroite(ArrayList<Carte> cartedroite) {
		this.cartedroite = cartedroite;
	}

	/**
	 * 
	 * @return La liste des cartes contenue dans le panneau gauche
	 */
	public ArrayList<Carte> getCartegauche() {
		return cartegauche;
	}

	/**
	 * Modifie la liste des cartes contenue dans le panneau gauche
	 * @param cartegauche
	 */
	public void setCartegauche(ArrayList<Carte> cartegauche) {
		this.cartegauche = cartegauche;
	}

	/**
	 * 
	 * @return La liste des cartes contenue dans le deck
	 */
	public ArrayList<Carte> getCartedecks() {
		return cartedecks;
	}

	/**
	 * Modifie la liste des cartes contenue dans le deck
	 * @param cartedecks
	 */
	public void setCartedecks(ArrayList<Carte> cartedecks) {
		this.cartedecks = cartedecks;
	}

}
