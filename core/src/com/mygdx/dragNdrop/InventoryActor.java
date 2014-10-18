package com.mygdx.dragNdrop;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;


public class InventoryActor extends Table {

	public InventoryActor(Inventory inventory, DragAndDrop dragAndDrop, Skin skin) {
		
		for (Slot slot : inventory.getSlots()) {
			SlotActor slotActor = new SlotActor(skin, slot);
			dragAndDrop.addSource(new SlotSource(slotActor));
			dragAndDrop.addTarget(new SlotTarget(slotActor));
			add(slotActor);
		}

		pack();

		// cacher par defaut au niveau 1 par exemple
//		setVisible(false);
		setVisible(true);

	}

}
