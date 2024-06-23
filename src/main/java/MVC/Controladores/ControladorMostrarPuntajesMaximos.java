package MVC.Controladores;

import Clases.Empleado;
import MVC.Modelo.Modelo;
import MVC.Vistas.VistaOrdenarTabladeEmpleados;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorMostrarPuntajesMaximos {
    private VistaOrdenarTabladeEmpleados vista;
    private Modelo modelo;

    public ControladorMostrarPuntajesMaximos(){
        vista = new VistaOrdenarTabladeEmpleados();
        modelo = new Modelo();
        iniciar();
    }
    public void iniciar() {
        vista.setVisible(true);

        vista.ordenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<Empleado> empleados = modelo.maximoPuntaje();
                    vista.mostrarPuntajesMaximos(empleados);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, "Error calculando la tabla de empleados");
                }
            }
        });
    }


}
