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

/** 
 * Exemple de règle implémentée avec l'API de base de SSLR.
 * 
 * @see sslr.typed.json.checks.NoPairWithNameEqualValueCheck pour comparer avec l'autre approche
 */
@Rule(
		key = "stupid_rule_1", 
		name = "Property name should not be equals to their value.", 
		priority = Priority.MINOR)
// On précise les informations SQALE pour le plugin de dette technique
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.DATA_RELIABILITY)
@SqaleConstantRemediation("2min")
public class NoPairWithNameEqualValueCheck extends SquidCheck<LexerlessGrammar> {

	@Override
	public void init() {
		subscribeTo(JsonGrammar.PAIR); // Seuls les noeuds PAIR nous intéresse pour cette règle
	}

	@Override
	public void visitNode(AstNode astNode) { // On sait que AstNode correspond forcément à un noeud
											 // PAIR car une seule souscription
		
		AstNode colon = astNode.getFirstChild(JsonGrammar.COLON);
		AstNode name = colon.getPreviousAstNode();
		AstNode value = colon.getNextAstNode().getNextAstNode(); // Ignore les éventuels espaces
		AstNode stringValue = value.getFirstChild(JsonGrammar.STRING);

		if (stringValue != null) { // La propriété de la valeur est-elle bien un noeud STRING ? 
			if (extractString(name).equals(extractString(stringValue))) {
				  getContext().createLineViolation(this, "Wrong!", astNode);
				  // Le contexte sert à remonter les violations
			}
		}
	}

	private String extractString(AstNode node) {
		return node.getFirstChild().getNextAstNode().getTokenValue(); // On ignore le premier "
	}

}
