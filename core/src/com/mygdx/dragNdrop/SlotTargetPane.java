package com.mygdx.dragNdrop;

import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;


public class SlotTargetPane extends Target {

	private Slot targetSlot;

	public SlotTargetPane(SlotActor actor) {
		super(actor);
		targetSlot = actor.getSlot();
		//		getActor().setColor(Color.LIGHT_GRAY);
	}

	@Override
	public boolean drag(Source source, Payload payload, float x, float y, int pointer) {

		Slot payloadSlot = (Slot) payload.getObject();

		if (targetSlot.getSide() == payloadSlot.getSide() || payloadSlot.getSide() == PaneSide.CENTER) {
			//			getActor().setColor(Color.WHITE);
			return true;
		} else {
			//			getActor().setColor(Color.DARK_GRAY);
			return false;
		}
	}

	@Override
	public void drop(Source source, Payload payload, float x, float y, int pointer) {		
	}

	@Override
	public void reset(Source source, Payload payload) {
		//		getActor().setColor(Color.LIGHT_GRAY);
	}


}
