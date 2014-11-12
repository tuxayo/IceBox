package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.coreLogic.paramGame.paramGame;


public class SelectAccountListenner extends ClickListener {

	@Override
	public void clicked(InputEvent event, float x, float y) {
		paramGame.setJoueur(paramGame.getJoueur(event.getListenerActor().getName()));
		( (Game) Gdx.app.getApplicationListener()).setScreen(new LevelScreen());
	}
}
