package sslr.typed.json.parser;

import java.util.List;

public interface ListTree<T> extends Tree, List<T> {

	List<SyntaxToken> separators();
}
