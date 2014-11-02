package com.mygdx.coreLogic.cards;

public class CarteBox extends Carte {

	
	/**
	 * Le constructeur de la classe, on lui donne le nom de l'image et il le stocke dans img
	 * @param img
	 */
	public CarteBox(String img) {
		super(img, -1);
	}
	
	/**
	 * Permet d'afficher une carte.
	 */
	public void afficheCarte(){
		System.out.println("image : " + this.textureRegion);
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
		return new CarteDeck(oppositeTexture, valeur); 
	}

	public boolean isOpposite(Carte other) {
		return getOppositeTexture().equalsIgnoreCase(other.getTextureRegion());
	}


}
