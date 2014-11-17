package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.assets.Assets;
import com.mygdx.game.screen.SplashScreen;


/**
 * Classe Pricipale du jeu ou est effectue un premier préchargement des images 
 * utilisée dans la suite du jeu, et qui lance la fenetre du jeu avec le premier 
 * "ecran"
 */
public class IceBox extends Game {
	// VERSION : <numeroDuSprint> . <numeroSemaineDansSprint> . <NbfonctionaliteAjoutee>
	public static final String TITLE = "Ice Box ", VERSION = "4.0.0";

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
