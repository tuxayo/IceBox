package com.mygdx.coreLogic.paramGame;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.coreLogic.controller.ControllerGame;
import com.mygdx.dragNdrop.Deck;
import com.mygdx.dragNdrop.DeckActor;
import com.mygdx.dragNdrop.Pane;
import com.mygdx.dragNdrop.PaneActor;
import com.mygdx.dragNdrop.PaneSide;
import com.mygdx.dragNdrop.WrongSideException;

public class paramGame {

	public static final String PATH_LVL = "/home/abdelhak/workspace/IceBox/core/src/com/mygdx/coreLogic/levels/";
	public static final String PATH_PROFILE = "/home/abdelhak/Téléchargements/projet_long/joueur.xml";
	
	private static Profil joueur;
	private static List<Profil> allProfils;
	private static List<Chapitre> allChapitres;
	
	private static ControllerGame controller;
	private static DeckActor deckActor;
	private static PaneActor leftpaneActor;
	private static PaneActor rightpaneActor;
	private static DragAndDrop dragAndDrop;
	private static Skin skin;
	private static boolean preloaded = false;


	public static Skin getSkin() {
		return skin;
	}

	public static void setSkin(Skin skin) {
		paramGame.skin = skin;
	}

	public static DragAndDrop getDragAndDrop() {
		return dragAndDrop;
	}

	public static void setDragAndDrop(DragAndDrop dragAndDrop) {
		paramGame.dragAndDrop = dragAndDrop;
	}

	public static void setParamGame(DeckActor deckActor, PaneActor leftpaneActor,
			PaneActor rightpaneActor)
	{
		paramGame.setDeckActor(deckActor);
		paramGame.setLeftpaneActor(leftpaneActor);
		paramGame.setRightpaneActor(rightpaneActor);

		controller = new ControllerGame();
	}

	public paramGame() {
		super();
	}

	public static void initGame() {
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"), new TextureAtlas("ui/uiskin.pack"));
		dragAndDrop = new DragAndDrop();

		if ( ! preloaded) {
			allProfils = Profil.loadAllProfil(PATH_PROFILE);
			allChapitres = Chapitre.loadAllChapitre();
		}
		
		try {
			deckActor = new DeckActor(new Deck(PaneSide.CENTER));
			leftpaneActor = new PaneActor(new Pane(PaneSide.LEFT));
			rightpaneActor = new PaneActor(new Pane(PaneSide.RIGHT));
		} catch (WrongSideException e) {
			e.printStackTrace();
		}

		float X = (Gdx.graphics.getWidth() - deckActor.getWidth()) / 2.0f;
		deckActor.setPosition(X, 10);
		leftpaneActor.setPosition(72, 163);
		rightpaneActor.setPosition(502, 163);

		controller = new ControllerGame();	
	}

	/**
	 * Précharge les données utilisée par le jeu
	 */
	public static void preload() {
		preloaded = true;
		allProfils = Profil.loadAllProfil(PATH_PROFILE);
		allChapitres = Chapitre.loadAllChapitre();
	}
	
	
	
	
	public static Profil getJoueur() {
		return joueur;
	}

	public static void setJoueur(Profil joueur) {
		paramGame.joueur = joueur;
	}

	public static ControllerGame getController() {
		return controller;
	}

	public static void setController(ControllerGame controller) {
		paramGame.controller = controller;
	}

	public static DeckActor getDeckActor() {
		return deckActor;
	}

	public static void setDeckActor(DeckActor deckActor) {
		paramGame.deckActor = deckActor;
	}

	public static PaneActor getLeftpaneActor() {
		return leftpaneActor;
	}

	public static void setLeftpaneActor(PaneActor leftpaneActor) {
		paramGame.leftpaneActor = leftpaneActor;
	}

	public static PaneActor getRightpaneActor() {
		return rightpaneActor;
	}

	public static PaneActor getNewRightPaneActor() {
		PaneActor rightpaneActor = new PaneActor(paramGame.rightpaneActor);
		rightpaneActor.setPosition(502, 163);

		return rightpaneActor;
	}

	public static PaneActor getNewLeftPaneActor() {
		PaneActor leftpaneActor = new PaneActor(paramGame.leftpaneActor);
		leftpaneActor.setPosition(72, 163);

		return leftpaneActor;
	}

	public static void setRightpaneActor(PaneActor rightpaneActor) {
		paramGame.rightpaneActor = rightpaneActor;
	}

	public static void removeRightpaneActor() {
		paramGame.rightpaneActor.remove();
		paramGame.rightpaneActor = null;
	}

	public static void removeLeftpaneActor() {
		paramGame.leftpaneActor.remove();
		paramGame.leftpaneActor = null;
	}

	public static void removeDeckpaneActor() {
		paramGame.deckActor.remove();
		paramGame.deckActor = null;
	}

	public static DeckActor getNewDeckActor() {
		DeckActor deckActor = new DeckActor(paramGame.deckActor);

		float X = (Gdx.graphics.getWidth() - deckActor.getWidth()) / 2.0f;
		deckActor.setPosition(X, 10);

		return deckActor;
	}

	public static List<Profil> getAllProfil() {
		return allProfils;
	}
	
	public static List<Chapitre> getAllChapitres() {
		return allChapitres;
	}
	
	public static Chapitre getCurrentChapitre() {
		return allChapitres.get(joueur.getChapEnCourt());
	}
	
	public static Niveau getCurrentLevel() {
		return getCurrentChapitre().getNiveau(joueur.getNivEnCourt());
	}

	public static Profil getJoueur(String name) {
		
		for (Profil profil : allProfils) {
			if (profil.getNom().equals(name))
				return profil;
		}		
		
		return null;
	}

} 
