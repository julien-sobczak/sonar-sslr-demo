package sslr.typed.json.parser.tree.impl;

import java.util.Iterator;
import java.util.List;

import sslr.typed.json.parser.InternalSyntaxToken;
import sslr.typed.json.parser.JsonLexer;
import sslr.typed.json.parser.SyntaxToken;
import sslr.typed.json.parser.Tree;
import sslr.typed.json.parser.TreeVisitor;
import sslr.typed.json.parser.tree.ObjectTree;
import sslr.typed.json.parser.tree.PairTree;

import com.google.common.collect.ImmutableList;

public class ObjectTreeImpl extends sslr.typed.json.parser.JsonTree implements ObjectTree {

	
	private SyntaxToken openCurlyBraceToken;
	private List<PairTree> pairs;
	private SyntaxToken closeCurlyBraceToken;
	
	public ObjectTreeImpl(InternalSyntaxToken openCurlyBraceToken,
			List<PairTree> pairs, InternalSyntaxToken closeCurlyBraceToken) {
		super(JsonLexer.OBJECT);
		this.openCurlyBraceToken = openCurlyBraceToken;
		this.pairs = pairs;
		this.closeCurlyBraceToken = closeCurlyBraceToken;
	}

	@Override
	public Kind getKind() {
		return Kind.OBJECT;
	}

	@Override
	public void accept(TreeVisitor visitor) {
		visitor.visitObject(this);
	}

	@Override
	public SyntaxToken openCurlyBrace() {
		return openCurlyBraceToken;
	}

	@Override
	public List<PairTree> pairs() {
		return pairs;
	}

	@Override
	public SyntaxToken closeCurlyBrace() {
		return closeCurlyBraceToken;
	}

	@Override
	public Iterator<Tree> childrenIterator() {
		ImmutableList.Builder<Tree> iteratorBuilder = ImmutableList.builder();
		iteratorBuilder.add(openCurlyBraceToken);
		iteratorBuilder.addAll(pairs);
		iteratorBuilder.add(closeCurlyBraceToken);
		return iteratorBuilder.build().iterator();
	}
}
