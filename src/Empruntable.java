/** Capacite "sait etre emprunte" : livres et DVD, pas les revues. */
public interface Empruntable {
    void emprunter();
    void rendre();
    boolean estDisponible();
}
