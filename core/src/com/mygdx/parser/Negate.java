package com.mygdx.parser;

public class Negate extends UnaryOperator {
	
	public static final int neutralElem = 0;
	
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

