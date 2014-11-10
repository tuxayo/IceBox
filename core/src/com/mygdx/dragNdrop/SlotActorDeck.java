package com.mygdx.dragNdrop;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class SlotActorDeck extends SlotActor {

	private EventListener clickRetournerCarte;
	
	public SlotActorDeck(Skin skin, Slot slot) {
		super(skin, slot);
		clickRetournerCarte = new ClickDeckListener();
		
		/* On ajoute le fait que dans le deck on puisse clické pour retourner une carte */
		addListener(clickRetournerCarte);
	}

	public void removeClickDeckListener() {
		removeListener(clickRetournerCarte);
	}
	
}
