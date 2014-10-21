
package com.mygdx.dragNdrop;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;


public class Inventory {

	private Array<Slot> slots;
	private int NbCardsInDeck = 8;
	
	public Inventory() {
		
		slots = new Array<Slot>(NbCardsInDeck);
		
		for (int i = 0; i < NbCardsInDeck; i++) { // Ici on récupere le nb de carte de deck !
			slots.add(new Slot(null));
		}

		// creation de cartes aléatoire
		for (Slot slot : slots) {
			slot.add(Item.values()[MathUtils.random(0, Item.values().length - 1)]);
		}

	}


	public boolean store(Item item) {
		// first check for a slot with the same item type
		Slot itemSlot = firstSlotWithItem(item);
		
		if (itemSlot != null) {
			itemSlot.add(item);
			return true;
		} else {
			// now check for an available empty slot
			Slot emptySlot = firstSlotWithItem(null);
			if (emptySlot != null) {
				emptySlot.add(item);
				return true;
			}
		}

		// no slot to add
		return false;
	}

	
	public Array<Slot> getSlots() {
		return slots;
	}

	private Slot firstSlotWithItem(Item item) {
		for (Slot slot : slots) {
			if (slot.getItem() == item) {
				return slot;
			}
		}

		return null;
	}

}
