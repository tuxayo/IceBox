package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.IceBox;

public class DesktopLauncher {
	
	public static void main (String[] arg) {
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = IceBox.TITLE + " v" + IceBox.VERSION;
		config.width = 900;
		config.height = 520;
		config.resizable = false;
		
		new LwjglApplication(new IceBox(), config);
	}
}