package com.mygdx.dragNdrop;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class PaneActor extends Table {

	public PaneActor(Pane leftpane, DragAndDrop dragAndDrop, Skin skin) {
		
		int i = 0;
		for (Slot slot : leftpane.getSlots()) {
			SlotActor slotActor = new SlotActorPane(skin, slot);
			dragAndDrop.addSource(new SlotSourcePane(slotActor));
			dragAndDrop.addTarget(new SlotTargetPane(slotActor));
			add(slotActor);

			i++;
			// toute les 5 cellule, on creer une nouvelle ligne
			if (i % 4 == 0) {
				row();
			}
		}
		
        defaults().space(8);
        row().fill().expandX();
		
		setVisible(true);
	}
	
}
