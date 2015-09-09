package sslr.basic.json;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.nio.charset.Charset;

import org.junit.Test;
import org.sonar.squidbridge.SquidAstVisitorContext;
import org.sonar.sslr.parser.LexerlessGrammar;
import org.sonar.sslr.parser.ParserAdapter;

import sslr.basic.json.checks.NoPairWithNameEqualValueCheck;
import sslr.basic.json.parser.JsonGrammar;

import com.sonar.sslr.api.AstNode;

public class JsonGrammarTest {

	@Test
	public void parseJsonGrammar() throws Exception {
		LexerlessGrammar grammar = JsonGrammar.create();

		ParserAdapter<LexerlessGrammar> parser = new ParserAdapter<LexerlessGrammar>(
				Charset.defaultCharset(), grammar);
		AstNode ast = parser.parse("{\"test\": \"Julien\"}");

		printTokens(ast, "");
	}

	@Test
	public void testCustomRuleOnNonCompliantCode() throws Exception {
		// Given
		AstNode ast = parse("{\"test\": \"test\"}");
		AstNode pair = ast.getFirstDescendant(JsonGrammar.PAIR);

		// When
		SquidAstVisitorContext<LexerlessGrammar> context = mock(SquidAstVisitorContext.class);

		NoPairWithNameEqualValueCheck rule = new NoPairWithNameEqualValueCheck();
		rule.setContext(context);
		rule.visitNode(pair);
		
		// Then		
		verify(context, times(1)).createLineViolation(rule, "Wrong!", pair);
	}
	
	@Test
	public void testCustomRuleOnCompliantCode() throws Exception {
		// Given
		AstNode ast = parse("{\"oneValue\": \"anotherValue\"}");
		AstNode pair = ast.getFirstDescendant(JsonGrammar.PAIR);

		// When
		SquidAstVisitorContext<LexerlessGrammar> context = mock(SquidAstVisitorContext.class);

		NoPairWithNameEqualValueCheck rule = new NoPairWithNameEqualValueCheck();
		rule.setContext(context);
		rule.visitNode(pair);
		
		// Then		
		verify(context, never()).createLineViolation(rule, "Wrong!", pair);
	}


	/* Test Helpers */
	
	private void printTokens(AstNode ast, String indentation) {
		StringBuilder description = new StringBuilder(indentation);
		description.append("[").append(ast.getName());
		if (!ast.hasChildren()) {
			description.append(", " + ast.getTokenValue());
		}
		description.append("]");

		System.out.println(description);
		if (ast.hasChildren()) {
			for (AstNode eachChild : ast.getChildren()) {
				printTokens(eachChild, indentation + "  ");
			}
		}
	}
	
	private AstNode parse(String json) {
		LexerlessGrammar grammar = JsonGrammar.create();
		ParserAdapter<LexerlessGrammar> parser = new ParserAdapter<LexerlessGrammar>(
				Charset.defaultCharset(), grammar);
		AstNode ast = parser.parse(json);
		return ast;
	}

}
