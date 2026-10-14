// Fourni tel quel - qu'est-ce qui vous gene ?
public class BibliothecaireAbusif extends Catalogue<Document> {
    private final String nom;

    public BibliothecaireAbusif(String nom) { this.nom = nom; }

    public void accueillir() {
        System.out.println("Bonjour, je suis " + nom);
    }
    // "Comme ca j'ai deja ajouter(), rechercherParTitre()... gratuit !"
}
