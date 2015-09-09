package sslr.typed.json.parser;

import java.util.Iterator;

import org.sonar.sslr.grammar.GrammarRuleKey;

public class InternalSyntaxToken extends JsonTree implements SyntaxToken {

	private int startIndex;
	private int endIndex;
	private final int line;
	private final int column;
	private final String value;
	private final boolean isEOF;

	protected InternalSyntaxToken(InternalSyntaxToken internalSyntaxToken) {
		super(null);
		this.value = internalSyntaxToken.value;
		this.line = internalSyntaxToken.line;
		this.column = internalSyntaxToken.column;
		this.startIndex = internalSyntaxToken.startIndex;
		this.endIndex = internalSyntaxToken.endIndex;
		this.isEOF = internalSyntaxToken.isEOF;
	}

	public InternalSyntaxToken(int line, int column, String value,
			int startIndex, int endIndex, boolean isEOF) {
		super(null);
		this.value = value;
		this.line = line;
		this.column = column;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.isEOF = isEOF;
	}

	public int fromIndex() {
		return startIndex;
	}

	@Override
	public String text() {
		return value;
	}

	@Override
	public void accept(TreeVisitor visitor) {
		// FIXME do nothing at the moment
	}

	@Override
	public int line() {
		return line;
	}

	@Override
	public int column() {
		return column;
	}

	@Override
	public Kind getKind() {
		return Kind.TOKEN;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	public boolean isEOF() {
		return isEOF;
	}

	public void setGrammarRuleKey(GrammarRuleKey grammarRuleKey) {
		this.grammarRuleKey = grammarRuleKey;
	}

	@Override
	public Iterator<Tree> childrenIterator() {
	    throw new UnsupportedOperationException();
	}
	
	@Override
	public String toString() {
		return text();
	}
}
