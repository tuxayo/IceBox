package com.mygdx.dragNdrop;

import com.badlogic.gdx.utils.Array;


public class Slot {

	private Item item;
	private PaneSide side;
	private Array<SlotListener> slotListeners = new Array<SlotListener>();

	public Slot(Item item, PaneSide side) {
		this.item = item;
		this.side = side;
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

	public void removeAllListener() {
		slotListeners.removeAll(slotListeners, true);
	}


	public boolean matches(Slot other) {
		return this.item == other.item;
	}

	public boolean isOpposite(Slot other) {
		return side.equals(other.side) && item.isOpposite(other.item);
	}


	public boolean add(Item item) {
		if (this.item == null) {
			this.item = item;

			notifyListeners();
			return true;
		}

		return false;
	}

	public boolean take() {
		item = null;
		notifyListeners();
		return true;
	}
	
	private void notifyListeners() {
		for (SlotListener slotListener : slotListeners) {
			slotListener.hasChanged(this);
		}
	}

	public Item getItem() {
		return item;
	}

	public String getoppositeTexture() {
		return item.getOppositeTexture();	
	}

	public PaneSide getSide() {
		return side;
	}

	public void setSide(PaneSide side) {
		this.side = side;
	}


	@Override
	public String toString() {
		return "Slot[" + item + ":" + side + "]";
	}

	public void inverse() {
		Item inverse = item.getOppositeItem();
		take();
		add(inverse);
	}
}
