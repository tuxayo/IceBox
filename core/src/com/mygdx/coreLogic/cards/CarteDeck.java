package com.mygdx.coreLogic.cards;

public class CarteDeck extends Carte {

	/**
	 * Un constructeur qui prend les deux paramètres.
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
