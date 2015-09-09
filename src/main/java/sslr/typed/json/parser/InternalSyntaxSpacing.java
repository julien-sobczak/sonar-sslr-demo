package sslr.typed.json.parser;

import java.util.Iterator;

public class InternalSyntaxSpacing extends JsonTree {

	private final int start;
	private final int end;

	public InternalSyntaxSpacing(int start, int end) {
		super(null);
		this.start = start;
		this.end = end;
	}

	@Override
	public Kind getKind() {
		return null; 
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public void accept(TreeVisitor visitor) {
		// Do nothing at the moment. Spacings are dropped anyway.
	}

	public int start() {
		return start;
	}

	public int end() {
		return end;
	}

	@Override
	public Iterator<Tree> childrenIterator() {
		throw new UnsupportedOperationException();
	}
}
