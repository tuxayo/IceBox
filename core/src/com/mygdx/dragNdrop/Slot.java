package com.mygdx.dragNdrop;

import com.badlogic.gdx.utils.Array;
import com.mygdx.coreLogic.cards.Carte;
import com.mygdx.coreLogic.paramGame.paramGame;


public class Slot {

	private Carte item;
	private String operator;
	private PaneSide side;
	private boolean isSuspended;
	private boolean isInverseBlocked;
	private Array<SlotListener> slotListeners = new Array<SlotListener>();

	
	public Slot(Carte item, PaneSide side, boolean isSuspended) {
		this.item = item;
		this.side = side;
		this.operator = " + ";
		this.isSuspended = isInverseBlocked = isSuspended;
	}
	

	public Slot(Slot slot) {
		this.item = slot.item;
		this.side = slot.side;
		this.operator = slot.operator;
		this.isSuspended = slot.isSuspended;
		this.isInverseBlocked = slot.isInverseBlocked;
		
	}


	public boolean isEmpty() {
		return item == null;
	}

	/**
	 * Predicat pour tester si la carte est la carte Box
	 * @return True si la carte est la carte Box, False si non.
	 */
	public boolean isCarteBox() {
		return item.isCarteBox();
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
		
		System.out.println("fin du jeu ? " + paramGame.getController().checkGameState());
		System.out.println("simplifiés ? " + paramGame.getController().isSimpified());
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
		if (isInverseBlocked) return;
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

	public void suspends() {
		isInverseBlocked = true;
		isSuspended = true;
	}
	
	public boolean isSuspended() {
		return isSuspended;
	}

	public void resume() {
		isInverseBlocked = false;
		isSuspended = false;		
	}


	public void suspendInversee() {
		isInverseBlocked = true;
	}

	public void resumeInversee() {
		isInverseBlocked = false;
	}

	public boolean isInverseBlocked() {
		return isInverseBlocked;
	}


}
