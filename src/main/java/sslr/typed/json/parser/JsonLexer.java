package sslr.typed.json.parser;

import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.sslr.grammar.LexerlessGrammarBuilder;

/**
 * JSON grammar. See <a href="http://json.org/">http://json.org/</a>.
 */
public enum JsonLexer implements GrammarRuleKey {

  // Idem pour que la grammaire dans sslr.basic.json.parser.JsonGrammar
  JSON,
  ARRAY,
  OBJECT,
  PAIR,
  VALUE,
  LITERAL,
  STRING,
  NUMBER,
  TRUE,
  FALSE,
  NULL,
  WHITESPACE,
  // Le fonctionnement oblige à même spécifier les symboles terminaux
  // qui servent dans la définition des symboles non-terminaux
  // Voir : JsonGrammar dans ce même package.
  COLON,
  COMMA,
  LBRK,
  LWING,
  RBRK,
  RWING;

  public static LexerlessGrammarBuilder createBuilder() {
	    LexerlessGrammarBuilder b = LexerlessGrammarBuilder.create();

	    // Seuls les symboles terminaux sont définis ici.
	    // Les symboles non-terminaux sont définis dans JsonGrammar afin d'être fortement typés.
	    
	    b.rule(NUMBER).is(b.regexp("-?+(0|[1-9][0-9]*+)(\\.[0-9]++)?+([eE][+-]?+[0-9]++)?+"));
	    b.rule(TRUE).is("true");
	    b.rule(FALSE).is("false");
	    b.rule(NULL).is("null");
	    b.rule(STRING).is(b.regexp("\"([^\"\\\\]|\\\\([\"\\\\/bfnrt]|u[0-9a-fA-F]{4}))*+\""), WHITESPACE);
	    b.rule(WHITESPACE).is(b.regexp("[ \n\r\t\f]*+"));
	    
	    b.rule(COLON).is(":");
	    b.rule(COMMA).is(",");
	    b.rule(LBRK).is("[");
	    b.rule(LWING).is("{");
	    b.rule(RBRK).is("]");
	    b.rule(RWING).is("}");
	    
	    b.setRootRule(JSON);

	    return b;
	  }

}
