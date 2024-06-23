package MVC.Controladores;

import Clases.Sueldo;
import ConexionBD.Conexion;
import MVC.Modelo.Modelo;
import MVC.Vistas.VistaPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ControladorVistaPrincipal {
    private VistaPrincipal vista;
    private Modelo modelo;

    public ControladorVistaPrincipal() {
        this.vista = new VistaPrincipal();
        this.modelo = new Modelo();
    }

    public void iniciar() {
        vista.setVisible(true);

        vista.agregarEmpleadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ControladorAgregarEmpleados();
            }
        });

        vista.tablaPuntajesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ControladorMostrarPuntajesMaximos();
            }
        });

        vista.verificarElegibilidadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ControladorMostrarElegibilidad();
            }
        });

        vista.consultarSueldosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ControladorMostrarLiquidaciones();
            }
        });

        vista.liquidarSueldosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(null, "Desea liquidar los sueldos correspondientes al periodo "+ Sueldo.periodoLiquidacion(new Date())+"?", "",JOptionPane.OK_CANCEL_OPTION);
                if (opcion == 0) {
                    modelo.liquidarSueldos();
                }
            }
        });
    }


}
