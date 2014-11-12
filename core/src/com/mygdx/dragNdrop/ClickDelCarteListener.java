package com.mygdx.dragNdrop;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.coreLogic.paramGame.paramGame;

public class ClickDelCarteListener extends ClickListener {

	@Override
	public void clicked(InputEvent event, float x, float y) {
		System.out.println("Button del cliked!!");
		
		((SlotActor)(event.getTarget())).getSlot().take();
		paramGame.getController().checkEndLevel();
	}

}
