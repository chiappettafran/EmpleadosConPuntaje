package Excepciones;

public class DatoObligatorioNullException extends Exception {
    public DatoObligatorioNullException() {
        super("Uno o más de los datos obligatorios (*) es nulo!");
    }
}
