
package com.mygdx.dragNdrop;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;


/**
 * Represente une petite fenetre qui affiche les 
 * informations un slot
 *
 */
public class SlotTooltip extends Window implements SlotListener {

	private Skin skin;
	private Slot slot;

	/**
	 * Construit un slot affichant les information du slot passé en parametre
	 * @param slot
	 * @param skin
	 */
	public SlotTooltip(Slot slot, Skin skin) {
		super("Tooltip...", skin);
		this.slot = slot;
		this.skin = skin;
		hasChanged(slot);
		slot.addListener(this);
		setVisible(false);
	}

	@Override
	public void hasChanged(Slot slot) {
		if (slot.isEmpty()) {
			setVisible(false);
			return;
		}

		setTitle("" + slot.getSide() + "x " + slot.getCard());
		clear();
		Label label = new Label("Carte " + slot.getCard(), skin);
		add(label);
		pack();
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		// le listener positionne la visibilité a True quand un slot est survolée
		// deplus on ne veut rien afficher si le slot est vide, 
		if (slot.isEmpty()) {
			super.setVisible(false);
		}
	}

}
