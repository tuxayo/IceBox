package com.mygdx.parser;

/**
 *	Represente un noeud qui modélise la negation une expression 
 */
public class Negate extends UnaryOperator {
	
	public static final int neutralElem = 0;
	
	/**
	 * Construit l'arbre qui represente la négation de right
	 * @param right
	 */
	public Negate (ExpressionTree right) {
		super(right);
	}

	public int evaluate() {
		return - getRight().evaluate();
	}

	public String getOpSymbol() { 
		return "~";
	}

	@Override
	public int getNeutralElem() {
		return neutralElem;
	}
	
	@Override
	public ExpressionTree simplify() {
		ExpressionTree rightValueSimplified = getRight().simplify();
		
		if (rightValueSimplified.evaluate() == getNeutralElem())
			return new Constant(getNeutralElem());
		else
			return new Negate(rightValueSimplified);

	}

}

