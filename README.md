# Mediatheque - fil rouge POO Avancee & Design Patterns

IT Business School - 1re annee Cycle Ingenieur - S1.

## Structure

```
src/   code de production (package par defaut)
test/  tests JUnit 5
lib/   junit-platform-console-standalone-*.jar (non versionne)
```

## Compiler et lancer les tests

Placer `junit-platform-console-standalone-1.11.4.jar` (ou plus recent) dans `lib/`, puis :

```bash
javac -d bin -cp lib/* src/*.java test/*.java
java -jar lib/junit-platform-console-standalone-1.11.4.jar -cp bin --scan-classpath
```

Sous VS Code (Extension Pack for Java), `.vscode/settings.json` declare deja `src`, `test` et `lib/**/*.jar`.
