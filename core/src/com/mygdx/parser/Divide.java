package com.mygdx.parser;

/**
 * Represente un noeud qui modélise la division de deux arbres d'expression 
 */
public class Divide extends BinaryOperator {

	public static final int neutralElem = 1;
	
	/**
	 * Construit l'arbre qui represente la division entre left et right
	 * @param left
	 * @param right
	 */
	public Divide (ExpressionTree left, ExpressionTree right) {
		super(left,right);
	}

	public int evaluate() {
		return getLeft().evaluate() / getRight().evaluate();
	}

	public String getOpSymbol() {
		return "/";
	}

	@Override
	public int getNeutralElem() {
		return neutralElem;
	}

	@Override
	public ExpressionTree simplify() {
		ExpressionTree leftValueSimplified = getLeft().simplify();
		ExpressionTree rightValueSimplified = getRight().simplify();
		
		if (rightValueSimplified.evaluate() == getNeutralElem()) {
			return leftValueSimplified;
		} else if (leftValueSimplified.evaluate() == rightValueSimplified.evaluate() ) {
			return new Constant(getNeutralElem());
		} else {
			return new Divide(leftValueSimplified, rightValueSimplified);
		}
	}

}