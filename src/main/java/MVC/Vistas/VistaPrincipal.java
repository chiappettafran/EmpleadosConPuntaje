package MVC.Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaPrincipal extends JFrame{

    public JButton agregarEmpleadosButton;
    public JButton verificarElegibilidadButton;
    public JButton tablaPuntajesButton;
    public JButton consultarSueldosButton;
    public JButton liquidarSueldosButton;

    private final Font fuenteBotones = new Font(Font.SANS_SERIF, Font.BOLD, 15);

    public VistaPrincipal(){
        super("Gestion de Empleados y Cursos");
        crearVentana();
    }

    private void crearVentana() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panelVentana = new JPanel(new GridLayout(2, 1));

        panelVentana.add(panelSuperiorBotones());
        panelVentana.add(panelInferiorBotones());

        this.getContentPane().add(panelVentana);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private JPanel panelSuperiorBotones() {
        JPanel panelSuperior = new JPanel(new GridLayout(1,3,30,5));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(40,40,15,40));

        agregarEmpleadosButton = new JButton("Agregar Empleado");
        agregarEmpleadosButton.setFont(fuenteBotones);
        tablaPuntajesButton = new JButton("Maximos Puntajes");
        tablaPuntajesButton.setFont(fuenteBotones);
        verificarElegibilidadButton = new JButton("Verificar Eligibilidad");
        verificarElegibilidadButton.setFont(fuenteBotones);

        panelSuperior.add(agregarEmpleadosButton);
        panelSuperior.add(tablaPuntajesButton);
        panelSuperior.add(verificarElegibilidadButton);

        return panelSuperior;
    }

    private JPanel panelInferiorBotones() {
        JPanel panelInferior = new JPanel(new GridLayout(1,2,50,5));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(15,150,40,150));

        consultarSueldosButton = new JButton("Consultar Bonos");
        consultarSueldosButton.setFont(fuenteBotones);
        liquidarSueldosButton = new JButton("Liquidar Sueldos");
        liquidarSueldosButton.setFont(fuenteBotones);

        panelInferior.add(consultarSueldosButton);
        panelInferior.add(liquidarSueldosButton);

        return panelInferior;
    }

    public static void main(String[] args) {
        new VistaPrincipal().setVisible(true);
    }
}