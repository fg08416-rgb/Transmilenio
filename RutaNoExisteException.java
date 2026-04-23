public class RutaNoExisteException extends TransMilenioException {
    public RutaNoExisteException(String nombreRuta) {
        super("La ruta '" + nombreRuta + "' no se encuentra registrada en el sistema.");
    }
}