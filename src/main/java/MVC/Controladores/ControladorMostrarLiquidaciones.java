package MVC.Controladores;

import MVC.Modelo.Modelo;
import MVC.Vistas.VistaMostrarLiquidaciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorMostrarLiquidaciones {
    private VistaMostrarLiquidaciones vista;
    private Modelo modelo;

    public ControladorMostrarLiquidaciones() {
        vista = new VistaMostrarLiquidaciones();
        modelo = new Modelo();
        iniciar();
    }

    public void iniciar() {
        vista.setVisible(true);

        vista.botonMostrarLiquidaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.mostrarBono(modelo.importeBruto(Integer.parseInt(((String) vista.comboBoxEmpleados.getSelectedItem()).split(",")[0]), (String) vista.comboBoxLiquidaciones.getSelectedItem()));
            }
        });
    }
}
