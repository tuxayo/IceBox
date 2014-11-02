package com.mygdx.parser;

public class Multiply extends BinaryOperator {

	public static final int neutralElem = 1;
	
	public Multiply (ExpressionTree left, ExpressionTree right)
	{
		super(left,right);
	}

	public int evaluate() { 
		return getLeft().evaluate() * getRight().evaluate();
	}

	public String getOpSymbol() {
		return "*";
	}
	
	@Override
	public int getNeutralElem() {
		return neutralElem;
	}

	@Override
	public ExpressionTree simplify() {
		ExpressionTree leftValueSimplified = getLeft().simplify();
		ExpressionTree rightValueSimplified = getRight().simplify();
		
		if (leftValueSimplified.evaluate() == getNeutralElem())
			return rightValueSimplified;
		
		else if (rightValueSimplified.evaluate() == getNeutralElem()) {
			return leftValueSimplified;
			
		} else if (leftValueSimplified.evaluate() == getNeutralElem() && 
				rightValueSimplified.evaluate() == getNeutralElem()) {
			return new Constant(getNeutralElem());
			
		} else {
			return new Multiply(getLeft().simplify(), getRight().simplify());
		}
	}
}
