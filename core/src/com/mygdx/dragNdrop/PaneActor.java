package com.mygdx.dragNdrop;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.coreLogic.paramGame.paramGame;

public class PaneActor extends Table {

	private Pane pane;
	private boolean isActive = true;
	private DragAndDrop dragAndDrop;
	private Skin skin;

	public PaneActor(Pane pane) {

		this.dragAndDrop = paramGame.getDragAndDrop();
		this.skin = paramGame.getSkin();
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
			// toute les 5 cellule, on creer une nouvelle ligne
			if (i % 4 == 0) {
				row();
			}

		}

		defaults().space(4);
		row().fill().expandX();
		//		setDebug(true, true);

		
		pack();

		setVisible(true);
	}


	public PaneActor( PaneActor paneActor ) {
		this.dragAndDrop = paramGame.getDragAndDrop();
		this.skin = paramGame.getSkin();
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
			// toute les 5 cellule, on creer une nouvelle ligne
			if (i % 4 == 0) {
				row();
			}

		}

		defaults().space(4);
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


	public Pane getPane() {
		return pane;
	}


	public void setPane(Pane pane) {
		this.pane = pane;	
	}

}
