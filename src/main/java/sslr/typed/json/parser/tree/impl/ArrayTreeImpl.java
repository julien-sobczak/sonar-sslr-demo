package sslr.typed.json.parser.tree.impl;

import java.util.Iterator;
import java.util.List;

import sslr.typed.json.parser.InternalSyntaxToken;
import sslr.typed.json.parser.JsonLexer;
import sslr.typed.json.parser.SyntaxToken;
import sslr.typed.json.parser.Tree;
import sslr.typed.json.parser.TreeVisitor;
import sslr.typed.json.parser.tree.ArrayTree;
import sslr.typed.json.parser.tree.ValueTree;

import com.google.common.collect.ImmutableList;

public class ArrayTreeImpl extends sslr.typed.json.parser.JsonTree implements ArrayTree {

	private SyntaxToken openBracketToken;
	private List<ValueTree> elements;
	private SyntaxToken closeBracketToken;

	public ArrayTreeImpl(InternalSyntaxToken openBracketToken,
			List<ValueTree> elements, InternalSyntaxToken closeBracketToken) {
		super(JsonLexer.ARRAY);
		this.openBracketToken = openBracketToken;
		this.elements = elements;
		this.closeBracketToken = closeBracketToken;
	}

	@Override
	public Kind getKind() {
		return Kind.ARRAY;
	}

	@Override
	public void accept(TreeVisitor visitor) {
		visitor.visitArray(this);
	}

	@Override
	public SyntaxToken openBracketToken() {
		return openBracketToken;
	}

	@Override
	public List<ValueTree> elements() {
		return elements;
	}

	@Override
	public SyntaxToken closeBracketToken() {
		return closeBracketToken;
	}

	@Override
	public Iterator<Tree> childrenIterator() {
		// Utilise Google Guava pour faciliter la création des itérators. 
		// Cette dépendance est tirée de SonarQube qui l'utilise massivement.
		
		ImmutableList.Builder<Tree> iteratorBuilder = ImmutableList.builder();
		iteratorBuilder.add(openBracketToken);
		iteratorBuilder.addAll(elements);
		iteratorBuilder.add(closeBracketToken);
		return iteratorBuilder.build().iterator();
	}

}
