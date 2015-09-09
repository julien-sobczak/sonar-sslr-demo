package sslr.typed.json;

import java.nio.charset.Charset;
import java.util.Iterator;

import org.junit.Test;

import sslr.typed.json.checks.NoPairWithNameEqualValueCheck;
import sslr.typed.json.parser.JsonGrammar;
import sslr.typed.json.parser.JsonLexer;
import sslr.typed.json.parser.JsonNodeBuilder;
import sslr.typed.json.parser.JsonTree;
import sslr.typed.json.parser.Tree;
import sslr.typed.json.parser.TreeFactory;

import com.sonar.sslr.api.typed.ActionParser;

/**
 * Classe de tests pour exercer notre grammaire et appliquer une règle sur l'AST créé.
 */
public class JsonGrammarTest {

	@Test
	public void useActionParserForAMoreConvenientApi() throws Exception {
		ActionParser<Tree> parser = new ActionParser<Tree>(
	    	      Charset.defaultCharset(),
	    	      JsonLexer.createBuilder(),
	    	      JsonGrammar.class,
	    	      new TreeFactory(),
	    	      new JsonNodeBuilder(),
	    	      JsonLexer.JSON);
		
		Tree tree = parser.parse("{\"test\": \"Julien\"}");
		
		printTokens(tree, "");
	}
	

	@Test
	public void withAFormattedText() throws Exception {
		ActionParser<Tree> parser = new ActionParser<Tree>(
	    	      Charset.defaultCharset(),
	    	      JsonLexer.createBuilder(),
	    	      JsonGrammar.class,
	    	      new TreeFactory(),
	    	      new JsonNodeBuilder(),
	    	      JsonLexer.JSON);
		
		Tree tree = parser.parse(
				"{" +
				"  \"test\": \"Julien\"" +
				"}");
		
		printTokens(tree, "");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCustomRuleOnNonCompliantCode() throws Exception {
		sslr.typed.json.parser.tree.JsonTree tree = (sslr.typed.json.parser.tree.JsonTree) parse(
				"{" +
				"  \"test\": \"test\"" +
				"}");
		
		NoPairWithNameEqualValueCheck rule = new NoPairWithNameEqualValueCheck();
		rule.scan(tree);
	}
	
	@Test
	public void testCustomRuleOnCompliantCode() throws Exception {
		sslr.typed.json.parser.tree.JsonTree tree = (sslr.typed.json.parser.tree.JsonTree) parse(
				"{" +
				"  \"aValue\": \"anotherValue\"" +
				"}");
		
		NoPairWithNameEqualValueCheck rule = new NoPairWithNameEqualValueCheck();
		rule.visitJson(tree);
	}
	
	

	/* Test Helpers */
	
	private void printTokens(Tree tree, String identation) {
		JsonTree jsonTree = (JsonTree) tree;
		StringBuilder description = new StringBuilder(identation);
		description.append("[").append(jsonTree.getKind());
		if (jsonTree.isLeaf()) {
			description.append(", " + jsonTree);
		}
		description.append("]");

		System.out.println(description);
		if (!jsonTree.isLeaf()) {
			Iterator<Tree> childrenIterator = jsonTree.childrenIterator();
			while (childrenIterator.hasNext()) {
				printTokens(childrenIterator.next(), identation + "  ");
			}
		}
	}
	
	
	private static Tree parse(String json) {
		ActionParser<Tree> parser = new ActionParser<Tree>(
	    	      Charset.defaultCharset(),
	    	      JsonLexer.createBuilder(),
	    	      JsonGrammar.class,
	    	      new TreeFactory(),
	    	      new JsonNodeBuilder(),
	    	      JsonLexer.JSON);
		
		Tree tree = parser.parse(json);
		
		return tree;
	}

}
