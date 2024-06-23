package MVC.Controladores;

import Clases.Curso;
import Clases.Empleado;
import MVC.Modelo.Modelo;
import MVC.Vistas.VistaVerificarEligibilidad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ControladorMostrarElegibilidad {
    private VistaVerificarEligibilidad vista;
    private Modelo modelo;

    public ControladorMostrarElegibilidad() {
        vista = new VistaVerificarEligibilidad();
        modelo = new Modelo();
        iniciar();
    }

    public void iniciar(){
        vista.setVisible(true);

        vista.consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int empleado = Integer.parseInt(((String) vista.empleadosComboBox.getSelectedItem()).split(",")[0]);
                    int curso = Integer.parseInt(((String) vista.cursosComboBox.getSelectedItem()).split("-")[0]);
                    HashMap<Empleado, Curso> mapPuedeRealizar = modelo.puedeRealizar(empleado, curso);
                    Empleado datoEmpleado = mapPuedeRealizar.keySet().iterator().next();
                    Curso datoCurso = mapPuedeRealizar.get(datoEmpleado);
                    vista.mostrarCodicionParaCurso(datoEmpleado, datoCurso);
                }catch (Exception exc){
                    JOptionPane.showMessageDialog(null, "Error al mostrar eligibilidad de curso");
                }
            }
        });
    }
}
