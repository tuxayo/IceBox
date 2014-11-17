package com.mygdx.coreLogic.controller;

import java.util.Stack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.coreLogic.cards.Carte;
import com.mygdx.coreLogic.paramGame.Profil;
import com.mygdx.coreLogic.paramGame.paramGame;
import com.mygdx.dragNdrop.DeckActor;
import com.mygdx.dragNdrop.PaneActor;
import com.mygdx.dragNdrop.PaneSide;
import com.mygdx.game.screen.PlayScreen;
import com.mygdx.parser.ExpressionParser;

/**
 * Singleton modélise le controleur qui va gérer les 
 * comportements logique du jeu
 *
 */
public class ControllerGame {

	private Stack<PaneActor> previous_pg = new Stack<PaneActor>();
	private Stack<PaneActor> previous_pd = new Stack<PaneActor>();
	private Stack<DeckActor> previous_pc = new Stack<DeckActor>();
	private Stack<Carte> previous_cardLeft = new Stack<Carte>();
	private Stack<Carte> previous_cardRight = new Stack<Carte>();

	
	private Carte cardLeft;
	private Carte cardRight;

	/**
	 * Constructeur d'un contrôleur
	 */
	public ControllerGame() {
		cardLeft = null;
		cardRight = null;
	}

	/**
	 * Fonction qui est appellé à chaque fois qu'une carte a été ajoutée 
	 * dans un panneau. Elle vérifie que l'équilibre est respecté entre les
	 * deux panneaux à chaque ajout. 
	 * @param newcarte
	 * @param side
	 * @return True si l'ajout est autorisé False sinon
	 */
	public boolean Verification(Carte newcarte, PaneSide side) {

		if (side == PaneSide.RIGHT) {

			if (cardRight == null && cardLeft == null) {
				cardRight = newcarte;
				paramGame.getInstance().getRightpaneActor().suspends();
				paramGame.getInstance().getDeckActor().suspends(newcarte);
				return true;
			} else if (cardRight == null && newcarte.getValeur() == cardLeft.getValeur()) {
				cardRight = cardLeft = null;
				paramGame.getInstance().getRightpaneActor().resume();
				paramGame.getInstance().getLeftpaneActor().resume();
				paramGame.getInstance().getDeckActor().resume();
				return true;
			} else if (cardRight != null) {
				return false;
			} else {
				return false;
			}

		} else if (side == PaneSide.LEFT) {

			if (cardLeft == null && cardRight == null) {
				cardLeft = newcarte;
				paramGame.getInstance().getLeftpaneActor().suspends();
				paramGame.getInstance().getDeckActor().suspends(newcarte);
				return true;
			} else if (cardLeft == null && newcarte.getValeur() == cardRight.getValeur()) {
				cardRight = cardLeft = null;
				paramGame.getInstance().getRightpaneActor().resume();
				paramGame.getInstance().getLeftpaneActor().resume();
				paramGame.getInstance().getDeckActor().resume();
				return true;
			} else if (cardLeft != null) {
				return false;
			} else {
				return false;
			}

		} else {
			return false;
		}

	}

	/**
	 * Renvoi {@code True} si le panneau contenant la carte box est vide
	 * @return Renvoie {@code True} si le niveau est fini {@code False} sinon
	 */
	public boolean checkGameState() {
		return paramGame.getInstance().getLeftpaneActor().isEmpty() && paramGame.getInstance().getLeftpaneActor().containBoxCard() 
				|| paramGame.getInstance().getRightpaneActor().isEmpty() && paramGame.getInstance().getRightpaneActor().containBoxCard();
	}

	/**
	 * Predicat pour savoir si les panneaux sont simplifiées ou non
	 * @return retourne True si les deux panneaux sont simplifiées, False sinon
	 */
	public boolean isSimpified() {

		String leftExpr = paramGame.getInstance().getLeftpaneActor().stringify();
		String rightExpr = paramGame.getInstance().getRightpaneActor().stringify();

		ExpressionParser expL = new ExpressionParser(leftExpr);
		ExpressionParser expR = new ExpressionParser(rightExpr);

		ExpressionParser expLSimpl = new ExpressionParser(leftExpr);
		ExpressionParser expRSimpl = new ExpressionParser(rightExpr);

		String LeftTree = null;
		String RightTree = null;

		String simplLeftTree = null;
		String simplRightTree = null;


		try {
			LeftTree = expL.parse().postfix();
			RightTree = expR.parse().postfix();
			simplLeftTree = expLSimpl.parse().simplify().postfix();
			simplRightTree = expRSimpl.parse().simplify().postfix();
		} catch (Exception e1) {
			e1.printStackTrace();
		}


//		System.out.println("Expression gauche : " + LeftTree);
//		System.out.println(simplLeftTree);
//
//		System.out.println("Expression droite : " + RightTree);
//		System.out.println(simplRightTree);		


		return LeftTree.equals(simplLeftTree) && RightTree.equals(simplRightTree);

	}

	/**
	 * Restore la configuration precedente du platteau
	 */
	public void undo() {
		System.out.println("undo !");	

		if (previous_pd.isEmpty() || previous_pg.isEmpty()) 
			return;

		paramGame.getInstance().setDragAndDrop(new DragAndDrop());

		paramGame.getInstance().removeRightpaneActor();
		paramGame.getInstance().removeLeftpaneActor();
		paramGame.getInstance().removeDeckpaneActor();

		paramGame.getInstance().setRightpaneActor(previous_pd.pop());
		paramGame.getInstance().setLeftpaneActor(previous_pg.pop());
		paramGame.getInstance().setDeckActor(previous_pc.pop());

		PlayScreen.stage.addActor(paramGame.getInstance().getRightpaneActor());
		PlayScreen.stage.addActor(paramGame.getInstance().getLeftpaneActor());
		PlayScreen.stage.addActor(paramGame.getInstance().getDeckActor());

		cardLeft  = previous_cardLeft.pop();
		cardRight = previous_cardRight.pop();

	}

	/**
	 * Sauvegarde la configuration courrante du platteau <p/>
	 * 
	 * /!\ PEUT ETRE FACILEMENT FAITE DE MANIERE <b>BOUCOUP</b> PLUS EFFICIENTE
	 */
	@Deprecated
	public void saveLastMove() {
		System.out.println("save !");	

		previous_pd.push(paramGame.getInstance().getNewRightPaneActor());
		previous_pg.push(paramGame.getInstance().getNewLeftPaneActor());
		previous_pc.push(paramGame.getInstance().getNewDeckActor());
		
		previous_cardLeft.push(cardLeft);
		previous_cardRight.push(cardRight);

	}

	/**
	 * Teste si c'est la fin d'un niveau et lance le niveau suivant
	 */
	public void checkEndLevel() {
		if (paramGame.getInstance().getController().checkGameState() && paramGame.getInstance().getController().isSimpified()) {
			
			// System.out.println("fini !!");
			
			/**
			 * TODO Attention implementer une fonction dans paramGame qui automatise le 
			 * passage d'un nouveau niveau et potentiellement un chapitre
			 */
			if (paramGame.getInstance().getJoueur().getNivEnCourt() == 10) {
				System.out.println("fin du jeu !");
			}
			
			paramGame.getInstance().getJoueur().setNivEnCourt(paramGame.getInstance().getJoueur().getNivEnCourt() + 1);
			Profil.updateLevel(paramGame.PATH_PROFILE, paramGame.getInstance().getJoueur());
			((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen());
		}
		
	}

}
