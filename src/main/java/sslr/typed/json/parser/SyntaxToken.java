package sslr.typed.json.parser;


/**
 * Representes un token dans l'AST (c'est-à-dire un symbole terminal).
 */
public interface SyntaxToken extends Tree {

  String text();

  int line();

  int column();
}
