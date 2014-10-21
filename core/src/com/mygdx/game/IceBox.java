package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.assets.Assets;
import com.mygdx.game.screen.SplashScreen;

public class IceBox extends Game {
	// VERSION : <numeroDuSprint> . <numeroSemaineDansSprint> . <NbfonctionaliteAjoutee>
	public static final String TITLE = "Ice Box ", VERSION = "1.2.6";

	@Override         
	public void create() {  
		Assets.load();
		Assets.manager.finishLoading();

		setScreen(new SplashScreen()); 
	}

	@Override
	public void render () {
		super.render();
	} 
	
	@Override
	public void dispose () {
		super.dispose();
		Assets.dispose();
	}
	
}
