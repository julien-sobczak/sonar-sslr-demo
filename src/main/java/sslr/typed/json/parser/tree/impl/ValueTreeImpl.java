package sslr.typed.json.parser.tree.impl;

import java.util.Iterator;

import sslr.typed.json.parser.JsonLexer;
import sslr.typed.json.parser.Tree;
import sslr.typed.json.parser.TreeVisitor;
import sslr.typed.json.parser.tree.ValueTree;

import com.google.common.collect.ImmutableList;

public class ValueTreeImpl extends sslr.typed.json.parser.JsonTree implements ValueTree {

	private Tree value;
	
	public ValueTreeImpl(Tree value) {
		super(JsonLexer.VALUE);
		this.value = value;
	}

	@Override
	public Kind getKind() {
		return Kind.VALUE;
	}

	@Override
	public void accept(TreeVisitor visitor) {
		visitor.visitValue(this);
	}

	@Override
	public Tree value() {
		return value;
	}
	
	@Override
	public Iterator<Tree> childrenIterator() {
		ImmutableList.Builder<Tree> iteratorBuilder = ImmutableList.builder();
		iteratorBuilder.add(value);
		return iteratorBuilder.build().iterator();
	}

}
