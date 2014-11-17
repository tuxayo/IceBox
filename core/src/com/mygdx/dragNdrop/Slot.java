package com.mygdx.dragNdrop;

import com.badlogic.gdx.utils.Array;
import com.mygdx.coreLogic.cards.Carte;

/**
 * Represente toute les information sur une carte et son état
 */
public class Slot {

	private Carte item;
	private String operator;
	private PaneSide side;
	private boolean isSuspended;
	private boolean isInverseBlocked;
	private Array<SlotListener> slotListeners = new Array<SlotListener>();

	/**
	 * Cree un Slot contenant une carte
	 * @param item la carte que va contenir le slot
	 * @param side dans quel panneau est le slot
	 * @param isSuspended le slot est-il suspendu ?
	 */
	public Slot(Carte item, PaneSide side, boolean isSuspended) {
		this.item = item;
		this.side = side;
		this.operator = " + ";
		this.isSuspended = isInverseBlocked = isSuspended;
	}
	
	/**
	 * Constructeur par recopie d'un slot
	 * @param slot
	 */
	public Slot(Slot slot) {
		this.item = slot.item;
		this.side = slot.side;
		this.operator = slot.operator;
		this.isSuspended = slot.isSuspended;
		this.isInverseBlocked = slot.isInverseBlocked;
		
	}

	/**
	 * Predicat pour tester si le slot est vide
	 * @return Renvoir True si le slot est vide
	 */
	public boolean isEmpty() {
		return item == null;
	}

	/**
	 * Predicat pour tester si la carte est la carte Box
	 * @return True si la carte est la carte Box, False si non.
	 */
	public boolean isCarteBox() {
		return item != null && item.isCarteBox();
	}
	
	/**
	 * Ajoute un listener qui sera notifié a chaque changement du slot
	 * @param slotListener
	 */
	public void addListener(SlotListener slotListener) {
		slotListeners.add(slotListener);
	}

	/**
	 * Supprime un listener pour ce slot 
	 * @param slotListener le slot a supprimé
	 */
	public void removeListener(SlotListener slotListener) {
		slotListeners.removeValue(slotListener, true);
	}

	/**
	 * Supprimme tout les listener pour ce slot
	 */
	public void removeAllListener() {
		slotListeners.removeAll(slotListeners, true);
	}

	/**
	 * Prédicat pour savoir si deux slot sont identique
	 * @param other l'autre slot a compraré
	 * @return True si les deux slots contiennent la meme carte, False sinon
	 */
	public boolean matches(Slot other) {
		return this.item == other.item;
	}
	
	/**
	 * Prédicat pour savoir si deux slot sont oposée
	 * @param other l'autre slot a compraré
	 * @return True si les deux slots contiennent des carte oposée, False sinon
	 */
	public boolean isOpposite(Slot other) {
		return side.equals(other.side) && item.isOpposite(other.item);
	}

	/**
	 * Ajoute une carte au slot, et notifie tout ses listener de son changement
	 * @param item la carte a ajouté dans ce slot
	 * @return True si l'ajout a été effectué False sinon
	 */
	public boolean add(Carte item) {
		if (this.item == null) {
 			this.item = item;

			notifyListeners();
			return true;
		}

		return false;
	}

	/**
	 * Supprime la carte contenu dans ce slot, et notifie tout ses listeners
	 * @return Toujours True
	 */
	public boolean take() {
		item = null;
		notifyListeners();
		return true;
	}
	
	/**
	 * Notifie tout les Listener de ce slot qu'il a été modifié et donc que 
	 * l'affichage n'est plus cohérent avec le nouvel état de ce slot
	 */
	private void notifyListeners() {		
		for (SlotListener slotListener : slotListeners) {
			slotListener.hasChanged(this);
		}		
	}

	/**
	 * 
	 * @return La carte contenue dans ce slot
	 */
	public Carte getCard() {
		return item;
	}

	/**
	 * 
	 * @return le nom de l'image associé à la carte opposée.
	 */
	public String getoppositeTexture() {
		return item.getOppositeTexture();	
	}

	/**
	 * 
	 * @return Dans quel panneau est contenue ce slot {@link PaneSide}
	 */
	public PaneSide getSide() {
		return side;
	}

	/**
	 * Modifie l'apartenance de ce slot a un panneau
	 * @param side le nouveau panneau du slot
	 */
	public void setSide(PaneSide side) {
		this.side = side;
	}

	@Override
	public String toString() {
		if (item == null || item.isCarteBox()) 
			return new String();

		return item.toString() + operator;		
	}

	/**
	 * Remplace la carte carte contenue dans le slot par son inverse, ssi
	 * inversement de ce slot n'est pas suspendu
	 */
	public void inverse() {
		if (isInverseBlocked) return;
		Carte inverse = item.getOppositeCarte();
		take();
		add(inverse);
	}

	/**
	 * Échange deux slots
	 * @param targetSlot le slot destination
	 * @param payloadSlot le slot source
	 */
	public void swapWith(Slot targetSlot, Slot payloadSlot) {
		Carte targetType = targetSlot.getCard();
		targetSlot.take();
		targetSlot.add(payloadSlot.getCard());
		add(targetType);
	}
	
	/**
	 * Remplace la carte contenue dans ce slot par la carte Zero
	 */
	public void setToZero() {
		take();
		add(Carte.getCarteZero());
	}

	/**
	 * Prédicat pour tester si la carte contenue dans ce slot est la carte Zero
	 * @return True si la carte contenue cans ce slot est la carte Zero, 
	 * 			False sinon
	 */
	public boolean isZero() {
		return item != null && item.getTextureRegion().equals("carte0");
	}

	/**
	 * Suspends toute action sur ce slot
	 */
	public void suspends() {
		isInverseBlocked = true;
		isSuspended = true;
	}
	
	/**
	 * Prédicat pour tester si le slot est suspendu
	 * @return True si le slot est suspendu
	 */
	public boolean isSuspended() {
		return isSuspended;
	}

	/**
	 * Réactive entierement le slot
	 */
	public void resume() {
		isInverseBlocked = false;
		isSuspended = false;		
	}

	/**
	 * Suspend la fonction pour remplacer la carte du slot 
	 * par son opoosée
	 */
	public void suspendInversee() {
		isInverseBlocked = true;
	}

	/**
	 * Réactive la fonction pour remplacer la carte du slot 
	 * par son opoosée
	 */
	public void resumeInversee() {
		isInverseBlocked = false;
	}

	/**
	 * Prédicat pour tester si la fonction pour remplacer la carte du slot 
	 * par son opoosée est suspendu 
	 * @return True si la fonction pour remplacer la carte du slot 
	 * par son opoosée est suspendu False ninon
	 */
	public boolean isInverseBlocked() {
		return isInverseBlocked;
	}


}
