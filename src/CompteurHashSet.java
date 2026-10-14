import java.util.Collection;
import java.util.HashSet;

/**
 * Live coding - version NAIVE par heritage (a ne pas reproduire).
 * addAll(List.of("a","b","c")) donne 6 et non 3 : HashSet.addAll (herite
 * d'AbstractCollection) appelle add() pour chaque element, et par liaison
 * dynamique c'est NOTRE add() qui s'execute. On depend d'un detail
 * d'implementation non documente : classe de base fragile.
 */
public class CompteurHashSet<E> extends HashSet<E> {
    private int nbAjouts = 0;

    @Override public boolean add(E e) {
        nbAjouts++;
        return super.add(e);
    }

    @Override public boolean addAll(Collection<? extends E> c) {
        nbAjouts += c.size();
        return super.addAll(c);
    }

    public int getNbAjouts() { return nbAjouts; }
}
