package com.mygdx.dragNdrop;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.screen.PlayScreen;


public class SlotActor extends Image implements SlotListener {

	private Slot slot;
	private Skin skin;
	private EventListener clickDelCarteListener;

	public EventListener getClickDelCarteListener() {
		return clickDelCarteListener;
	}

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
	
}
