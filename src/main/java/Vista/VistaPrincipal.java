package Vista;

import Vista.GestionDeCursos.VistaGestionDeCursos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaPrincipal extends JFrame{

    private JButton gestiondDeCursosButton;
    private JButton gestionDeEmpleadosButton;
    private JButton liquidacionDeSueldosButton;

    public VistaPrincipal(){
        super("Gestion de Empleados y Cursos :3");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,600);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(gestiondDeCursosButton);
        panel.add(gestionDeEmpleadosButton);
        panel.add(liquidacionDeSueldosButton);

        add(panel);
        setVisible(true);

        agregarListenerGestionCursos(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VistaGestionDeCursos();
            }
        });
    }

    public void agregarListenerGestionCursos(ActionListener listener){
        gestiondDeCursosButton.addActionListener(listener);
    }
    public void agregarListenerGestionEmpleados(ActionListener listener){
        gestionDeEmpleadosButton.addActionListener(listener);
    }
    public void agregarListenerLiquidacionSueldos(ActionListener listener){
        liquidacionDeSueldosButton.addActionListener(listener);
    }
}