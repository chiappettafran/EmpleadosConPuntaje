package MVC.Controladores;

import MVC.Vistas.VistaPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVistaPrincipal {
    private VistaPrincipal vista;

    public ControladorVistaPrincipal(VistaPrincipal vista) {
        this.vista = new VistaPrincipal();
    }

    public void iniciar() {
        vista.setVisible(true);

        vista.agregarEmpleadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "holaaaa");
            }
        });
        /*vista.tablaPuntajesButton.addActionListener();
        vista.verificarElegibilidadButton.addActionListener();
        vista.consultarSueldosButton.addActionListener();
        vista.liquidarSueldosButton.addActionListener();*/
    }


}
