package com.mygdx.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Cette classe permet le chargement de la plupart des ressources de manière 
 * asynchrone, de sorte que vous pouvez afficher un écran de chargement réactive
 * alors que les choses se chargent.
 * 
 * Les référence données sont comptabilisés. 
 * Si deux données A et B dépendent d'une autre donnée C, 
 * C ne seront pas supprimée jusqu'à ce que A et B ont été supprimées. 
 * Cela signifie également que si vous chargez un atout à plusieurs reprises, 
 * il sera effectivement partagé et ne prennent de la mémoire une fois!
 *
 */
public class Assets {
	public static AssetManager manager = new AssetManager();

	public static final String splash = "ui/splash.jpg";
	public static final String plateau = "ui/plateau.jpg";
	public static final String scene = "ui/scene.jpg";
	public static final String menu = "ui/menu.jpg";

	/**
	 * Charge les images en memoire
	 */
	public static void load() {
		manager.load(splash, Texture.class);
		manager.load(plateau, Texture.class);
		manager.load(scene, Texture.class);
		manager.load(menu, Texture.class);
	}

	/**
	 * Detruit le manager et supprime les images chargées en memoire
	 */
	public static void dispose() {
		manager.dispose();
	}
}
