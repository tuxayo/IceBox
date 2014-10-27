
package com.mygdx.dragNdrop;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;


public class Deck {

	private Array<Slot> slots;
	private int NbCardsInDeck = 8;

	public Deck(PaneSide side) {

		slots = new Array<Slot>(NbCardsInDeck);

		for (int i = 0; i < NbCardsInDeck; i++) { // Ici on récupere le nb de carte de deck !
			slots.add(new Slot(null, side));
		}

		// creation de cartes aléatoire
		for (Slot slot : slots) {
			slot.add(Item.values()[MathUtils.random(1, Item.values().length - 1)]);
		}
	}

	public Array<Slot> getSlots() {
		return slots;
	}

}
