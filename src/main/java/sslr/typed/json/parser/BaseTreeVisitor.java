package sslr.typed.json.parser;

import java.util.List;

import javax.annotation.Nullable;

import sslr.typed.json.parser.Tree.Kind;
import sslr.typed.json.parser.tree.ArrayTree;
import sslr.typed.json.parser.tree.JsonTree;
import sslr.typed.json.parser.tree.ObjectTree;
import sslr.typed.json.parser.tree.PairTree;
import sslr.typed.json.parser.tree.TypeTree;
import sslr.typed.json.parser.tree.ValueTree;

/**
 * Default implementation of {@link TreeVisitor}.
 */
public class BaseTreeVisitor implements TreeVisitor {

	/*
	 * Point d'entrée lors de l'analyse d'une règle.
	 * Est appeler avec la racine puis successivement avec les différents noeuds (récursif).
	 */
	public void scan(@Nullable Tree tree) {
		if (tree != null) {
			tree.accept(this);
		}
	}

	protected void scan(List<? extends Tree> trees) {
		for (Tree tree : trees) {
			scan(tree);
		}
	}
	
	/* Liste des méthodes à surcharger dans les sous-classes au besoin */

	@Override
	public void visitJson(JsonTree tree) {
		scan(tree.type());
	}

	@Override
	public void visitArray(ArrayTree tree) {
		scan(tree.elements());
	}

	@Override
	public void visitObject(ObjectTree tree) {
		scan(tree.pairs());
	}

	@Override
	public void visitPair(PairTree tree) {
		scan(tree.name());
		scan(tree.value());
	}

	@Override
	public void visitType(TypeTree tree) {
		if (tree.is(Kind.ARRAY)) {
			visitArray((ArrayTree) tree);
		} else  { // Object
			visitObject((ObjectTree) tree);
		}
	}

	@Override
	public void visitValue(ValueTree tree) {
		scan(tree.value());
	}

}
