import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmpruntManagerTest {

    @Test void emprunt_document_inconnu() {
        EmpruntManager m = new EmpruntManager(new Catalogue<>());
        MediathequeException ex = assertThrows(
                DocumentIntrouvableException.class,
                () -> m.emprunter("Fantome"));
        assertTrue(ex.getMessage().contains("Fantome"));
    }

    @Test void double_emprunt() throws MediathequeException {
        Catalogue<Document> c = new Catalogue<>();
        c.ajouter(new Livre("Clean Code", 2008, "R. Martin", 464));
        EmpruntManager m = new EmpruntManager(c);
        m.emprunter("Clean Code");                      // 1er emprunt : aucune exception
        DocumentIndisponibleException ex = assertThrows(
                DocumentIndisponibleException.class,
                () -> m.emprunter("Clean Code"));       // 2e : leve au bon moment
        assertTrue(ex.getMessage().contains("Clean Code"));
    }

    @Test void revue_non_empruntable() {
        Catalogue<Document> c = new Catalogue<>();
        c.ajouter(new Revue("Science", 2026, 412));
        EmpruntManager m = new EmpruntManager(c);
        DocumentIndisponibleException ex = assertThrows(
                DocumentIndisponibleException.class,
                () -> m.emprunter("Science"));
        assertTrue(ex.getMessage().contains("Science"));
        assertTrue(ex.getMessage().contains("consultation sur place"));
    }

    @Test void dvd_empruntable_via_le_manager() throws MediathequeException {
        Dvd d = new Dvd("Inception", 2010, 148);
        Catalogue<Document> c = new Catalogue<>();
        c.ajouter(d);
        new EmpruntManager(c).emprunter("inception");  // recherche insensible a la casse
        assertFalse(d.estDisponible());
    }

    @Test void emprunt_puis_retour_puis_nouvel_emprunt() throws MediathequeException {
        Livre l = new Livre("Dune", 1965, "F. Herbert", 612);
        Catalogue<Document> c = new Catalogue<>();
        c.ajouter(l);
        EmpruntManager m = new EmpruntManager(c);
        m.emprunter("Dune");
        assertFalse(l.estDisponible());
        l.rendre();
        assertDoesNotThrow(() -> m.emprunter("Dune"));
    }

    @Test void une_seule_clause_catch_suffit_grace_a_la_racine() {
        EmpruntManager m = new EmpruntManager(new Catalogue<>());
        try {
            m.emprunter("Absent");
            fail("une exception etait attendue");
        } catch (MediathequeException e) {             // attrape toute la hierarchie
            assertInstanceOf(DocumentIntrouvableException.class, e);
        }
    }

    private static void fail(String message) { throw new AssertionError(message); }
}
