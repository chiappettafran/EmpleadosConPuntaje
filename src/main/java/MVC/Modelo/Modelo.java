package MVC.Modelo;

import Clases.Curso;
import Clases.Empleado;
import Clases.Sueldo;

public class Modelo {
    public boolean puedeRealizar() {
        return false;
    }

    public int[] maximoPuntaje(){
        return null;
    }

    public double importeBruto() {
        return 0D;
    }

    public void liquidarSueldos() {
        new Sueldo().liquidarSueldos();
    }

}
