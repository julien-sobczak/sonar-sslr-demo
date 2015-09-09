package sslr.typed.json.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.api.CodeVisitor;

import sslr.typed.json.parser.BaseTreeVisitor;
import sslr.typed.json.parser.SyntaxToken;
import sslr.typed.json.parser.Tree.Kind;
import sslr.typed.json.parser.tree.PairTree;

@Rule(
		key = "stupid_rule_1", 
		name = "Property name should not be equals to their value.", 
		priority = Priority.MINOR)
public class NoPairWithNameEqualValueCheck extends BaseTreeVisitor implements CodeVisitor {

	
	@Override
	public void visitPair(PairTree tree) {
		SyntaxToken name = tree.name();
		if (tree.value().value().is(Kind.TOKEN)) {
			SyntaxToken value = (SyntaxToken) tree.value().value();
			if (name.text().equals(value.text())) {
				// Use language specific context to report
				throw new IllegalArgumentException("Wrong!");
			}
		}
	}

}
