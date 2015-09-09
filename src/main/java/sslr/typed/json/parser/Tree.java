package sslr.typed.json.parser;

import org.sonar.sslr.grammar.GrammarRuleKey;

import sslr.typed.json.parser.tree.ArrayTree;
import sslr.typed.json.parser.tree.JsonTree;
import sslr.typed.json.parser.tree.ObjectTree;
import sslr.typed.json.parser.tree.PairTree;
import sslr.typed.json.parser.tree.TypeTree;

/**
 * Interface commune à tous les types de noeud.
 * 
 * @see sslr.typed.json.parser.JsonTree 
 */
public interface Tree {

  boolean is(Kind... kind);

  /* Utiliser du pattern Visitor pour découpler l'arbre des règles qui doivent le parcourir */
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
