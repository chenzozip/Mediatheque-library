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

## Seance 2 - Exceptions et composition vs heritage

| Etape | Fichiers | Idee cle |
|---|---|---|
| 1 | `MediathequeException`, `DocumentIndisponibleException`, `DocumentIntrouvableException` | Hierarchie **checked** ; justification en commentaire dans `MediathequeException` |
| 2 | `Empruntable`, `Livre`, `Dvd`, `CatalogueTest` | `emprunter()` leve `DocumentIndisponibleException` (titre dans le message) |
| 3 | `Bibliothecaire` | Heritage abusif remplace par composition + delegation ; reponses ecrites en commentaire |
| 4 | `EmpruntManager` | Possede un `Catalogue<Document>`, delegue la recherche, `orElseThrow` + `instanceof Empruntable` |
| 5 | `EmpruntManagerTest`, `CauseEtRessourcesTest`, `ChargeurCatalogue`, `CatalogueCorrompuException` | Type, message **et** cause chainee testes |
| 6 | `RapportEmprunt` | `AutoCloseable` + try-with-resources : ferme meme si l'emprunt echoue |
| Cours | `CompteurHashSet`, `CompteurSet`, `CompteurTest` | Classe de base fragile (6 au lieu de 3), puis composition |

### Choix checked / unchecked

**Checked** pour toute la hierarchie : l'indisponibilite d'un document est une situation
metier attendue et recuperable (reservation, message a l'usager), donc le compilateur doit
obliger l'appelant a la traiter. Le motif « je verifie `estDisponible()` avant » d'une
version unchecked est fragile en cas d'acces concurrents.
