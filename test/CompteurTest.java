import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import static org.junit.jupiter.api.Assertions.*;

class CompteurTest {

    @Test void heritage_fragile_compte_6_au_lieu_de_3() {
        CompteurHashSet<String> s = new CompteurHashSet<>();
        s.addAll(List.of("a", "b", "c"));
        assertEquals(6, s.getNbAjouts());              // la classe de base fragile
    }

    @Test void composition_compte_3() {
        CompteurSet<String> s = new CompteurSet<>(new HashSet<>());
        s.addAll(List.of("a", "b", "c"));
        assertEquals(3, s.getNbAjouts());
        assertEquals(3, s.size());
    }

    @Test void composition_fonctionne_avec_n_importe_quel_set() {
        CompteurSet<String> s = new CompteurSet<>(new TreeSet<>());
        s.add("b");
        s.add("a");
        assertEquals(2, s.getNbAjouts());
        assertEquals("a", s.iterator().next());        // TreeSet : ordre naturel conserve
        Set<String> vue = s;                           // un CompteurSet EST un Set
        assertTrue(vue.contains("b"));
    }
}
