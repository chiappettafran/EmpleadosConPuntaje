package MVC.Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaVerificarEligibilidad extends JFrame {

    private JComboBox<String> seleccionEmpleadoCombobox;
    private JComboBox<String> cursosComboBox;
    private JButton consultarButton;


    public VistaVerificarEligibilidad() {
        super("Verificar Elijibilidad");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,600);

        String[] empleados = {};
        String[] cursos = {};
        seleccionEmpleadoCombobox = new JComboBox<>(empleados);
        cursosComboBox = new JComboBox<>(cursos);
        consultarButton = new JButton("Consultar");

        JPanel leftpanel = new JPanel();
        leftpanel.setLayout(new GridLayout(3, 1));
        leftpanel.add(seleccionEmpleadoCombobox);
        leftpanel.add(cursosComboBox);
        leftpanel.add(consultarButton);

        add(leftpanel, BorderLayout.WEST);
        add(new JOptionPane(), BorderLayout.CENTER);

        setVisible(true);
    }

    public void agregarListenerSeleccionEmpleado(ActionListener listener){
        seleccionEmpleadoCombobox.addActionListener(listener);
    }
    public void agregarListenerCurso(ActionListener listener){
        cursosComboBox.addActionListener(listener);
    }
    public void agregarListenerConsultar(ActionListener listener){
        consultarButton.addActionListener(listener);
    }
}
