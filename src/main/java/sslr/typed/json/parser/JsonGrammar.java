package sslr.typed.json.parser;

import sslr.typed.json.parser.tree.ArrayTree;
import sslr.typed.json.parser.tree.JsonTree;
import sslr.typed.json.parser.tree.ObjectTree;
import sslr.typed.json.parser.tree.PairTree;
import sslr.typed.json.parser.tree.ValueTree;

import com.sonar.sslr.api.typed.GrammarBuilder;

public class JsonGrammar {

	private final GrammarBuilder<InternalSyntaxToken> b;
	private final TreeFactory f;

	public JsonGrammar(GrammarBuilder<InternalSyntaxToken> b, TreeFactory f) {
		this.b = b;
		this.f = f;
	}
	
    public JsonTree JSON() {
    	return b.<JsonTree> nonterminal(JsonLexer.JSON).is(
    			f.json(
    				b.firstOf(ARRAY(), OBJECT())));
    }
    
    public ObjectTree OBJECT() {
    	return b.<ObjectTree> nonterminal(JsonLexer.OBJECT).is(
    			f.object(
    					b.token(JsonLexer.LWING), 
    					b.token(JsonLexer.WHITESPACE),
    					b.optional(f.newTuple1(PAIR(), b.zeroOrMore(f.newTriple1(b.token(JsonLexer.COMMA), b.token(JsonLexer.WHITESPACE), PAIR())))), 
    					b.token(JsonLexer.RWING),
    					b.token(JsonLexer.WHITESPACE)));
    }
    
    public ArrayTree ARRAY() {
    	return b.<ArrayTree> nonterminal(JsonLexer.ARRAY).is(
    			f.array(
	    			b.token(JsonLexer.LBRK),
	    			b.token(JsonLexer.WHITESPACE), 
	    			b.optional(f.newTuple2(VALUE(), b.zeroOrMore(f.newTriple2(b.token(JsonLexer.COMMA), b.token(JsonLexer.WHITESPACE), VALUE())))), 
	    			b.token(JsonLexer.RBRK),
	    			b.token(JsonLexer.WHITESPACE)));
    }

    public PairTree PAIR() {
    	return b.<PairTree> nonterminal(JsonLexer.PAIR).is(
    			f.pair(
    					b.token(JsonLexer.STRING),
    					b.token(JsonLexer.COLON),
    					b.token(JsonLexer.WHITESPACE),
    					VALUE()));
    }
    
	public ValueTree VALUE() {
		return b.<ValueTree> nonterminal(JsonLexer.VALUE).is(
				f.value(
						b.firstOf(
								OBJECT(),
								ARRAY(),
								b.token(JsonLexer.STRING),
								b.token(JsonLexer.NUMBER),
								b.token(JsonLexer.TRUE),
								b.token(JsonLexer.FALSE), 
								b.token(JsonLexer.NULL),
						b.token(JsonLexer.WHITESPACE))));
	}
	
}
