package com.mygdx.coreLogic.cards;

/**
 * Classe qui represente les carte du Deck
 */
public class CarteDeck extends Carte {

	/**
	 * Un constructeur qui prend comme paramètres l'image et la valeur de cette 
	 * carte 
	 * @param img
	 * @param val
	 */
	public CarteDeck(String img,int val) {
		super(img,val);
	}


	public void afficheCarte(){
		System.out.println("image : " + this.textureRegion + " valeur : " + this.valeur);
	}


	public String getOppositeTexture() {
		if (textureRegion.contains("-neg")) {
			return textureRegion.substring(0, 6);
		} else {
			return textureRegion.concat("-neg");
		}
	}

	
	public Carte getOppositeCarte () {
		String oppositeTexture = getOppositeTexture();
		return new CarteDeck(oppositeTexture, -valeur); 
	}

	
	public boolean isOpposite(Carte other) {
		return getOppositeTexture().equalsIgnoreCase(other.getTextureRegion());
	}

}
