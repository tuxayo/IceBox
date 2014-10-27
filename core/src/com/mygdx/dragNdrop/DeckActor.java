package com.mygdx.dragNdrop;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;


public class DeckActor extends Table {

	public DeckActor(Deck deck, DragAndDrop dragAndDrop, Skin skin) {
		
		for (Slot slot : deck.getSlots()) {
			SlotActor slotActor = new SlotActorDeck(skin, slot);
			dragAndDrop.addSource(new SlotSourceDeck(slotActor));
			dragAndDrop.addTarget(new SlotTargetDeck(slotActor));
			
			add(slotActor);
		}

		pack();

		// cacher par defaut au niveau 1 par exemple
//		setVisible(false);
		setVisible(true);

	}

}
