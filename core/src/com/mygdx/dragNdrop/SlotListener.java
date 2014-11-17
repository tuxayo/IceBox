package com.mygdx.dragNdrop;

/**
 * Cette interface rend possible le déclanchement d'actions et traitements,
 * dès qu'un slot a changé
 *
 */
public interface SlotListener {

	/**
	 * Sera appelé a chaque fois qu'un slot a changé
	 * 
	 * @param slot
	 *            le slot qui a été modifié.
	 */
	void hasChanged(Slot slot);

}
