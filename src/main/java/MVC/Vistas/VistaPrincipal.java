package MVC.Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaPrincipal extends JFrame{

    private JButton verificarElegibilidadButton;
    private JButton tablaPuntajesButton;
    private JButton consultarSueldosButton;

    public VistaPrincipal(){
        super("Gestion de Empleados y Cursos :3");

        verificarElegibilidadButton = new JButton();
        tablaPuntajesButton = new JButton();
        consultarSueldosButton = new JButton();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(verificarElegibilidadButton);
        panel.add(tablaPuntajesButton);
        panel.add(consultarSueldosButton);

        add(panel);
        setVisible(true);

        agregarListenerVerfificarElegibilidad(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VistaVerificarEligibilidad();
            }
        });
        agregarListenerTablaPuntajes(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VistaOrdenarTabladeEmpleados();
            }
        });
    }

    public void agregarListenerVerfificarElegibilidad(ActionListener listener){
        verificarElegibilidadButton.addActionListener(listener);
    }
    public void agregarListenerTablaPuntajes(ActionListener listener){
        tablaPuntajesButton.addActionListener(listener);
    }
    public void agregarListenerConsultarSueldos(ActionListener listener){
        consultarSueldosButton.addActionListener(listener);
    }
}