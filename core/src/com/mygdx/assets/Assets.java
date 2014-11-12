package com.mygdx.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
	public static AssetManager manager = new AssetManager();

	public static final String splash = "ui/splash.jpg";
	public static final String plateau = "ui/plateau.jpg";
	public static final String scene = "ui/scene.jpg";
	public static final String menu = "ui/menu.jpg";

	public static void load() {
		manager.load(splash, Texture.class);
		manager.load(plateau, Texture.class);
		manager.load(scene, Texture.class);
		manager.load(menu, Texture.class);
	}

	public static void dispose() {
		manager.dispose();
	}
}
