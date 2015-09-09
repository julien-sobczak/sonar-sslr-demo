package sslr.basic.json.parser;

import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.sslr.grammar.LexerlessGrammarBuilder;
import org.sonar.sslr.parser.LexerlessGrammar;

/**
 * JSON grammar. See <a href="http://json.org/">http://json.org/</a>.
 */
public enum JsonGrammar implements GrammarRuleKey {

  // On commence par lister tous les terminaux et non-terminaux de notre grammaire
	
  JSON,
  ARRAY,
  OBJECT,
  PAIR,
  VALUE,
  STRING,
  COLON,
  NUMBER,
  TRUE,
  FALSE,
  NULL,
  WHITESPACE;
  /*
   * Remarque : Les caractères de ponctuation par exemple peuvent être renseigner directement
   * dans la définition de la grammaire ci-dessous. Inutile de les faire figurer dans le 
   * type énuméré des symboles.
   */

  public static LexerlessGrammar create() {
	    LexerlessGrammarBuilder b = LexerlessGrammarBuilder.create();
	    // Lexerless vs Lexerfull => http://comments.gmane.org/gmane.comp.java.sonar.devel/11974
	    // Lexerless permet la composition de grammaire (ex : JavaScript dans JSP dans HTML)
	    // -> Lexerfull sera sûrement supprimé un jour.

	    // Implémentation proche d'une définition BNF ou en Yacc mais en DSL interne
	    b.rule(JSON).is(b.firstOf(ARRAY, OBJECT));
	    b.rule(OBJECT).is("{", WHITESPACE, b.optional(PAIR, b.zeroOrMore(",", WHITESPACE, PAIR)), "}", WHITESPACE);
	    b.rule(PAIR).is(STRING, COLON, WHITESPACE, VALUE);
	    b.rule(ARRAY).is("[", WHITESPACE, b.optional(VALUE, b.zeroOrMore(",", WHITESPACE, VALUE)), "]", WHITESPACE);
	    b.rule(STRING).is('"', b.regexp("([^\"\\\\]|\\\\([\"\\\\/bfnrt]|u[0-9a-fA-F]{4}))*+"), '"', WHITESPACE);
	    b.rule(VALUE).is(b.firstOf(STRING, NUMBER, OBJECT, ARRAY, TRUE, FALSE, NULL), WHITESPACE);
	    b.rule(NUMBER).is(b.regexp("-?+(0|[1-9][0-9]*+)(\\.[0-9]++)?+([eE][+-]?+[0-9]++)?+"));
	    b.rule(COLON).is(":");
	    b.rule(TRUE).is("true");
	    b.rule(FALSE).is("false");
	    b.rule(NULL).is("null");
	    b.rule(WHITESPACE).is(b.regexp("[ \n\r\t\f]*+"));
	    
	    b.setRootRule(JSON); // Le noeud racine de notre AST

	    return b.build();
	  }

}
