package sslr.typed.json.parser;

import java.util.Iterator;

import org.sonar.sslr.grammar.GrammarRuleKey;

/** 
 * Abstract class pour définir le comportement commun à chaque type de noeud (voir sous-classes).
 */
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
	 * Pour itérer sur les noeuds enfants de ce noeud.
	 */
	public abstract Iterator<Tree> childrenIterator();

	public boolean isLeaf() {
		return false; 
	}

	public GrammarRuleKey getGrammarRuleKey() {
		return grammarRuleKey;
	}

}
