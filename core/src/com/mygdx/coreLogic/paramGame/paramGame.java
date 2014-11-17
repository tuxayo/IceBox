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


/**
 * Singleton qui est au coeur de la gestion du jeu et 
 * gere un grande partie de la logique
 *
 */
public final class paramGame {

	public static final String PATH_LVL = 
			"/home/abdelhak/workspace/IceBox/core/src/com/mygdx/coreLogic/levels/";
	public static final String PATH_PROFILE = 
			"/home/abdelhak/workspace/IceBox/core/src/com/mygdx/coreLogic/profiles/joueur.xml";
	
	private static paramGame instance = null;
	
	private Profil joueur;
	private List<Profil> allProfils;
	private List<Chapitre> allChapitres;
	
	private ControllerGame controller;
	private DeckActor deckActor;
	private PaneActor leftpaneActor;
	private PaneActor rightpaneActor;
	private DragAndDrop dragAndDrop;
	private Skin skin;
	private boolean preloaded = false;

	/**
	 * Constrcuteur privée vide
	 */
	private paramGame() {
		
	}
	
	/**
	 * 
	 * @return l'unique instance de paramGame ou en creer une nouevlle si c'est
	 * 			la premiere
	 */
	public static paramGame getInstance() {
		if (instance == null) {
			instance = new paramGame();
			return instance;
		} else {
			return instance;
		}
	}
	
	/**
	 * 
	 * @return le skin du jeu
	 */
	public Skin getSkin() {
		return skin;
	}

	/**
	 * modifie le skin du jeu
	 * @param skin
	 */
	public void setSkin(Skin skin) {
		this.skin = skin;
	}

	/**
	 * 
	 * @return l'objet qui gere les DragAndDrop 
	 */
	public DragAndDrop getDragAndDrop() {
		return dragAndDrop;
	}

	/**
	 * Modifie l'objet qui gere les DragAndDrop 
	 * @param dragAndDrop
	 */
	public void setDragAndDrop(DragAndDrop dragAndDrop) {
		this.dragAndDrop = dragAndDrop;
	}

	/**
	 * Modifie les trois paneaux du jeu
	 * @param deckActor
	 * @param leftpaneActor
	 * @param rightpaneActor
	 */
	public void setParamGame(DeckActor deckActor, PaneActor leftpaneActor,
			PaneActor rightpaneActor)
	{
		this.setDeckActor(deckActor);
		this.setLeftpaneActor(leftpaneActor);
		this.setRightpaneActor(rightpaneActor);

		controller = new ControllerGame();
	}

	/**
	 * Initialise le jeu
	 */
	public void initGame() {
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
	public void preload() {
		preloaded = true;
		allProfils = Profil.loadAllProfil(PATH_PROFILE);
		allChapitres = Chapitre.loadAllChapitre();
	}
	
	/**
	 * 
	 * @return le joueur courrant du jeu
	 */
	public Profil getJoueur() {
		return joueur;
	}

	/**
	 * Modifie le joueur courrant du jeu
	 * @param joueur
	 */
	public void setJoueur(Profil joueur) {
		this.joueur = joueur;
	}

	/**
	 * 
	 * @return le {@link ControllerGame} de ce jeu
	 */
	public ControllerGame getController() {
		return controller;
	}

	/**
	 * Modifie le {@link ControllerGame} de ce jeu
	 * @param controller
	 */
	public void setController(ControllerGame controller) {
		this.controller = controller;
	}

	/**
	 * 
	 * @return le {@link DeckActor} de ce jeu
	 */
	public DeckActor getDeckActor() {
		return deckActor;
	}

	/**
	 * Modifie le {@link DeckActor} de ce jeu
	 * @param deckActor
	 */
	public void setDeckActor(DeckActor deckActor) {
		this.deckActor = deckActor;
	}

	/**
	 * 
	 * @return le {@link PaneActor} gauche de ce jeu
	 */
	public PaneActor getLeftpaneActor() {
		return leftpaneActor;
	}

	/**
	 * Modifie le {@link PaneActor} gauche de ce jeu
	 * @param leftpaneActor
	 */
	public void setLeftpaneActor(PaneActor leftpaneActor) {
		this.leftpaneActor = leftpaneActor;
	}

	/**
	 * 
	 * @return le {@link PaneActor} droit de ce jeu
	 */
	public PaneActor getRightpaneActor() {
		return rightpaneActor;
	}

	/**
	 * Modifie le {@link PaneActor} droit de ce jeu
	 * @param rightpaneActor
	 */
	public void setRightpaneActor(PaneActor rightpaneActor) {
		this.rightpaneActor = rightpaneActor;
	}

	/**
	 * 
	 * @return un nouveau {@link PaneActor} identique a celui renvoyé 
	 * par {@link #getRightpaneActor()}
	 */
	public PaneActor getNewRightPaneActor() {
		PaneActor rightpaneActor = new PaneActor(this.rightpaneActor);
		rightpaneActor.setPosition(502, 163);

		return rightpaneActor;
	}

	/**
	 * 
	 * @return un nouveau {@link PaneActor} identique a celui renvoyé 
	 * par {@link #getLeftpaneActor()}
	 */
	public PaneActor getNewLeftPaneActor() {
		PaneActor leftpaneActor = new PaneActor(this.leftpaneActor);
		leftpaneActor.setPosition(72, 163);

		return leftpaneActor;
	}

	/**
	 * Supprimme le {@link PaneActor} Droit de ce jeu
	 */
	public void removeRightpaneActor() {
		this.rightpaneActor.remove();
		this.rightpaneActor = null;
	}

	/**
	 * Supprimme le {@link PaneActor} gauche de ce jeu
	 */
	public void removeLeftpaneActor() {
		this.leftpaneActor.remove();
		this.leftpaneActor = null;
	}

	/**
	 * Supprimme le {@link DeckActor} de ce jeu
	 */
	public void removeDeckpaneActor() {
		this.deckActor.remove();
		this.deckActor = null;
	}

	/**
	 * 
	 * @return un nouveau {@link DeckActor} identique a celui renvoyé 
	 * par {@link #getDeckActor()}
	 */
	public DeckActor getNewDeckActor() {
		DeckActor deckActor = new DeckActor(this.deckActor);

		float X = (Gdx.graphics.getWidth() - deckActor.getWidth()) / 2.0f;
		deckActor.setPosition(X, 10);

		return deckActor;
	}

	/**
	 * 
	 * @return la liste de tout les profils enregistrée 
	 */
	public List<Profil> getAllProfil() {
		return allProfils;
	}
	
	/**
	 * 
	 * @return La liste de tout les chapitres
	 */
	public List<Chapitre> getAllChapitres() {
		return allChapitres;
	}
	
	/**
	 * 
	 * @return le chapitre courrant 
	 */
	public Chapitre getCurrentChapitre() {
		return allChapitres.get(joueur.getChapEnCourt());
	}
	
	/**
	 * 
	 * @return Le niveau courrant du chapitre courrant
	 */
	public Niveau getCurrentLevel() {
		return getCurrentChapitre().getNiveau(joueur.getNivEnCourt());
	}

	/**
	 * 
	 * @param name nom du profil
	 * @return Le profil d'un joueur
	 */
	public Profil getJoueur(String name) {
		
		for (Profil profil : allProfils) {
			if (profil.getNom().equals(name))
				return profil;
		}		
		
		return null;
	}
	
} 
