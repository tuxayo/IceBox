package com.mygdx.dragNdrop;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Pane {

	private Array<Slot> slots;
	private int NbCardsInLeftPane = 20;

	public Pane(PaneSide side) {

		slots = new Array<Slot>(NbCardsInLeftPane);

		for (int i = 0; i < NbCardsInLeftPane; i++) { // Ici on récupere le nb de carte dans le panneau gauche !
			slots.add(new Slot(null, side));
		}

		// creation de cartes aléatoire
		for (Slot slot : slots) {
			slot.add(Item.values()[MathUtils.random(1, Item.values().length - 1)]);
		}

		// creation d'emplacement vide
		for (int i = 0; i < 5; i++) {
			Slot randomSlot = slots.get(MathUtils.random(0, slots.size - 1));
			randomSlot.take();
		}

	}

	public boolean store(Item item) {
		// check for an available empty slot
		Slot emptySlot = firstSlotWithItem(null);
		if (emptySlot != null) {
			emptySlot.add(item);
			return true;
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

