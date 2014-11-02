package com.mygdx.parser;

import java.util.Stack;
import java.util.StringTokenizer;


public class ExpressionParser {

	private String expression;
	StringTokenizer tokens;
	
	public ExpressionParser(String expression) {
		this.expression = expression.concat(";");
		tokens = new StringTokenizer(this.expression, "()+-*/~; ",true);
	}


	public ExpressionTree parse() throws Exception
	{
		Stack<Object> ops  = new Stack<Object>();
		Stack<Object> exps = new Stack<Object>();

		ExpressionTree l,r;

		for (;tokens.hasMoreTokens();) {
			String token = tokens.nextToken();;

			//on ignore les espaces
			if (token.charAt(0) == ' ')
				;

			//un entier
			else if ('0' <= token.charAt(0) && token.charAt(0) <= '9')
				exps.push(new Constant(Integer.parseInt(token)));

			// (
			else if ( token.equals("(") )
				ops.push("(");

			// ; ie:fin de l'expression 
			else if ( token.equals(";") )
				while (!ops.isEmpty()) {
					String op = (String)ops.pop();
					if ( op.equals("(") )
						throw new IllegalArgumentException("parse: Bad Expression, too many (");
					r = (ExpressionTree)exps.pop();
					l = op.equals("~") ? null : (ExpressionTree)exps.pop();
					exps.push( ExpressionTree.buildExpTree(op,l,r) );
				} 

			// )
			else if ( token.equals(")") )
				while (true) {
					if (ops.isEmpty())
						throw new IllegalArgumentException("parse: Bad Expression, too many )");
					String op = (String)ops.pop();
					if ( op.equals("(") )
						break;
					r = (ExpressionTree)exps.pop();
					l = op.equals("~") ? null : (ExpressionTree)exps.pop();
					exps.push( ExpressionTree.buildExpTree(op,l,r));
				} 

			// si non operateur
			else {     
				// On depile tous les opérateur dans la pile des opérateurs de precedence plus petite
				// puis on empile le nouvel operateur
				while(! ops.isEmpty()) {
					String op = (String)ops.peek();
					if ( op.equals("(") || Operator.getPrecedence(op) < Operator.getPrecedence(token) )
						break;
					ops.pop();
					r = (ExpressionTree)exps.pop();
					l = op.equals("~") ? null : (ExpressionTree)exps.pop();
					exps.push( ExpressionTree.buildExpTree(op,l,r));
				}

				ops.push(token);
			}
		}

		// On vérifie l'état de la pile
		if (ops.isEmpty() && exps.size() == 1)
			return (ExpressionTree)exps.pop();
		else
			throw new IllegalArgumentException("parse: Bad Expression, stacks not reduced at end");
	}

}
