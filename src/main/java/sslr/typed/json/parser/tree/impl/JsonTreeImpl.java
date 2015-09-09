package sslr.typed.json.parser.tree.impl;

import java.util.Iterator;

import sslr.typed.json.parser.JsonLexer;
import sslr.typed.json.parser.Tree;
import sslr.typed.json.parser.TreeVisitor;
import sslr.typed.json.parser.tree.JsonTree;
import sslr.typed.json.parser.tree.TypeTree;

import com.google.common.collect.ImmutableList;

public class JsonTreeImpl extends sslr.typed.json.parser.JsonTree implements JsonTree {

	private TypeTree type;
	
	public JsonTreeImpl(TypeTree type) {
		super(JsonLexer.JSON);
		this.type = type;
	}


	@Override
	public Kind getKind() {
		return Kind.JSON;
	}

	@Override
	public void accept(TreeVisitor visitor) {
		visitor.visitJson(this);
	}

	@Override
	public TypeTree type() {
		return type;
	}
	
	@Override
	public Iterator<Tree> childrenIterator() {
		ImmutableList.Builder<Tree> iteratorBuilder = ImmutableList.builder();
		iteratorBuilder.add(type);
		return iteratorBuilder.build().iterator();
	}

}
