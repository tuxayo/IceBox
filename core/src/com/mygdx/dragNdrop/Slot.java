package com.mygdx.dragNdrop;

import com.badlogic.gdx.utils.Array;
import com.mygdx.coreLogic.cards.Carte;


public class Slot {

	private Carte item;
	private String operator;
	private PaneSide side;
	private Array<SlotListener> slotListeners = new Array<SlotListener>();

	public Slot(Carte item, PaneSide side) {
		this.item = item;
		this.side = side;
		this.operator = " + ";
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


	public boolean add(Carte item) {
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

	public Carte getCard() {
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
		if (item == null || item.isCarteBox()) 
			return new String();

		return item.toString() + operator;		
	}

	public void inverse() {
		Carte inverse = item.getOppositeCarte();
		take();
		add(inverse);
	}

	public void swapWith(Slot targetSlot, Slot payloadSlot) {
		Carte targetType = targetSlot.getCard();
		targetSlot.take();
		targetSlot.add(payloadSlot.getCard());
		add(targetType);
	}
	
	
	public void setToZero() {
		take();
		add(Carte.getCarteZero());
	}

	public boolean isZero() {
		return item != null && item.getTextureRegion().equals("carte0");
	}
}
