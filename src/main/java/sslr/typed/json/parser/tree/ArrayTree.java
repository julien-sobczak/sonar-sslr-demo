package sslr.typed.json.parser.tree;

import java.util.List;

import sslr.typed.json.parser.SyntaxToken;

public interface ArrayTree extends TypeTree {

	SyntaxToken openBracketToken();

	List<ValueTree> elements();
	
	SyntaxToken closeBracketToken();
	
}
