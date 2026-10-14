import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Catalogue generique : n'accepte que des sous-types de Document. */
public class Catalogue<T extends Document> {
    private final List<T> elements = new ArrayList<>();

    public void ajouter(T element) { elements.add(element); }

    /** Recherche par titre, sans tenir compte des majuscules. */
    public Optional<T> rechercherParTitre(String titre) {
        return elements.stream()
                .filter(d -> d.getTitre().equalsIgnoreCase(titre))
                .findFirst();
    }

    public void afficherTout() {
        elements.forEach(d -> System.out.println(d.descriptionCourte()));
    }

    /** Bonus seance 1 : methode generique bornee. */
    public static <T extends Comparable<T>> T max(List<T> liste) {
        T m = liste.get(0);
        for (T e : liste) if (e.compareTo(m) > 0) m = e;
        return m;
    }
}
