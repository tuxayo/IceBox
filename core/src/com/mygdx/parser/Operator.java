package com.mygdx.parser;

public abstract class Operator extends ExpressionTree {
	
	/**
	 * Renvoi la précendence de l'opérateur {@link op}
	 * @param op
	 * @return La précendence de l'opérateur {@link op}
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
