import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Refactoring par COMPOSITION : implemente Set (le contrat) et possede un Set
 * interne (l'implementation). Les add() internes de l'ensemble enveloppe ne nous
 * concernent plus. Fonctionne avec n'importe quel Set (HashSet, TreeSet...).
 * Ce wrapper qui enveloppe et delegue est le squelette du pattern Decorator.
 */
public class CompteurSet<E> implements Set<E> {
    private final Set<E> interne;                       // COMPOSITION
    private int nbAjouts = 0;

    public CompteurSet(Set<E> interne) { this.interne = interne; }

    @Override public boolean add(E e) {
        nbAjouts++;
        return interne.add(e);                          // DELEGATION
    }

    @Override public boolean addAll(Collection<? extends E> c) {
        nbAjouts += c.size();
        return interne.addAll(c);                       // ses add() internes : sans effet
    }

    public int getNbAjouts() { return nbAjouts; }

    // Autres methodes de Set : simple delegation vers interne
    @Override public int size()                          { return interne.size(); }
    @Override public boolean isEmpty()                   { return interne.isEmpty(); }
    @Override public boolean contains(Object o)          { return interne.contains(o); }
    @Override public Iterator<E> iterator()              { return interne.iterator(); }
    @Override public Object[] toArray()                  { return interne.toArray(); }
    @Override public <T> T[] toArray(T[] a)              { return interne.toArray(a); }
    @Override public boolean remove(Object o)            { return interne.remove(o); }
    @Override public boolean containsAll(Collection<?> c) { return interne.containsAll(c); }
    @Override public boolean retainAll(Collection<?> c)  { return interne.retainAll(c); }
    @Override public boolean removeAll(Collection<?> c)  { return interne.removeAll(c); }
    @Override public void clear()                        { interne.clear(); }

    @Override public boolean equals(Object o)            { return interne.equals(o); }
    @Override public int hashCode()                      { return interne.hashCode(); }
    @Override public String toString()                   { return interne.toString(); }
}
