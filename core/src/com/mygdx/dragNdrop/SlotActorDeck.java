package com.mygdx.dragNdrop;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Cette classe effectue le rendu graphique d'un slot dans le deck, 
 * sous forme d'une image
 */
public class SlotActorDeck extends SlotActor {

	private EventListener clickRetournerCarte;
	
	/**
	 * Cree un SlotActorDeck qui gere le rendu graphique du slot en paramètre, 
	 * et lui ajoute la propriété que la carte qu'il contient peut etre retourné
	 * @param skin
	 * @param slot slot qui se trouve dans le deck
	 */
	public SlotActorDeck(Skin skin, Slot slot) {
		super(skin, slot);
		clickRetournerCarte = new ClickDeckListener();
		
		/* On ajoute le fait que dans le deck on puisse clické pour retourner une carte */
		addListener(clickRetournerCarte);
	}

	/**
	 * Supprimme la capacité de l'image contenu dans le slot a étre retourné
	 */
	public void removeClickDeckListener() {
		removeListener(clickRetournerCarte);
	}
	
}
