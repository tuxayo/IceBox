package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * 
 */
public class addAccountListenner extends ClickListener {

	@Override
	public void clicked(InputEvent event, float x, float y) {
		MyTextInputListener listener = new MyTextInputListener();
		Gdx.input.getTextInput(listener, "Entre ton nom et commence une partie !", "nom");
	
	}
}
