public class EstacionNoExisteException extends TransMilenioException {
    public EstacionNoExisteException(String nombreEstacion) {
        super("La estación '" + nombreEstacion + "' no se encuentra registrada en el sistema.");
    }
}