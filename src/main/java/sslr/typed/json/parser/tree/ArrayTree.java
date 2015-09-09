package sslr.typed.json.parser.tree;

import java.util.List;

import sslr.typed.json.parser.SyntaxToken;

/**
 * Noeud fortement typé correspondant à un tableau JSON.
 */
public interface ArrayTree extends TypeTree {

	/* 
	 * On retrouve ici une méthode par composant d'un tableau JSON : les crochets et les éléments.
	 * 
	 * C'est grâce à toutes ces méthodes qu'on navigue facilement dans l'AST lors de 
	 * l'implémentation des règles.
	 */
	
	SyntaxToken openBracketToken();

	List<ValueTree> elements();
	
	SyntaxToken closeBracketToken();
	
}
