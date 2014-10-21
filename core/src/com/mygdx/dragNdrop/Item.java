package com.mygdx.dragNdrop;


public enum Item {

	CARTE_0("carte0"), 
	CARTE_1("carte1"),
	CARTE_1_NEG("carte1-neg"),
	CARTE_2("carte2"),
	CARTE_2_NEG("carte2-neg"),	
	CARTE_3("carte3"),
	CARTE_3_NEG("carte3-neg"),
	CARTE_4("carte4"),
	CARTE_4_NEG("carte4-neg"),
	CARTE_5("carte5"),
	CARTE_5_NEG("carte5-neg"),
	CARTE_6("carte6"),
	CARTE_6_NEG("carte6-neg"),
	CARTE_7("carte7"),
	CARTE_7_NEG("carte7-neg"),
	CARTE_8("carte8"),
	CARTE_8_NEG("carte8-neg"),
	CARTE_9("carte9"),
	CARTE_9_NEG("carte9-neg");

	
	private String textureRegion;

	private Item(String textureRegion) {
		this.textureRegion = textureRegion;
	}

	public String getTextureRegion() {
		return textureRegion;
	}

	public String getOppositeTexture() {
		if (textureRegion.contains("-neg"))
			return textureRegion.substring(0, 6);
		else
			return textureRegion.concat("-neg");
	}
	
	public void inverse() {
		this.textureRegion = getOppositeTexture();
	}
	
	public void setTextureRegion(String textureRegion) {
		this.textureRegion = textureRegion;
	}
}
