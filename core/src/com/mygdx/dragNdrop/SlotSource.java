package com.mygdx.dragNdrop;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;


public class SlotSource extends Source {

	private Slot sourceSlot;

	
	public SlotSource(SlotActor actor) {
		super(actor);
		this.sourceSlot = actor.getSlot();
		actor.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("Button turn cliked!!");
				((SlotActor)(event.getTarget().getParent())).getSlot().inverse();
			}
		});
	}

	@Override
	public Payload dragStart(InputEvent event, float x, float y, int pointer) {

		Payload payload = new Payload();
		Slot payloadSlot = new Slot(sourceSlot.getItem());
		payload.setObject(payloadSlot);

		TextureAtlas icons = new TextureAtlas("ui/uiskin.pack");	
		TextureRegion icon = icons.findRegion(payloadSlot.getItem().getTextureRegion());

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
			
			if (targetSlot.getItem() == payloadSlot.getItem() || targetSlot.getItem() == null ) {
				
				targetSlot.add(payloadSlot.getItem());
			} else {
				Item targetType = targetSlot.getItem();
				targetSlot.add(payloadSlot.getItem());
				sourceSlot.add(targetType);
			}
		} else {
			sourceSlot.add(payloadSlot.getItem());
		}
	}
}
