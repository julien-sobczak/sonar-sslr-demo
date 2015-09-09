package sslr.typed.json.parser.tree;

import sslr.typed.json.parser.SyntaxToken;
import sslr.typed.json.parser.Tree;

public interface PairTree extends Tree {

	SyntaxToken name();
	
	ValueTree value();
	
}
