package sslr.typed.json.parser;

import org.sonar.sslr.grammar.GrammarRuleKey;

import sslr.typed.json.parser.tree.ArrayTree;
import sslr.typed.json.parser.tree.JsonTree;
import sslr.typed.json.parser.tree.ObjectTree;
import sslr.typed.json.parser.tree.PairTree;
import sslr.typed.json.parser.tree.TypeTree;

/**
 * Common interface for all nodes in a syntax tree.
 */
public interface Tree {

  boolean is(Kind... kind);

  void accept(TreeVisitor visitor);

  public enum Kind implements GrammarRuleKey {
    JSON(JsonTree.class),
    ARRAY(ArrayTree.class),
    OBJECT(ObjectTree.class),
    PAIR(PairTree.class),
    TOKEN(SyntaxToken.class),
    VALUE(TypeTree.class);

    final Class<? extends Tree> associatedInterface;

    Kind(Class<? extends Tree> associatedInterface) {
      this.associatedInterface = associatedInterface;
    }

    public Class<? extends Tree> getAssociatedInterface() {
      return associatedInterface;
    }
  }

}
