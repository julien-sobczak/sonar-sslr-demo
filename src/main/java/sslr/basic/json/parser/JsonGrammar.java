package sslr.basic.json.parser;

import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.sslr.grammar.LexerlessGrammarBuilder;
import org.sonar.sslr.parser.LexerlessGrammar;

/**
 * JSON grammar. See <a href="http://json.org/">http://json.org/</a>.
 */
public enum JsonGrammar implements GrammarRuleKey {

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

  public static LexerlessGrammar create() {
	    LexerlessGrammarBuilder b = LexerlessGrammarBuilder.create();

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
	    
	    b.setRootRule(JSON);

	    return b.build();
	  }

}
