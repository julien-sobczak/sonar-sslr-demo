#SonarSource Language Recognizer (SSLR) Démonstration

SSLR est utilisé pour supporter de nouveaux langages dans SonarQube. L'écriture de règles Sonar "The Sonar Way" est étroitement liée à ce projet. Les règles travaillent directement avec l'AST (Abstract Syntax Tree) créé par SSLR.

La documentation SSLR est minimaliste. Le projet [sonar-examples](https://github.com/SonarSource/sonar-examples/tree/master/plugins/java-custom-rules/src/main/java/org/sonar/samples/java) comporte quelques règles Java pour s'inspirer et débuter, mais fait défaut lorsque l'on cherche véritablement à comprendre le fonctionnement derrière ces règles.

Pour cette démonstration, c'est le langage JSON qui été choisi. Deux implémentations du langage sont proposées :
* Une implémentation basique basée sur l'API classique de SSLR.
* Une implémentation typées basée sur l'API expérimentale de SSLR.

Pour chaque implémentation, un exemple de règle est proposé pour comparer les deux approches.
