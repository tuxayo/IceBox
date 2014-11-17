package com.mygdx.parser;


/**
 *	Classe abstraite qui represente un noeud qui modélise une opération binaire 
 *  entre deux arbres d'expressions 
 */
public abstract class BinaryOperator extends Operator {

	private ExpressionTree left,right;
	
	/**
	 * Construit les deux opérandes d'une opération binaire a partir des 
	 * sous-arbres left et right
	 * @param left
	 * @param right
	 */
	public BinaryOperator (ExpressionTree left, ExpressionTree right)
	{
		this.left  = left;
		this.right = right;
	}

	/**
	 * @return l'opérande de gauche, donc le sous arbre gauche
	 */
	public ExpressionTree getLeft() {
		return left;
	}

	/**
	 * @return l'opérande de droite, donc le sous arbre droit
	 */
	public ExpressionTree getRight() {
		return right;
	}

	@Override
	public String postfix()	{
		return left.postfix() + right.postfix() + getOpSymbol() + " ";
	} 
	
}

