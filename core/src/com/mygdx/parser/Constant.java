package com.mygdx.parser;

/**
 *	Represente une feuille de l'arbre d'une expression et donc une constante 
 *  dans l'expression réprésenté
 */
public class Constant extends ExpressionTree {
	private int value;

	/**
	 * Construit une constante de valeur value
	 * @param value
	 */
	public Constant (int value)	{
		this.value = value;
	}

	public int evaluate() { 
		return value;
	}

	public String postfix()	{
		return ""+value+" ";
	}

	@Override
	public ExpressionTree simplify() {
		return new Constant(value);
	}

}

