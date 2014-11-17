package com.mygdx.dragNdrop;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.mygdx.coreLogic.paramGame.paramGame;

/**
 * Défini une source valide pour un drag And Drop depuis un panneau, 
 * et lui fourni un payload (ie l'information qui va etre du "Drand and Dropé")
 * 
 */
public class SlotSourcePane extends Source {

	private Slot sourceSlot;


	public SlotSourcePane(SlotActor actor) {
		super(actor);
		this.sourceSlot = actor.getSlot();
	}

	@Override
	public Payload dragStart(InputEvent event, float x, float y, int pointer) {

		Payload payload = new Payload();
		Slot payloadSlot = new Slot(sourceSlot);
		sourceSlot.take();
		payload.setObject(payloadSlot);

		if (payloadSlot.getCard() == null) return null;
		
		TextureAtlas icons = new TextureAtlas("ui/uiskin.pack");	
		TextureRegion icon = icons.findRegion(payloadSlot.getCard().getTextureRegion());

		Actor dragActor = new Image(icon);
		payload.setDragActor(dragActor);

		Actor validDragActor = new Image(icon);
		validDragActor.setColor(0, 1, 0, 1);
		payload.setValidDragActor(validDragActor);

		Actor invalidDragActor = new Image(icon);
		invalidDragActor.setColor(1, 0, 0, 1);
		payload.setInvalidDragActor(invalidDragActor);

		return payload;
	}


	@Override
	public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
		Slot payloadSlot = (Slot) payload.getObject();
		
		
		
		if (target != null) {
			Slot targetSlot = ((SlotActor) target.getActor()).getSlot();
			
			if (payloadSlot.isSuspended() || targetSlot.isSuspended()) {
				sourceSlot.add(payloadSlot.getCard());
				return;
			}
			
			
			paramGame.getInstance().getController().saveLastMove();
			
			
			if (targetSlot.getCard() == null) {
				targetSlot.add(payloadSlot.getCard());				
			} else if (targetSlot.isOpposite(payloadSlot)) {
				targetSlot.setToZero();
			} else {
				sourceSlot.swapWith(targetSlot, payloadSlot);
			}
			
		} else {
			sourceSlot.add(payloadSlot.getCard());
		}

	}
}
