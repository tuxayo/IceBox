package com.mygdx.dragNdrop;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Cette classe effectue le rendu graphique d'un slot dans un des deux panneau, 
 * sous forme d'une image
 */
public class SlotActorPane extends SlotActor {

	/**
	 * Cree un SlotActorPane qui gere le rendu graphique du slot en paramètre, 
	 * @param skin
	 * @param slot
	 */
	public SlotActorPane(Skin skin, Slot slot) {
		super(skin, slot);

	}

}
