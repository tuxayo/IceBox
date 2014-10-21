package com.mygdx.dragNdrop;

import com.badlogic.gdx.utils.Array;


public class Slot {

	private Item item;
	private long val;
	private Array<SlotListener> slotListeners = new Array<SlotListener>();

	public Slot(Item item) {
		this.item = item;
		this.val = (item != null) ? item.name().substring(0, 7).hashCode() : 0;
	}

	public boolean isEmpty() {
		return item == null;
	}

	public void addListener(SlotListener slotListener) {
		slotListeners.add(slotListener);
	}

	public void removeListener(SlotListener slotListener) {
		slotListeners.removeValue(slotListener, true);
	}

	/**
	 * Returns {@code true} in case this slot has the same item type and at
	 * least the same amount of items as the given other slot.
	 * 
	 * @param other
	 *            The other slot to be checked.
	 * @return {@code True} in case this slot has the same item type and at
	 *         least the same amount of items as the given other slot.
	 *         {@code False} otherwise.
	 */
	public boolean matches(Slot other) {
		return this.item == other.item;
	}
	
	public boolean isOpposite(Slot other) {
		return this.val == other.val;
	}

	public boolean add(Item item) {
		if (this.item == item || this.item == null) {
			this.item = item;
			this.val = item.name().substring(0, 7).hashCode();
			notifyListeners();
			return true;
		} 

		return false;
	}


	private void notifyListeners() {
		for (SlotListener slotListener : slotListeners) {
			slotListener.hasChanged(this);
		}
	}

	public Item getItem() {
		return item;
	}
	
	public String getoppositeName() {
		return item.getOppositeTexture();	
	}
	
	public void inverse() {
		System.out.println("inversement !!");
		item.inverse();
		val = (item != null) ? item.name().substring(0, 7).hashCode() : 0;
		notifyListeners();
	}
	
	@Override
	public String toString() {
		return "Slot[" + item + ":" + val + "]";
	}

	public long getVal() {
		return val;
	}
}
