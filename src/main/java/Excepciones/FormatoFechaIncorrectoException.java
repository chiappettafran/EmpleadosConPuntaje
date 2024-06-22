package Excepciones;

public class FormatoFechaIncorrectoException extends Exception {
    public FormatoFechaIncorrectoException() {
        super("Formato de fecha incorrecto (AAAA-MM-DD)");
    }
}
