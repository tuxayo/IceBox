
package com.mygdx.dragNdrop;

import com.badlogic.gdx.utils.Array;
import com.mygdx.coreLogic.cards.Carte;
import com.mygdx.coreLogic.paramGame.Niveau;


public class Deck {

	private Array<Slot> slots;
	
	public Deck(PaneSide side, Niveau level) throws WrongSideException {

		slots = new Array<Slot>(level.getCartedecks().size());

		if (side.equals(PaneSide.CENTER)) {
			for(Carte card : level.getCartedecks())
				slots.add(new Slot(card, side));
		} else {
			throw new WrongSideException("Wrong side for deck");
		}

	}

	public Array<Slot> getSlots() {
		return slots;
	}

}
