package com.mygdx.dragNdrop;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class PaneActor extends Table {

	private Pane pane;
	
	public PaneActor(Pane pane, DragAndDrop dragAndDrop, Skin skin) {
		
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

	
	public String stringify() {
		
		StringBuilder expr = new StringBuilder() ;
		
		for (Slot slot : pane.getSlots()) {
			expr.append(slot.toString());
		}
		
		return expr.toString().substring(0, expr.length()-3);
	}

	
}
