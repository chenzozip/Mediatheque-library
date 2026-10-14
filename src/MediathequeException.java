/**
 * Racine de la hierarchie d'exceptions metier de la mediatheque.
 *
 * CHOIX : checked (extends Exception).
 * Justification : l'indisponibilite d'un document ou l'absence d'un titre sont des
 * situations METIER attendues et recuperables (proposer une reservation, afficher un
 * message a l'usager, suggerer un autre titre). L'appelant peut reagir utilement :
 * le compilateur doit donc l'obliger a traiter le cas.
 * L'alternative "unchecked + l'appelant verifie estDisponible() avant" est fragile :
 * entre la verification et l'emprunt, un autre usager peut avoir emprunte le document.
 * Regle appliquee uniformement a toute la hierarchie MediathequeException.
 *
 * Abstraite : on ne lance jamais une "exception mediatheque" generique, toujours un
 * cas precis. Elle sert de racine pour un seul catch (MediathequeException e).
 * Constructeurs protected : seules les sous-classes peuvent les appeler.
 */
public abstract class MediathequeException extends Exception {

    protected MediathequeException(String message) {
        super(message);
    }

    protected MediathequeException(String message, Throwable cause) {
        super(message, cause);
    }
}
