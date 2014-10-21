package com.mygdx.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
	public static AssetManager manager = new AssetManager();

	public static final String splashScreen = "ui/splash-screen.jpg";
	public static final String plateau = "ui/plateau.png";

	public static void load() {
		manager.load(splashScreen, Texture.class);
		manager.load(plateau, Texture.class);
	}

	public static void dispose() {
		manager.dispose();
	}
}
