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

    public Sueldo importeBruto(int legajo, String periodo) {
        return Sueldo.extraerBono(legajo, periodo);
    }

    public void liquidarSueldos() {
        new Sueldo().liquidarSueldos();
    }

    public void agregarEmpleado(Empleado empleadoNuevo) {
        Empleado.agregarEmpleado(empleadoNuevo);
    }

}
