package PRuebas;

import Clases.Curso;
import Clases.Empleado;
import Clases.Inscripcion;
import Clases.Sueldo;
import MVC.Controladores.ControladorVistaPrincipal;
import MVC.Vistas.VistaPrincipal;

import javax.swing.*;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        new ControladorVistaPrincipal().iniciar();

        //System.out.println(Sueldo.extraerBono(1, "2024-01").getIncentivoCapacitacion());

    }
}