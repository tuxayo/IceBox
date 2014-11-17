package com.mygdx.parser;

/**
 * Modélise une expression arithmetique a l'aide d'un arbre binaire 
 */
public abstract class ExpressionTree {
	
	/**
	 * Evalue l'arbre sur le quelle elle est appelé
	 * @return la valeur de l'expression que represente l'arbre
	 */
	public abstract int evaluate();
	
	
	/**
	 * Parcours l'arbre de maniere postfixé afin d'obtenir une formule 
	 * en Notation Polonaise Inverse pour s'affranchir du besoin des parenthèses
	 * et obtenir une formule simplifié
	 * @return La formule représenté par l'arbre en NPI
	 */
	public abstract String postfix();
	
	
	/**
	 * Effectue les simplification arithmetique basique suivante (ou x est un nombre) <p/>
	 * 	0 + x = x + 0 = x,  <p/>
	 * 	1 * x = x * 1 = x,  <p/>
	 *  x / 1 = x,  <p/>
	 *  x / x = 1.  <p/>
	 * @return Un arbre qui représente la formule simplifiée
	 */
	public abstract ExpressionTree simplify();
	
	
	/**
	 * Fabrique  pour la construction d'un arbre representant 
	 * l'expression : <b> left op right </b>  
	 * @param op
	 * @param left
	 * @param right
	 * @return Un arbre representant l'expression décrite par <b> left op right </b>  
	 */
	public static ExpressionTree buildExpTree(String op, ExpressionTree left, ExpressionTree right)
	{
	  if (op.equals("+"))
	    return new Add(left,right);
	  else if (op.equals("-"))
	    return new Subtract(left,right);
	  else if (op.equals("*"))
	    return new Multiply(left,right);
	  else if (op.equals("/"))
	    return new Divide(left,right);
	  else if (op.equals("~"))
	    return new Negate(right);
	  else
	    throw new IllegalArgumentException("ExpressionTree.makeET: illegal operators = " + op);
	}

	
}
