package com.mygdx.dragNdrop;

import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;


public class SlotTargetDeck extends Target {

	public SlotTargetDeck(SlotActor actor) {
		super(actor);
		actor.getSlot();
//		getActor().setColor(Color.LIGHT_GRAY);
	}

	@Override
	public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
		
		// On refuse TOUT les drop sur le deck !
//		getActor().setColor(Color.DARK_GRAY);
		return false;

	}

	@Override
	public void drop(Source source, Payload payload, float x, float y, int pointer) {
	}

	@Override
	public void reset(Source source, Payload payload) {
//		getActor().setColor(Color.LIGHT_GRAY);
	}


}
