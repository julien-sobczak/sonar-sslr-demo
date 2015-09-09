package sslr.typed.json.parser.tree;

import javax.annotation.Nullable;

import sslr.typed.json.parser.Tree;

public interface JsonTree extends Tree {

	 @Nullable
	 TypeTree type();
	
}
