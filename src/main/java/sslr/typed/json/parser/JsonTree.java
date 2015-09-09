package sslr.typed.json.parser;

import java.util.Iterator;

import org.sonar.sslr.grammar.GrammarRuleKey;

public abstract class JsonTree implements Tree {

	protected GrammarRuleKey grammarRuleKey;

	public JsonTree(GrammarRuleKey grammarRuleKey) {
		this.grammarRuleKey = grammarRuleKey;
	}

	@Override
	public final boolean is(Kind... kind) {
		if (getKind() != null) {
			for (Kind kindIter : kind) {
				if (getKind() == kindIter) {
					return true;
				}
			}
		}
		return false;
	}

	public abstract Kind getKind();

	/**
	 * Creates iterator for children of this node. Note that iterator may
	 * contain {@code null} elements.
	 *
	 * @throws java.lang.UnsupportedOperationException
	 *             if {@link #isLeaf()} returns {@code true}
	 */
	public abstract Iterator<Tree> childrenIterator();

	public boolean isLeaf() {
		return false;
	}

	public GrammarRuleKey getGrammarRuleKey() {
		return grammarRuleKey;
	}

}
