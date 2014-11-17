package com.mygdx.dragNdrop;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.screen.PlayScreen;

/**
 * Cette classe effectue le rendu graphique d'un slot, sous forme d'une image
 */
public class SlotActor extends Image implements SlotListener {

	private Slot slot;
	private Skin skin;
	private EventListener clickDelCarteListener;

	/**
	 *
	 * @return Le listener qui s'occupe de la destruction d'une carte Zero
	 */
	public EventListener getClickDelCarteListener() {
		return clickDelCarteListener;
	}

	/**
	 * Cree un SlotActor qui gere le rendu graphique du slot en paramètre
	 * @param skin
	 * @param slot
	 */
	public SlotActor(Skin skin, Slot slot) {
		super(createStyle(skin, slot));
		this.slot = slot;
		this.skin = skin;
		
		slot.addListener(this);
		
		clickDelCarteListener = new ClickDelCarteListener();
		
		SlotTooltip tooltip = new SlotTooltip(slot, skin);
		PlayScreen.stage.addActor(tooltip);
		addListener(new TooltipListener(tooltip, true));
	}

	/**
	 * Crée un nouveau style pour notre image, avec l'image correspondant 
	 * a la carte du slot 
	 * @param skin
	 * @param slot
	 * @return
	 */
	private static TextureRegion createStyle(Skin skin, Slot slot) {
		TextureAtlas icons = new TextureAtlas("ui/uiskin.pack");
		TextureRegion image;
		
		if (slot.getCard() != null) {
			image = icons.findRegion(slot.getCard().getTextureRegion());
		} else {
			image = icons.findRegion("transparent");
		}
		
		return image;
	}

	/**
	 * @return le slot
	 */
	public Slot getSlot() {
		return slot;
	}
	
	@Override
	public void hasChanged(Slot slot) {
		setDrawable(new TextureRegionDrawable(createStyle(skin, slot)));

		if (slot.isZero())
			addListener(clickDelCarteListener);
		else
			removeListener(clickDelCarteListener);
	}
	
	@Override
	public boolean addListener(EventListener listener) {
		return super.addListener(listener);
	}
	
	@Override
	public boolean removeListener(EventListener listener) {
		return super.removeListener(listener);
	}
	
	/**
	 * 
	 * @return True si le slot est dans l'état suspendu 
	 * ie:les actions possible sur le slot sont donc limités 
	 */
	public boolean isSuspended() {
		return slot.isSuspended();
	}
	
	/**
	 * Positionne l'état du slot a suspendu, afin de limité 
	 * les action sur ce dernier
	 */
	public void suspends() {
		slot.suspends();
	}
}
