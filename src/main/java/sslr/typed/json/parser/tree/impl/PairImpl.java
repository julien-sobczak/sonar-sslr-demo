package sslr.typed.json.parser.tree.impl;

import java.util.Iterator;

import sslr.typed.json.parser.InternalSyntaxToken;
import sslr.typed.json.parser.JsonLexer;
import sslr.typed.json.parser.SyntaxToken;
import sslr.typed.json.parser.Tree;
import sslr.typed.json.parser.TreeVisitor;
import sslr.typed.json.parser.tree.PairTree;
import sslr.typed.json.parser.tree.ValueTree;

import com.google.common.collect.ImmutableList;

public class PairImpl extends sslr.typed.json.parser.JsonTree implements PairTree {

	private SyntaxToken name;
	private ValueTree value;
	
	public PairImpl(InternalSyntaxToken name, ValueTree value) {
		super(JsonLexer.PAIR);
		this.name = name;
		this.value = value;
	}

	@Override
	public Kind getKind() {
		return Kind.PAIR;
	}

	@Override
	public void accept(TreeVisitor visitor) {
		visitor.visitPair(this);
	}

	@Override
	public SyntaxToken name() {
		return name;
	}

	@Override
	public ValueTree value() {
		return value;
	}
	
	@Override
	public Iterator<Tree> childrenIterator() {
		ImmutableList.Builder<Tree> iteratorBuilder = ImmutableList.builder();
		iteratorBuilder.add(name);
		iteratorBuilder.add(value);
		return iteratorBuilder.build().iterator();
	}

}
