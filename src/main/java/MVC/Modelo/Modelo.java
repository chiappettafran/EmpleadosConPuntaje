package MVC.Modelo;

import Clases.Curso;
import Clases.Empleado;
import Clases.Inscripcion;
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

    public ArrayList<Empleado> maximoPuntaje() {
        ArrayList<Empleado> empleados = new ArrayList<>();
        for (Integer legajo : Inscripcion.legajosCursadores()) {
            empleados.add(Empleado.extraerEmpleado(legajo));
        }
        return empleados;
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
