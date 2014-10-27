package com.mygdx.dragNdrop;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ClickDeckListener extends ClickListener {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			System.out.println("Button turn cliked!!");
			((SlotActor)(event.getTarget().getParent())).getSlot().inverse();
		}

}
