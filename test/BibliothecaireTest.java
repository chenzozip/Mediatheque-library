import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BibliothecaireTest {

    @Test void chercher_delegue_au_catalogue_recu() {
        Catalogue<Document> c = new Catalogue<>();
        Livre l = new Livre("Dune", 1965, "F. Herbert", 612);
        c.ajouter(l);
        Bibliothecaire b = new Bibliothecaire("Sarra", c);
        assertSame(l, b.chercher("dune").orElseThrow());
        assertTrue(b.chercher("Inconnu").isEmpty());
    }

    @Test void on_peut_lui_donner_un_autre_catalogue() {
        Catalogue<Document> jeunesse = new Catalogue<>();
        jeunesse.ajouter(new Revue("Okapi", 2026, 1200));
        Bibliothecaire b = new Bibliothecaire("Ali", jeunesse);
        assertTrue(b.chercher("Okapi").isPresent());
    }
}
