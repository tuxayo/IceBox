package com.mygdx.dragNdrop;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.coreLogic.paramGame.paramGame;

/**
 * Cette classe effectue le rendu graphique d'un {@link Pane}, sous forme 
 * d'une grille de {@link SlotActor} (ie: une grille d'image)
 */
public class PaneActor extends Table {

	private Pane pane;
	private boolean isActive = true;
	private DragAndDrop dragAndDrop;
	private Skin skin;

	/**
	 * Cree un PaneActor qui gere le rendu graphique du panneau passé en paramètre
	 * @param pane
	 */
	public PaneActor(Pane pane) {

		this.dragAndDrop = paramGame.getInstance().getDragAndDrop();
		this.skin = paramGame.getInstance().getSkin();
		this.pane = pane;
		
		int i = 0;

		for (Slot slot : pane.getSlots()) {
			SlotActor slotActor = new SlotActorPane(skin, slot);
			dragAndDrop.addSource(new SlotSourcePane(slotActor));
			dragAndDrop.addTarget(new SlotTargetPane(slotActor));
			add(slotActor);

			if (slot.isZero())
				slotActor.addListener(slotActor.getClickDelCarteListener());

			i++;
			// toute les 6 cellules, on creer une nouvelle ligne
			if (i % 6 == 0) {
				row();
			}

		}

		defaults().space(6);
		row().fill().expandX();		
		pack();
		setVisible(true);
	}


	public PaneActor( PaneActor paneActor ) {
		this.dragAndDrop = paramGame.getInstance().getDragAndDrop();
		this.skin = paramGame.getInstance().getSkin();
		this.isActive = paneActor.isActive;
		this.pane = new Pane(paneActor.pane);
		
		int i = 0;

		for (Slot slot : pane.getSlots()) {
			SlotActor slotActor = new SlotActorPane(skin, slot);
			dragAndDrop.addSource(new SlotSourcePane(slotActor));
			dragAndDrop.addTarget(new SlotTargetPane(slotActor));
			add(slotActor);
			

			if (slot.isZero())
				slotActor.addListener(slotActor.getClickDelCarteListener());

			i++;
			// toute les 6 cellule, on creer une nouvelle ligne
			if (i % 6 == 0) {
				row();
			}

		}

		defaults().space(6);
		row().fill().expandX();
		//		setDebug(true, true);

		pack();

		setVisible(true);
		
	}
	
	public String stringify() {

		StringBuilder expr = new StringBuilder() ;

		for (Slot slot : pane.getSlots()) {
			expr.append(slot.toString());
		}

		if (expr.length() < 3) return new String();
		return expr.toString().substring(0, expr.length()-3);
	}


	/**
	 * Bloque toute action sur le panneau
	 */
	public void suspends() {
		isActive = false;

		for (Slot slot : pane.getSlots())
			slot.suspends();		
	}


	/**
	 * Renvoie Vrai ou Faux selon que le panneau est bloquée 
	 * @return Renvoie vrai si le panneau est suspendu, si non faux
	 */
	public boolean isSuspended() {
		return isActive;
	}


	/**
	 * Débloque toute action sur le panneau
	 */
	public void resume() {
		isActive = true;

		for (Slot slot : pane.getSlots())
			slot.resume();
	}

	/**
	 * Predicat pour tester le panneau est vide Attention la carte Box. 
	 * n'est PAS comptée comme une carte 
	 * @return True si le panneau est vide False si non.
	 */
	public boolean isEmpty() {
		int res = 0;

		for (Slot slot : pane.getSlots())
			res += (slot.isEmpty() || slot.isCarteBox()) ? 0 : 1;

		return res == 0;

	}


	/**
	 * Predicat pour tester le panneau contient la carte Box. 
	 * @return True si la carte Box est presente dans ce panneau False si non.
	 */
	public boolean containBoxCard() {

		for (Slot slot : pane.getSlots())
			if (slot.isCarteBox()) return true;

		return false;

	}
	
	
	public Pane getPane() {
		return pane;
	}


	public void setPane(Pane pane) {
		this.pane = pane;	
	}

}
