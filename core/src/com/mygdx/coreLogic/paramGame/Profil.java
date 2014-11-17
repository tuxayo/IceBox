package com.mygdx.coreLogic.paramGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *	Represente un Profil avec son nom, son nombre d'étoile, le numéro du chapitre 
 *	en cours, le numéro du niveau en cours, l'image associée a ce profil
 */
public class Profil {

	private static Document read;
	private String nom;
	private int chapEnCourt;
	private int nivEnCourt;
	private int nbEtoile;
	private String img;

	
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
	 * Récupère le profil d'un joueur. Renvoi null si le joueur n'a pas été trouvé
	 * @param file 
	 * @param player le nom du joueur a réccupéré
	 * @return Renvoi le profil de ce joueur, null si il n'existe pas
	 */
	public static Profil loadProfil(String file, String player) {

		Profil profil = null;

		lirefichier(file);

		Element racine= read.getRootElement();
		List<?> listejoueur= racine.getChildren("joueur");
		Iterator<?> i = listejoueur.iterator();

		while(i.hasNext()){
			Element joueur = (Element)i.next();

			if ( joueur.getChildText("nom").equals(player)) {
				String nomimg = joueur.getAttributeValue("nomimg");
				
				List<?> listechapitre= joueur.getChildren("chapitre");
				Element dernierchapitre= (Element)(listechapitre.get(listechapitre.size()-1));

				System.out.println("dernier chapitre de  " + player + " est "+ dernierchapitre.getText() );

				List<?> listeniveau = dernierchapitre.getChildren("niveau");
				Element dernierniveau = (Element)(listeniveau.get(listeniveau.size()-1));

				System.out.println("dernier niveau  "+ dernierniveau.getText()
						+ " avec "+ dernierniveau.getAttributeValue("nb_etoile"));

				profil = new Profil(player, 
						Integer.parseInt(dernierchapitre.getText()), 
						Integer.parseInt(dernierniveau.getText()), 
						Integer.parseInt(dernierniveau.getAttributeValue("nb_etoile")), 
						nomimg);
			}
		}
		return profil;
	}



	/**
	 * Renvoie la liste de tout les profils sauvegardés
	 * @param file
	 * @return Renvoie la liste de tout les profils sauvegardés
	 */
	public static List<Profil> loadAllProfil(String file) {


		List<Profil> players = new LinkedList<Profil>();
		lirefichier(file);

		Element racine= read.getRootElement();
		List<?> listejoueur= racine.getChildren("joueur");
		Iterator<?> i = listejoueur.iterator();

		while(i.hasNext()){
			Element joueur = (Element)i.next();
			String nomimg=joueur.getAttributeValue("nomimg");

			List<?> listechapitre= joueur.getChildren("chapitre");
			Element dernierchapitre= (Element)(listechapitre.get(listechapitre.size()-1));

			System.out.println("dernier chapitre de  " + joueur.getChildText("nom") + " est "+ dernierchapitre.getText() );

			List<?> listeniveau = dernierchapitre.getChildren("niveau");
			Element dernierniveau = (Element)(listeniveau.get(listeniveau.size()-1));

			System.out.println("dernier niveau  "+ dernierniveau.getText()
					+ " avec "+ dernierniveau.getAttributeValue("nb_etoile"));

			players.add(new Profil(joueur.getChildText("nom"), 
							Integer.parseInt(dernierchapitre.getText().replaceAll("[^0-9]", "")), 
							Integer.parseInt(dernierniveau.getText().replaceAll("[^0-9]", "")), 
							Integer.parseInt(dernierniveau.getAttributeValue("nb_etoile")), 
							nomimg)
						);

		}
		
		return players;
	}




