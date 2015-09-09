package sslr.typed.json.parser;

import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.sslr.grammar.LexerlessGrammarBuilder;

/**
 * JSON grammar. See <a href="http://json.org/">http://json.org/</a>.
 */
public enum JsonLexer implements GrammarRuleKey {

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
  COLON,
  COMMA,
  LBRK,
  LWING,
  RBRK,
  RWING;

  public static LexerlessGrammarBuilder createBuilder() {
	    LexerlessGrammarBuilder b = LexerlessGrammarBuilder.create();

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
