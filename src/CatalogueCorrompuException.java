/** Le fichier source du catalogue est illisible ; l'erreur d'origine est conservee. */
public class CatalogueCorrompuException extends MediathequeException {

    public CatalogueCorrompuException(String message, Throwable cause) {
        super(message, cause);                         // on garde la cause !
    }
}
