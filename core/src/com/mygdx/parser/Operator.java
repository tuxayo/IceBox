package com.mygdx.parser;

/**
 * Classe abtraite qui represente un arbre qui modelise un opérateur 
 */
public abstract class Operator extends ExpressionTree {
	
	/**
	 * Renvoi la précendence d'un opérateur 
	 * @param op 
	 * @return La précendence d'un opérateur
	 */
	public static int getPrecedence (String op)
	{
		if (op.equals("+"))
			return 1;
		else if (op.equals("-"))
			return 1;
		else if (op.equals("*"))
			return 2;
		else if (op.equals("/"))
			return 2;
		else if (op.equals("~"))
			return 3;
		else
			throw new IllegalArgumentException("getPrecedence - op illegal = " + op);
	}

	/**
	 * Renvoie la chaine de caractère associée a l'opérateur
	 * @return La chaine de caractère associée a l'opérateur
	 */
	public abstract String getOpSymbol();
	
	/**
	 * Renvoie l'élément neutre de l'opérateur dans N
	 * @return L'élément neutre de l'opérateur dans N
	 */
	public abstract int getNeutralElem();
}
