package com.mygdx.dragNdrop;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.coreLogic.cards.Carte;
import com.mygdx.coreLogic.paramGame.paramGame;


public class DeckActor extends Table {

	private Deck deck;
	private DragAndDrop dragAndDrop;
	private Skin skin;
	
	public DeckActor(Deck deck) {
		
		this.deck = deck;
		this.dragAndDrop = paramGame.getDragAndDrop();
		this.skin = paramGame.getSkin();
		
		
		for (Slot slot : deck.getSlots()) {
			SlotActor slotActor = new SlotActorDeck(skin, slot);
			dragAndDrop.addSource(new SlotSourceDeck(slotActor));
			dragAndDrop.addTarget(new SlotTargetDeck(slotActor));
			
			add(slotActor);
		}

		pack();


		setVisible(true);

	}

	public DeckActor(DeckActor deckActor) {
		
		this.deck = new Deck(deckActor.deck);
		this.dragAndDrop = paramGame.getDragAndDrop();
		this.skin = paramGame.getSkin();
		
		for (Slot slot : deck.getSlots()) {
			SlotActor slotActor = new SlotActorDeck(skin, slot);
			dragAndDrop.addSource(new SlotSourceDeck(slotActor));
			dragAndDrop.addTarget(new SlotTargetDeck(slotActor));
			
			add(slotActor);
		}

		pack();

		setVisible(true);

	}

	/**
	 * Bloque tout action des carte du Deck SAUF pour la carte {@link cardLeft} 
	 * @param cardLeft
	 */
	public void suspends(Carte cardLeft) {
		for (Slot slot : deck.getSlots()) {
			if (! slot.getCard().equals(cardLeft)) 
				slot.suspends();
			else
				slot.suspendInversee();
				
		}
	}
	
	public void resume () {
		for (Slot slot : deck.getSlots()) {
				slot.resume();
		}
	}

}
