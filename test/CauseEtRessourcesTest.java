import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CauseEtRessourcesTest {

    @Test void cause_chainee() {
        Path absent = Path.of("fichier-qui-n-existe-pas.txt");
        CatalogueCorrompuException ex = assertThrows(
                CatalogueCorrompuException.class,
                () -> ChargeurCatalogue.chargerTitres(absent));
        assertTrue(ex.getMessage().contains("fichier-qui-n-existe-pas.txt"));   // message
        assertInstanceOf(IOException.class, ex.getCause());                    // cause conservee
    }

    @Test void chargement_nominal() throws IOException, CatalogueCorrompuException {
        Path f = Files.createTempFile("titres", ".txt");
        try {
            Files.write(f, List.of("Dune", "Clean Code"));
            assertEquals(List.of("Dune", "Clean Code"), ChargeurCatalogue.chargerTitres(f));
        } finally {
            Files.deleteIfExists(f);
        }
    }

    @Test void rapport_ferme_meme_si_exception() throws MediathequeException {
        Catalogue<Document> c = new Catalogue<>();
        c.ajouter(new Livre("Clean Code", 2008, "R. Martin", 464));
        EmpruntManager m = new EmpruntManager(c);
        RapportEmprunt rapport = new RapportEmprunt();

        assertThrows(DocumentIndisponibleException.class, () -> {
            try (rapport) {                               // Java 9+ : ressource effectivement finale
                m.emprunter("Clean Code");
                rapport.enregistrer("Clean Code");
                m.emprunter("Clean Code");                // leve : close() sera appele
            }
        });

        assertTrue(rapport.estFerme());
        assertEquals(1, rapport.getTitres().size());
    }

    @Test void rapport_ferme_en_fin_de_bloc_nominal() throws MediathequeException {
        Catalogue<Document> c = new Catalogue<>();
        c.ajouter(new Dvd("Inception", 2010, 148));
        EmpruntManager m = new EmpruntManager(c);
        RapportEmprunt rapport = new RapportEmprunt();
        try (rapport) {
            m.emprunter("Inception");
            rapport.enregistrer("Inception");
            assertFalse(rapport.estFerme());
        }
        assertTrue(rapport.estFerme());
    }
}
