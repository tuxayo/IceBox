package com.mygdx.dragNdrop;

import com.badlogic.gdx.utils.Array;
import com.mygdx.coreLogic.cards.Carte;
import com.mygdx.coreLogic.paramGame.Niveau;
import com.mygdx.coreLogic.paramGame.paramGame;

public class Pane {

	private Array<Slot> slots;

	public Pane(PaneSide side) throws WrongSideException {
		int emptySlot = 30;
		slots = new Array<Slot>(30);
		Niveau level = paramGame.getCurrentLevel();

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

	public Pane(Pane pane) {
		slots = new Array<Slot>(30);
		
		for (Slot slot : pane.getSlots())
			slots.add(new Slot(slot));
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

