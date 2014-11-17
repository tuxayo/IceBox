package com.mygdx.dragNdrop;

import com.badlogic.gdx.utils.Array;
import com.mygdx.coreLogic.cards.Carte;
import com.mygdx.coreLogic.paramGame.Niveau;
import com.mygdx.coreLogic.paramGame.paramGame;

/**
 * Cette classe s'occupe de géré un ensemble de {@link Slot} 
 * présent dans un panneau
 */
public class Pane {

	private Array<Slot> slots;

	/**
	 * Construit un deck qui gére les slot créés grace au 
	 * cartes d'un panneau du niveau courrant pour le joueur courrant
	 * Une exception est levée si le side == PaneSide.CENTER
	 * @param side
	 * @throws WrongSideException
	 */
	public Pane(PaneSide side) throws WrongSideException {
		int emptySlot = 30;
		slots = new Array<Slot>(30);
		Niveau level = paramGame.getInstance().getCurrentLevel();

		if (side.equals(PaneSide.LEFT)) {
			emptySlot -= level.getCartegauche().size();
			
			for (Carte card : level.getCartegauche())
				slots.add(new Slot(card, side, false));
			
		} else if (side.equals(PaneSide.RIGHT)) {
			emptySlot -= level.getCartegauche().size();

			for (Carte card : level.getCartedroite())
				slots.add(new Slot(card, side, false));
			
		} else {
			throw new WrongSideException("Wrong side for pane");
		}
		
		for(int i = 1; i < emptySlot; ++i)
			slots.add(new Slot(null, side, false));

	}

	/**
	 * Constructeur par recopie, recree un nouveau paneau
	 * @param pane
	 */
	public Pane(Pane pane) {
		slots = new Array<Slot>(30);
		
		for (Slot slot : pane.getSlots())
			slots.add(new Slot(slot));
	}

	/**
	 * Ajoute une carte au premier slot vide rencontré
	 * @param card
	 * @return True si l'ajout a été effectué False sinon
	 */
	public boolean store(Carte card) {
		// check for an available empty slot
		Slot emptySlot = firstSlotWithItem(null);
		if (emptySlot != null) {
			emptySlot.add(card);
			return true;
		}

		// aucun slot a ajouter
		return false;
	}

	/**
	 * 
	 * @return La liste des Slots géré par ce panneau
	 */
	public Array<Slot> getSlots() {
		return slots;
	}

	/**
	 * 
	 * @param card
	 * @return Le premier slot contenant la carte passé en parametre, 
	 * renvoie null si la carte n'est pas dans le panneau 
	 */
	private Slot firstSlotWithItem(Carte card) {
		for (Slot slot : slots) {
			if (slot.getCard() == card) {
				return slot;
			}
		}
		return null;
	}
	
}

