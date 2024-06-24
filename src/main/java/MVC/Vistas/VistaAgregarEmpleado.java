package MVC.Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class VistaAgregarEmpleado extends JFrame {
    public JTextField jTextFieldDni;
    public JTextField jTextFieldNombre;
    public JTextField jTextFieldDireccion;
    public JTextField jTextFieldFuncion;
    public JTextField jTextFieldtelefono;
    public JTextField jTextFieldFechaVencimientoContrato;
    public JTextField jTextFieldSueldoBase;
    public JComboBox jComboBoxTipoContrato;
    public JButton botonAgregarEmpleado;

    public VistaAgregarEmpleado() {
        iniciar();
    }

    public void iniciar() {
        setTitle("Agregar Empleado");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.add(panelAtributosEmpleado());

        botonAgregarEmpleado = new JButton("Agregar Empleado");
        panelPrincipal.add(botonAgregarEmpleado);


        add(panelPrincipal);
        pack();
        setLocationRelativeTo(null);
    }

    public JPanel panelAtributosEmpleado() {
        JPanel panelAtributosEmpleado = new JPanel(new GridLayout(8, 2,30,10));
        panelAtributosEmpleado.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));


        panelAtributosEmpleado.add(new JLabel("DNI*:"));
        jTextFieldDni = new JTextField();
        panelAtributosEmpleado.add(jTextFieldDni);

        panelAtributosEmpleado.add(new JLabel("Nombre*:"));
        jTextFieldNombre = new JTextField();
        panelAtributosEmpleado.add(jTextFieldNombre);

        panelAtributosEmpleado.add(new JLabel("Direccion:"));
        jTextFieldDireccion = new JTextField();
        panelAtributosEmpleado.add(jTextFieldDireccion);

        panelAtributosEmpleado.add(new JLabel("Telefono de contacto*:"));
        jTextFieldtelefono = new JTextField();
        panelAtributosEmpleado.add(jTextFieldtelefono);

        panelAtributosEmpleado.add(new JLabel("Funcion*:"));
        jTextFieldFuncion = new JTextField();
        panelAtributosEmpleado.add(jTextFieldFuncion);


        jTextFieldFechaVencimientoContrato = new JTextField();
        panelAtributosEmpleado.add(new JLabel("Tipo Contrato:"));
        jComboBoxTipoContrato = new JComboBox(new String[]{"Permanente", "Contrato Definido"});
        panelAtributosEmpleado.add(jComboBoxTipoContrato);
        jComboBoxTipoContrato.setSelectedItem("Permanente");
        jTextFieldFechaVencimientoContrato.setEditable(false);

        jComboBoxTipoContrato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcionComboBox = String.valueOf(jComboBoxTipoContrato.getSelectedItem());
                jTextFieldFechaVencimientoContrato.setEditable(opcionComboBox.equals("Contrato Definido"));
            }
        });

        panelAtributosEmpleado.add(new JLabel("Fecha Vencimiento Contrato:"));
        jTextFieldFechaVencimientoContrato.setToolTipText("AAAA-MM-DD");
        panelAtributosEmpleado.add(jTextFieldFechaVencimientoContrato);

        panelAtributosEmpleado.add(new JLabel("Sueldo Base*:"));
        jTextFieldSueldoBase = new JTextField();
        panelAtributosEmpleado.add(jTextFieldSueldoBase);


        return panelAtributosEmpleado;
    }
}
