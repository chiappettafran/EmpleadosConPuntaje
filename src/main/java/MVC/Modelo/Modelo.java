package MVC.Modelo;

import Clases.Curso;
import Clases.Empleado;
import Clases.Sueldo;

import java.util.*;

public class Modelo {





    public HashMap<Empleado, Curso> puedeRealizar(int legajo, int curso) {
        HashMap<Empleado, Curso> mapPuedeRealizar = new HashMap<>();
        Empleado empleado = Empleado.extraerEmpleado(legajo);
        Curso curso1 = Curso.extraerCurso(curso);
        mapPuedeRealizar.put(empleado, curso1);
        return mapPuedeRealizar;
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
