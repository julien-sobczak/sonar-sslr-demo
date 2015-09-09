package sslr.typed.json.parser;

import sslr.typed.json.parser.tree.ArrayTree;
import sslr.typed.json.parser.tree.JsonTree;
import sslr.typed.json.parser.tree.ObjectTree;
import sslr.typed.json.parser.tree.PairTree;
import sslr.typed.json.parser.tree.TypeTree;
import sslr.typed.json.parser.tree.ValueTree;


public interface TreeVisitor {

	void visitJson(JsonTree tree);

	void visitArray(ArrayTree tree);

	void visitObject(ObjectTree tree);

	void visitPair(PairTree tree);

	void visitType(TypeTree tree);

	void visitValue(ValueTree tree);

}
