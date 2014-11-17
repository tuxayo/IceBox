package com.mygdx.dragNdrop;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.coreLogic.cards.Carte;
import com.mygdx.coreLogic.paramGame.paramGame;

/**
 * Cette classe effectue le rendu graphique d'un {@link Deck}, sous forme 
 * d'une liste de {@link DeckActor} (ie: une Liste d'image)
 */
public class DeckActor extends Table {

	private Deck deck;
	private DragAndDrop dragAndDrop;
	private Skin skin;
	
	
	/**
	 * Cree un DeckActor qui gere le rendu graphique du deck en paramètre
	 * @param deck
	 */
	public DeckActor(Deck deck) {
		
		this.deck = deck;
		this.dragAndDrop = paramGame.getInstance().getDragAndDrop();
		this.skin = paramGame.getInstance().getSkin();
		
		
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
	 * Constructeur par recopie 
	 * @param deckActor
	 */
	public DeckActor(DeckActor deckActor) {
		
		this.deck = new Deck(deckActor.deck);
		this.dragAndDrop = paramGame.getInstance().getDragAndDrop();
		this.skin = paramGame.getInstance().getSkin();
		
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
	 * Bloque tout action des carte du Deck SAUF pour une carte carte 
	 * @param cardLeft la carte qui ne doit pas etre bloqué entierement
	 */
	public void suspends(Carte cardLeft) {
		for (Slot slot : deck.getSlots()) {
			if (! slot.getCard().equals(cardLeft)) 
				slot.suspends();
			else
				slot.suspendInversee();
				
		}
	}
	
	/**
	 * Réactive entierement tout les slots du deck
	 */
	public void resume () {
		for (Slot slot : deck.getSlots()) {
				slot.resume();
		}
	}

}
