
package com.mygdx.dragNdrop;

import com.badlogic.gdx.utils.Array;
import com.mygdx.coreLogic.cards.Carte;
import com.mygdx.coreLogic.paramGame.Niveau;
import com.mygdx.coreLogic.paramGame.paramGame;


public class Deck {

	private Array<Slot> slots;

	public Deck(PaneSide side) throws WrongSideException {
		Niveau level = paramGame.getCurrentLevel();

		slots = new Array<Slot>(level.getCartedecks().size());

		if (side.equals(PaneSide.CENTER)) {
			for(Carte card : level.getCartedecks())
				slots.add(new Slot(card, side, false));
		} else {
			throw new WrongSideException("Wrong side for deck");
		}

	}

	public Deck (Deck deck) {

		Niveau level = paramGame.getCurrentLevel();
		slots = new Array<Slot>(level.getCartedecks().size());

		for (Slot slot : deck.getSlots())
			slots.add(new Slot(slot));

	}
	
	public Array<Slot> getSlots() {
		return slots;
	}

}
