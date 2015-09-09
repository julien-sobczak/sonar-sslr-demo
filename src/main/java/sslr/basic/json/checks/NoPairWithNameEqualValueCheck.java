package sslr.basic.json.checks;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import org.sonar.squidbridge.checks.SquidCheck;
import org.sonar.sslr.parser.LexerlessGrammar;

import sslr.basic.json.parser.JsonGrammar;

import com.sonar.sslr.api.AstNode;

@Rule(
		key = "stupid_rule_1", 
		name = "Property name should not be equals to their value.", 
		priority = Priority.MINOR)
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.DATA_RELIABILITY)
@SqaleConstantRemediation("2min")
public class NoPairWithNameEqualValueCheck extends SquidCheck<LexerlessGrammar> {

	@Override
	public void init() {
		subscribeTo(JsonGrammar.PAIR);
	}

	@Override
	public void visitNode(AstNode astNode) {
		AstNode colon = astNode.getFirstChild(JsonGrammar.COLON);
		AstNode name = colon.getPreviousAstNode();
		AstNode value = colon.getNextAstNode().getNextAstNode(); // Ignore whitespace
		AstNode stringValue = value.getFirstChild(JsonGrammar.STRING);

		if (stringValue != null) {
			if (extractString(name).equals(extractString(stringValue))) {
				  getContext().createLineViolation(this, "Wrong!", astNode);
			}
		}
	}

	private String extractString(AstNode node) {
		return node.getFirstChild().getNextAstNode().getTokenValue(); // Ignore first token "
	}

}
