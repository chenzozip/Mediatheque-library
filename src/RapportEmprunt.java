import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Memorise les titres empruntes pendant une session.
 * AutoCloseable : utilisable dans un try-with-resources, close() est appele
 * automatiquement en fin de bloc, MEME si une exception est levee.
 */
public class RapportEmprunt implements AutoCloseable {
    private final List<String> titres = new ArrayList<>();
    private boolean ferme = false;

    public void enregistrer(String titre) {
        if (ferme) throw new IllegalStateException("Rapport deja ferme");
        titres.add(titre);
    }

    public List<String> getTitres() { return Collections.unmodifiableList(titres); }

    public boolean estFerme() { return ferme; }

    @Override
    public void close() {
        ferme = true;
        System.out.println("Rapport ferme : " + titres.size() + " emprunt(s) " + titres);
    }
}
