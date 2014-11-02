package com.mygdx.parser;

public class Constant extends ExpressionTree {
	private int value;

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

