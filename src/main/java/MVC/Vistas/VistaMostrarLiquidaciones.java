package MVC.Vistas;

import Clases.Empleado;
import Clases.Sueldo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VistaMostrarLiquidaciones extends JFrame {
    public JComboBox comboBoxEmpleados;
    public JComboBox comboBoxLiquidaciones;
    public JButton botonMostrarLiquidaciones;
    private final Font fuenteBotones = new Font(Font.SANS_SERIF, Font.BOLD, 19);
    private final Font fuentetextos = new Font(Font.SANS_SERIF, Font.BOLD, 14);


    public VistaMostrarLiquidaciones() {
        iniciar();
    }

    private void iniciar() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new GridLayout(2,1));

        panelPrincipal.add(panelComboBoxes());
        panelPrincipal.add(panelBoton());

        this.getContentPane().add(panelPrincipal);
        this.pack();
        setTitle("Consultar Bonos");
        setLocationRelativeTo(null);
    }

    private JPanel panelComboBoxes() {
        JPanel panelComboBoxes = new JPanel(new GridLayout(2,2,20,20));
        panelComboBoxes.setBorder(BorderFactory.createEmptyBorder(25,20,20,20));
        comboBoxEmpleados = new JComboBox<>(opcionesComboBoxEmpleados());
        comboBoxLiquidaciones = new JComboBox<>();

        comboBoxEmpleados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int legajo = Integer.parseInt(((String) comboBoxEmpleados.getSelectedItem()).split(",")[0]);
                comboBoxLiquidaciones.removeAllItems();
                for (String periodo : Sueldo.getPeriodosDeLiquidacionLegajo(legajo)) {
                    comboBoxLiquidaciones.addItem(periodo);
                }
            }
        });

        JLabel jLabelEmpleados = new JLabel("Empleado:");
        jLabelEmpleados.setFont(fuentetextos);
        panelComboBoxes.add(jLabelEmpleados);
        panelComboBoxes.add(comboBoxEmpleados);
        JLabel jLabelLiquidaciones = new JLabel("Periodo Liquidacion: ");
        jLabelLiquidaciones.setFont(fuentetextos);
        panelComboBoxes.add(jLabelLiquidaciones);
        panelComboBoxes.add(comboBoxLiquidaciones);

        return panelComboBoxes;

    }
    private JPanel panelBoton() {
        JPanel panelBoton = new JPanel();
        panelBoton.setSize(50,50);
        panelBoton.setBorder(BorderFactory.createEmptyBorder(27,0,0,0));
        botonMostrarLiquidaciones = new JButton("Consultar");
        botonMostrarLiquidaciones.setFont(fuenteBotones);

        panelBoton.add(botonMostrarLiquidaciones);
        return panelBoton;

    }

    private String[] opcionesComboBoxEmpleados() {
        ArrayList<Integer> legajos = Empleado.listaLegajosActivos();
        String[] opcionesCombo = new String[legajos.size()];

        int contador = 0;
        for (Integer legajo : legajos) {
            opcionesCombo[contador] = legajo+", "+Empleado.extraerEmpleado(legajo).getNombre();
            contador++;
        }

        return opcionesCombo;
    }

    public void mostrarBono(Sueldo sueldo) {
        JFrame vistaBono = new JFrame();
        vistaBono.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        vistaBono.setTitle("Bono de Sueldo");

        JPanel panelPrincipal = new JPanel(new GridLayout(2,1,10,10));
        panelPrincipal.add(datosBono(sueldo));
        panelPrincipal.add(panelCalculoBono(sueldo));




        vistaBono.add(panelPrincipal);
        vistaBono.pack();
        vistaBono.setLocationRelativeTo(null);
        vistaBono.setVisible(true);
    }

    public JPanel datosBono(Sueldo sueldo) {
        JPanel panelDatosBono = new JPanel(new GridLayout(2,3,10,10));
        panelDatosBono.setBorder(BorderFactory.createEmptyBorder(70,20,70,20));

        Font fuente = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
        Font fuente2 = new Font(Font.SANS_SERIF, Font.BOLD, 15);

        JLabel jLabel1 = new JLabel("DNI");
        jLabel1.setFont(fuente);
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setVerticalAlignment(SwingConstants.CENTER);

        JLabel jLabel2 = new JLabel("Nombre");
        jLabel2.setFont(fuente);
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setVerticalAlignment(SwingConstants.CENTER);

        JLabel jLabel3 = new JLabel("Periodo");
        jLabel3.setFont(fuente);
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setVerticalAlignment(SwingConstants.CENTER);

        JLabel dni = new JLabel(String.valueOf(Empleado.extraerEmpleado(sueldo.getLegajoEmpleado()).getDni()));
        dni.setFont(fuente2);
        dni.setHorizontalAlignment(SwingConstants.CENTER);
        dni.setVerticalAlignment(SwingConstants.CENTER);

        JLabel nombre = new JLabel(Empleado.extraerEmpleado(sueldo.getLegajoEmpleado()).getNombre());
        nombre.setFont(fuente2);
        nombre.setHorizontalAlignment(SwingConstants.CENTER);
        nombre.setVerticalAlignment(SwingConstants.CENTER);

        JLabel periodo = new JLabel(sueldo.getPeriodoLiquidacion());
        periodo.setFont(fuente2);
        periodo.setHorizontalAlignment(SwingConstants.CENTER);
        periodo.setVerticalAlignment(SwingConstants.CENTER);

        panelDatosBono.add(jLabel1);
        panelDatosBono.add(jLabel2);
        panelDatosBono.add(jLabel3);
        panelDatosBono.add(dni);
        panelDatosBono.add(nombre);
        panelDatosBono.add(periodo);

        return panelDatosBono;
    }

    public JPanel panelCalculoBono(Sueldo sueldo) {
        JPanel panelCalculoBono = new JPanel(new GridLayout(6,2,10,10));
        panelCalculoBono.setBorder(BorderFactory.createEmptyBorder(0,30,20,0));

        Font fuente = new Font(Font.SANS_SERIF, Font.PLAIN, 13);
        Font fuente2 = new Font(Font.SANS_SERIF, Font.BOLD, 13);
        Font fuente3 = new Font(Font.SANS_SERIF, Font.BOLD, 13);

        JLabel jLabelNombres = new JLabel();
        jLabelNombres.setFont(fuente);
        jLabelNombres.setText("Sueldo Base");
        JLabel jLabelDatos = new JLabel();
        jLabelDatos.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelDatos.setVerticalAlignment(SwingConstants.CENTER);
        jLabelDatos.setFont(fuente2);
        jLabelDatos.setForeground(Color.GREEN);
        jLabelDatos.setText(String.valueOf(sueldo.getSueldoBase()));
        panelCalculoBono.add(jLabelNombres);
        panelCalculoBono.add(jLabelDatos);

        JLabel jLabelNombres2 = new JLabel();
        jLabelNombres2.setFont(fuente);
        jLabelNombres2.setText("Incentivo Capacitaciones");
        JLabel jLabelDatos3 = new JLabel();
        jLabelDatos3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelDatos3.setVerticalAlignment(SwingConstants.CENTER);
        jLabelDatos3.setFont(fuente2);
        jLabelDatos3.setForeground(Color.GREEN);
        jLabelDatos3.setText(String.valueOf(sueldo.getIncentivoCapacitacion()));
        panelCalculoBono.add(jLabelNombres2);
        panelCalculoBono.add(jLabelDatos3);

        JLabel jLabelNombres3 = new JLabel();
        jLabelNombres3.setFont(fuente);
        jLabelNombres3.setText("Jubilacion (11%)");
        JLabel jLabelDatos2 = new JLabel();
        jLabelDatos2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelDatos2.setVerticalAlignment(SwingConstants.CENTER);
        jLabelDatos2.setFont(fuente3);
        jLabelDatos2.setForeground(Color.RED);
        jLabelDatos2.setText(String.valueOf(sueldo.getImporteBruto()*0.11));
        panelCalculoBono.add(jLabelNombres3);
        panelCalculoBono.add(jLabelDatos2);

        JLabel jLabelNombres4 = new JLabel();
        jLabelNombres4.setFont(fuente);
        jLabelNombres4.setText("Obra Social (3%)");
        JLabel jLabelDatos4 = new JLabel();
        jLabelDatos4.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelDatos4.setVerticalAlignment(SwingConstants.CENTER);
        jLabelDatos4.setFont(fuente3);
        jLabelDatos4.setForeground(Color.RED);
        jLabelDatos4.setText(String.valueOf(sueldo.getImporteBruto()*0.03));
        panelCalculoBono.add(jLabelNombres4);
        panelCalculoBono.add(jLabelDatos4);

        JLabel jLabelNombres5 = new JLabel();
        jLabelNombres5.setFont(fuente);
        jLabelNombres5.setText("Ley 19032 (3%)");
        JLabel jLabelDatos5 = new JLabel();
        jLabelDatos5.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelDatos5.setVerticalAlignment(SwingConstants.CENTER);
        jLabelDatos5.setFont(fuente3);
        jLabelDatos5.setForeground(Color.RED);
        jLabelDatos5.setText(String.valueOf(sueldo.getImporteBruto()*0.03));
        panelCalculoBono.add(jLabelNombres5);
        panelCalculoBono.add(jLabelDatos5);

        JLabel importeNeto = new JLabel();
        importeNeto.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));

        importeNeto.setText("Importe Neto");
        panelCalculoBono.add(importeNeto);

        JLabel importeNeto2 = new JLabel();
        importeNeto2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        importeNeto2.setText(String.valueOf(sueldo.getImporteBruto()-((sueldo.getImporteBruto()*0.11)+(sueldo.getImporteBruto()*0.03)+(sueldo.getImporteBruto()*0.03))));
        importeNeto2.setHorizontalAlignment(SwingConstants.CENTER);
        importeNeto2.setVerticalAlignment(SwingConstants.CENTER);
        panelCalculoBono.add(importeNeto2);

        return panelCalculoBono;
    }
}
