
package com.mygdx.dragNdrop;

import com.badlogic.gdx.utils.Array;
import com.mygdx.coreLogic.cards.Carte;
import com.mygdx.coreLogic.paramGame.Niveau;
import com.mygdx.coreLogic.paramGame.paramGame;

/**
 * Cette classe s'occupe de géré un ensemble de {@link Slot} 
 * présent dans le Deck
 */
public class Deck {

	private Array<Slot> slots;
	
	/**
	 * Construit un deck qui gére les slot créés grace au 
	 * cartes du deck du niveau courrant pour le joueur courrant
	 * Une exception est levée si le side != PaneSide.CENTER
	 * @param side
	 * @throws WrongSideException
	 */
	public Deck(PaneSide side) throws WrongSideException {
		Niveau level = paramGame.getInstance().getCurrentLevel();

		slots = new Array<Slot>(level.getCartedecks().size());

		if (side.equals(PaneSide.CENTER)) {
			for(Carte card : level.getCartedecks())
				slots.add(new Slot(card, side, false));
		} else {
			throw new WrongSideException("Wrong side for deck");
		}

	}

	/**
	 * Constructeur par recopie, recree un nouveau deck
	 * @param deck
	 */
	public Deck (Deck deck) {

		Niveau level = paramGame.getInstance().getCurrentLevel();
		slots = new Array<Slot>(level.getCartedecks().size());

		for (Slot slot : deck.getSlots())
			slots.add(new Slot(slot));

	}
	
	/**
	 * 
	 * @return l'ensemble des slots que gére le deck
	 */
	public Array<Slot> getSlots() {
		return slots;
	}

}
