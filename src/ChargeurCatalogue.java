import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class ChargeurCatalogue {

    private ChargeurCatalogue() { }

    /** Lit un fichier contenant un titre par ligne. */
    public static List<String> chargerTitres(Path chemin) throws CatalogueCorrompuException {
        try (BufferedReader lecteur = Files.newBufferedReader(chemin)) {   // fermeture garantie
            return lecteur.lines().toList();
        } catch (IOException e) {
            throw new CatalogueCorrompuException(
                    "Echec du chargement : " + chemin, e);              // message utile + cause
        }
    }
}
