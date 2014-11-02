package com.mygdx.parser;

public abstract class UnaryOperator extends Operator {
	
	private ExpressionTree right;
	
	public UnaryOperator (ExpressionTree right) {
		this.right = right;
	}

	public ExpressionTree getRight() {
		return right;
	}

	public String postfix() {
		return right.postfix() + getOpSymbol() + " ";
	} 

}


