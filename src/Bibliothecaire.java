import java.util.Optional;

/*
 * ===================== Etape 3 - Reponses ecrites =====================
 *
 * 1) Un bibliothecaire est-il un catalogue ?
 *    Non. Un bibliothecaire est une personne qui UTILISE un catalogue : la relation
 *    "est un" est fausse, l'heritage n'etait justifie que par l'envie de recuperer du
 *    code "gratuit". Si Catalogue change (methode renommee, ajoutee, signature ou
 *    comportement modifie), BibliothecaireAbusif change avec lui sans qu'on y touche :
 *    c'est la classe de base fragile. Pour un client, bibliothecaire.ajouter(livre)
 *    n'a aucun sens : le bibliothecaire "s'ajouterait" un livre a lui-meme ; toute
 *    l'API publique du catalogue (ajouter, afficherTout, max...) lui est imposee.
 *
 * 2) Problemes de BibliothecaireAbusif extends Catalogue<Document> :
 *    - Relation "est un" fausse : l'heritage sert a reutiliser du code, pas a
 *      exprimer une vraie specialisation.
 *    - API polluee : tout client voit ajouter, rechercherParTitre, afficherTout sur
 *      un bibliothecaire ; l'encapsulation est affaiblie.
 *    - Couplage maximal : toute evolution de Catalogue peut casser le bibliothecaire
 *      silencieusement (classe de base fragile).
 *    - Substituabilite absurde : partout ou un Catalogue est attendu, on pourrait
 *      passer un bibliothecaire.
 *    - Heritage unique consomme : impossible d'heriter de Personne ou Employe.
 *    - Relation figee a la compilation : impossible de lui donner un autre catalogue
 *      (catalogue de test, section jeunesse...).
 *
 * 3) Refactoring ci-dessous : COMPOSITION (un Bibliothecaire POSSEDE un Catalogue,
 *    recu par son constructeur) + DELEGATION (chercher() delegue a
 *    catalogue.rechercherParTitre()). On n'expose que ce qui a un sens pour un
 *    bibliothecaire : accueillir() et chercher().
 * =======================================================================
 */
public class Bibliothecaire {
    private final String nom;
    private final Catalogue<Document> catalogue;   // COMPOSITION : possede un catalogue

    public Bibliothecaire(String nom, Catalogue<Document> catalogue) {
        this.nom = nom;
        this.catalogue = catalogue;
    }

    public String getNom() { return nom; }

    public void accueillir() {
        System.out.println("Bonjour, je suis " + nom);
    }

    // DELEGATION : on n'expose que ce qui a un sens pour un bibliothecaire
    public Optional<Document> chercher(String titre) {
        return catalogue.rechercherParTitre(titre);
    }
}
