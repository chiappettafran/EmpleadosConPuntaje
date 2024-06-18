package MVC.Vistas;

import javax.swing.*;
import java.awt.*;

public class VistaOrdenarTabladeEmpleados extends JFrame {

    private JTable tblEmpleados;

    public VistaOrdenarTabladeEmpleados() {
        super("Puntaje de Empleados");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,600);

        String[] nombreColumnas = {"Legajo", "Nombre", "Puntoje"};
        Object[][] datos = {};

        tblEmpleados = new JTable(datos, nombreColumnas);

        add(new JScrollPane(tblEmpleados), BorderLayout.CENTER);

        setVisible(true);
    }

    public JTable getTblEmpleados() {
        return tblEmpleados;
    }
}
