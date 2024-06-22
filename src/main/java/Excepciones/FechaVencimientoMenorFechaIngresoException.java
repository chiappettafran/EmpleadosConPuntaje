package Excepciones;

public class FechaVencimientoMenorFechaIngresoException extends Exception {
    public FechaVencimientoMenorFechaIngresoException() {
        super("La fecha de vencimiento es menor a la fecha de ingreso.");
    }
}