	/**
	 * Sauvegarde un profil
	 * @param file
	 * @param player
	 */
	public static void saveProfil(String file, Profil player) {

		if (player == null) return;

		String name = player.getNom();
		String chapitre = Integer.toString(player.getChapEnCourt());
		String niveau = Integer.toString(player.getNivEnCourt());
		String nbetoile = Integer.toString(player.getNbEtoile());
		String img = player.getImg();

		lirefichier(file);
		boolean change = false;

		Element racine= read.getRootElement();
		List<?> listejoueur= racine.getChildren("joueur");
		Iterator<?> i = listejoueur.iterator();

		while(i.hasNext() && change == false) {
			Element joueur = (Element)i.next();

			if ( joueur.getChildText("nom").equals(name)){
				Attribute nomimg = new Attribute("nomimg",img);
				joueur.setAttribute(nomimg);
				Element Echapitre = new Element("chapitre");
				Echapitre.setText(chapitre);
				joueur.addContent(Echapitre);
				Element Eniveau = new Element("niveau");
				Eniveau.setText(niveau);
				Echapitre.addContent(Eniveau);
				Attribute nb_etoile = new Attribute("nb_etoile",nbetoile);
				Eniveau.setAttribute(nb_etoile);
				change=true;

			}
		}
		try {
			enregistreFichier(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sauvegarde le dernier niveau et le nombre d'etoile d'un joueur 
	 * @param file
	 * @param player
	 */
	public static void updateLevel(String file, Profil player) {

		if (player == null) return;

		String name = player.getNom();
		String chapitre = Integer.toString(player.getChapEnCourt());
		String niveau = Integer.toString(player.getNivEnCourt());
		String nbetoile = Integer.toString(player.getNbEtoile());

		boolean change = false;
		lirefichier(file);
		Element racine= read.getRootElement();
		List<?> listejoueur= racine.getChildren("joueur");
		Iterator<?> i = listejoueur.iterator();

		while(i.hasNext() && change==false){
			Element joueur = (Element)i.next();

			if ( joueur.getChildText("nom").equals(name)){
				List<?> listechapitre= joueur.getChildren("chapitre");
				Iterator<?> j = listechapitre.iterator();
				boolean trouve = false;

				while(j.hasNext() && trouve==false){
					Element Echapitre = (Element)j.next();

					if ( Echapitre.getText().replaceAll("[^0-1]", "").equals(chapitre)){
						Element Eniveau = new Element("niveau");
						Eniveau.setText(niveau);
						Echapitre.addContent(Eniveau);
						Attribute nb_etoile = new Attribute("nb_etoile", nbetoile);
						Eniveau.setAttribute(nb_etoile);
						trouve=true;
						change=true;
					}
				}
			}
		}
		try {
			enregistreFichier(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Ajoute un nouveau joueur dans le fichier xml
	 * @param file
	 * @param nom
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void addplayer(String file, String nom) throws FileNotFoundException, IOException{

		lirefichier(file);
		Element racine;

		racine=read.getRootElement();

		Element joueur=new Element ("joueur");
		racine.addContent(joueur);

		Element name = new Element("nom");
		name.setText(nom);

		joueur.addContent(name);
		enregistreFichier(file);
	}



	/**
	 * Sauvegarde un nouveau chapitre
	 * @param file
	 * @param player
	 * @param chapitre
	 */
	public void ajouterchapitre(String file, String player, String chapitre) {
		lirefichier(file);
		boolean change = false;
		Element racine= read.getRootElement();
		List<?> listejoueur= racine.getChildren("joueur");
		Iterator<?> i = listejoueur.iterator();

		while(i.hasNext() && change == false){
			Element joueur = (Element)i.next();

			if ( joueur.getChildText("nom").equals(player)){
				Element Echapitre = new Element("chapitre");
				Echapitre.setText(chapitre);
				joueur.addContent(Echapitre);
				change = true;
			}
		}
		try {
			enregistreFichier(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



	/**
	 * Lis le fichier
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
	 * Enregistre les modification dans le fichier xml
	 * @param fichier
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void enregistreFichier(String fichier) throws FileNotFoundException, IOException {
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat ());
		sortie.output( read, new FileOutputStream(fichier));
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

	/**
	 * Renvoi le chapitre demandé si il n'est pas supérieur au
	 * dernier chapitre atteint par le joueur
	 * @param numChap le numero du chapitre a réccupéré
	 * @return Le chapitre demandée
	 */
	public Chapitre getChapitre(int numChap) {
		return paramGame.getInstance().getAllChapitres().get(
				numChap > chapEnCourt ? chapEnCourt : numChap );
	}

	/**
	 * @return Renvoie le dernier chapitre atteint par le joueur
	 */
	public Chapitre getLastChapitre() {
		return paramGame.getInstance().getAllChapitres().get(chapEnCourt);
	}


}
