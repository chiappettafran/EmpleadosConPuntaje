package MVC.Controladores;

import MVC.Modelo.Modelo;
import MVC.Vistas.VistaPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                JOptionPane.showMessageDialog(null, "holaaaa");
            }
        });

        vista.tablaPuntajesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        vista.verificarElegibilidadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        vista.consultarSueldosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        vista.liquidarSueldosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelo.liquidarSueldos();
            }
        });
    }


}
