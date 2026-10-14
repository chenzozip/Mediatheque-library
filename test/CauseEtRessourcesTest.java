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
}
