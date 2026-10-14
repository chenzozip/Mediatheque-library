/**
 * Orchestre un emprunt a partir d'un titre.
 * COMPOSITION + DELEGATION : possede un Catalogue et lui confie la recherche,
 * sans dupliquer son code ni en heriter.
 */
public class EmpruntManager {
    private final Catalogue<Document> catalogue;        // COMPOSITION

    public EmpruntManager(Catalogue<Document> catalogue) {
        this.catalogue = catalogue;
    }

    /**
     * @throws DocumentIntrouvableException   aucun document ne porte ce titre
     * @throws DocumentIndisponibleException  revue (consultation sur place) ou deja emprunte
     */
    public void emprunter(String titre) throws MediathequeException {
        // 1. recherche (delegation au catalogue) : absent -> exception metier
        Document d = catalogue.rechercherParTitre(titre)
                .orElseThrow(() -> new DocumentIntrouvableException(titre));

        // 2. verification de la capacite "Empruntable" (pattern variable, Java 16+)
        if (!(d instanceof Empruntable e)) {
            throw new DocumentIndisponibleException(titre, "consultation sur place uniquement");
        }

        // 3. emprunt : peut lever DocumentIndisponibleException (deja emprunte)
        e.emprunter();
    }
}
