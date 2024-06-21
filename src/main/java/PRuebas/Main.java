package PRuebas;

import Clases.Curso;
import Clases.Empleado;
import Clases.Inscripcion;
import Clases.Sueldo;
import MVC.Controladores.ControladorVistaPrincipal;
import MVC.Vistas.VistaPrincipal;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        new Sueldo().liquidarSueldos();

        //System.out.println(Curso.extraerCurso(6).getPuntaje());
    }
}