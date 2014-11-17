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
 * Défini une source valide pour un drag And Drop depuis le deck, 
 * et lui fourni un payload (ie l'information qui va etre du "Drand and Dropé")
 *
 */
public class SlotSourceDeck extends Source {

	private Slot sourceSlot;
	
	/**
	 * Créée une source initalise la source comme étant le slotActor passé en parametre
	 * @param actor la source du drag and drop 
	 */
	public SlotSourceDeck(SlotActor actor) {
		super(actor);
		this.sourceSlot = actor.getSlot();
	}

	@Override
	public Payload dragStart(InputEvent event, float x, float y, int pointer) {

		Payload payload = new Payload();
		Slot payloadSlot = new Slot(sourceSlot);
		payload.setObject(payloadSlot);

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
			
			if (targetSlot.getCard() == null && !targetSlot.isSuspended() && !payloadSlot.isSuspended()) {
				
				paramGame.getInstance().getController().saveLastMove();
				
				targetSlot.add(payloadSlot.getCard());
			}
						
			boolean isValid = paramGame.getInstance().getController().Verification(payloadSlot.getCard(), targetSlot.getSide());
			System.out.println("isValidDrop : " + isValid);
		}
	}
}
