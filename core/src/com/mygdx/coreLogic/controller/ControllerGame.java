package com.mygdx.coreLogic.controller;

import java.util.Stack;

import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.coreLogic.cards.Carte;
import com.mygdx.coreLogic.paramGame.paramGame;
import com.mygdx.dragNdrop.DeckActor;
import com.mygdx.dragNdrop.PaneActor;
import com.mygdx.dragNdrop.PaneSide;
import com.mygdx.game.screen.PlayScreen;
import com.mygdx.parser.ExpressionParser;

/**
 * Cette classe modélise le controleur qui va gérer les comportements 
 * des panneaux
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
	 * Constructeur d'un contrôleur avec trois paramètres
	 * @param pg
	 * @param pd
	 * @param deck
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
				paramGame.getRightpaneActor().suspends();
				paramGame.getDeckActor().suspends(newcarte);
				return true;
			} else if (cardRight == null && newcarte.getValeur() == cardLeft.getValeur()) {
				cardRight = cardLeft = null;
				paramGame.getRightpaneActor().resume();
				paramGame.getLeftpaneActor().resume();
				paramGame.getDeckActor().resume();
				return true;
			} else if (cardRight != null) {
				return false;
			} else {
				return false;
			}
			
		} else if (side == PaneSide.LEFT) {
			
			if (cardLeft == null && cardRight == null) {
				cardLeft = newcarte;
				paramGame.getLeftpaneActor().suspends();
				paramGame.getDeckActor().suspends(newcarte);
				return true;
			} else if (cardLeft == null && newcarte.getValeur() == cardRight.getValeur()) {
				cardRight = cardLeft = null;
				paramGame.getRightpaneActor().resume();
				paramGame.getLeftpaneActor().resume();
				paramGame.getDeckActor().resume();
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
	 * Renvoi {@code True} si un des deux panneau est vide 
	 * @return Renvoie {@code True} si le niveau est fini {@code False} sinon
	 */
	public boolean checkGameState() {
		return paramGame.getLeftpaneActor().isEmpty() || paramGame.getRightpaneActor().isEmpty();
	}
	
	/**
	 * Predicat pour savoir si les panneaux sont simplifiées ou non
	 * @return retourne True si les deux panneaux sont simplifiées, False sinon
	 */
	public boolean isSimpified() {
		
		String leftExpr = paramGame.getLeftpaneActor().stringify();
		String rightExpr = paramGame.getRightpaneActor().stringify();

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
		
		
		System.out.println("Expression gauche : " + LeftTree);
		System.out.println(simplLeftTree);
		
		System.out.println("Expression droite : " + RightTree);
		System.out.println(simplRightTree);		
	

		return LeftTree.equals(simplLeftTree) && RightTree.equals(simplRightTree);
		
	}

	/**
	 * Restore la configuration precedente du platteau
	 */
	public void undo() {
		System.out.println("undo !");	
		
		if (previous_pd.isEmpty() || previous_pg.isEmpty()) 
			return;
		
		paramGame.setDragAndDrop(new DragAndDrop());
		
		paramGame.removeRightpaneActor();
		paramGame.removeLeftpaneActor();
		paramGame.removeDeckpaneActor();
		
		paramGame.setRightpaneActor(previous_pd.pop());
		paramGame.setLeftpaneActor(previous_pg.pop());
		paramGame.setDeckActor(previous_pc.pop());
		
		PlayScreen.stage.addActor(paramGame.getRightpaneActor());
		PlayScreen.stage.addActor(paramGame.getLeftpaneActor());
		PlayScreen.stage.addActor(paramGame.getDeckActor());
		
		cardLeft  = previous_cardLeft.pop();
		cardRight = previous_cardRight.pop();
		
	}
	
	/**
	 * Sauvegarde la configuration courrante du platteau
	 */
	public void saveLastMove() {
		System.out.println("save !");	
		
		previous_pd.push(paramGame.getNewRightPaneActor());
		previous_pg.push(paramGame.getNewLeftPaneActor());
		previous_pc.push(paramGame.getNewDeckActor());
		
		previous_cardLeft.push(cardLeft);
		previous_cardRight.push(cardRight);
		
	}

	
}


