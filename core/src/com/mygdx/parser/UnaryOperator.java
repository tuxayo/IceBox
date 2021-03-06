package com.mygdx.parser;

/**
 *	Classe abstraite qui represente un noeud qui modélise une opération unaire 
 *  sur une expressions 
 */
public abstract class UnaryOperator extends Operator {
	
	private ExpressionTree right;
	
	/**
	 * Construit les l'opérande d'une opération unanire a partir du
	 * sous-arbre  right
	 * @param right
	 */
	public UnaryOperator (ExpressionTree right) {
		this.right = right;
	}

	/**
	 * 
	 * @return l'arbre sur lequel est effectué l'opération unaire
	 */
	public ExpressionTree getRight() {
		return right;
	}

	public String postfix() {
		return right.postfix() + getOpSymbol() + " ";
	} 

}


