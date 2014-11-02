package com.mygdx.dragNdrop;

import com.badlogic.gdx.utils.Array;
import com.mygdx.coreLogic.cards.Carte;
import com.mygdx.coreLogic.paramGame.Niveau;

public class Pane {

	private Array<Slot> slots;

	public Pane(PaneSide side, Niveau level) throws WrongSideException {

		int emptySlot = 21;
		slots = new Array<Slot>(21);

		if (side.equals(PaneSide.LEFT)) {
			emptySlot -= level.getCartegauche().size();
			
			for (Carte card : level.getCartegauche())
				slots.add(new Slot(card, side));
			
		} else if (side.equals(PaneSide.RIGHT)) {
			emptySlot -= level.getCartegauche().size();

			for (Carte card : level.getCartedroite())
				slots.add(new Slot(card, side));
			
		} else {
			throw new WrongSideException("Wrong side for pane");
		}
		
		for(int i = 1; i < emptySlot; ++i)
			slots.add(new Slot(null, side));

	}

	public boolean store(Carte card) {
		// check for an available empty slot
		Slot emptySlot = firstSlotWithItem(null);
		if (emptySlot != null) {
			emptySlot.add(card);
			return true;
		}

		// no slot to add
		return false;
	}


	public Array<Slot> getSlots() {
		return slots;
	}


	private Slot firstSlotWithItem(Carte card) {
		for (Slot slot : slots) {
			if (slot.getCard() == card) {
				return slot;
			}
		}
		return null;
	}
	
}

