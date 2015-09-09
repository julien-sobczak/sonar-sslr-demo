package sslr.typed.json.parser.tree;

import java.util.List;

import sslr.typed.json.parser.SyntaxToken;

public interface ObjectTree extends TypeTree {

	SyntaxToken openCurlyBrace();
	
	List<PairTree> pairs();
	
	SyntaxToken closeCurlyBrace();
	
}
