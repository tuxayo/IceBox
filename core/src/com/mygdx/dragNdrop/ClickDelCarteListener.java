package com.mygdx.dragNdrop;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.coreLogic.paramGame.paramGame;

/**
 * Listener qui supprime une carte si elle est clické
 *
 */
public class ClickDelCarteListener extends ClickListener {

	@Override
	public void clicked(InputEvent event, float x, float y) {
		//System.out.println("Button del cliked!!");
		
		((SlotActor)(event.getTarget())).getSlot().take();
		paramGame.getInstance().getController().checkEndLevel();
	}

}
