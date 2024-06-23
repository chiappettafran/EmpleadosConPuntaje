package MVC.Vistas;

import Clases.Curso;
import Clases.Empleado;
import Clases.Inscripcion;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VistaOrdenarTabladeEmpleados extends JFrame {
    public JButton ordenarButton;
    private final Font fuenteBotones = new Font(Font.SANS_SERIF, Font.BOLD, 19);


    public VistaOrdenarTabladeEmpleados() {
        iniciar();
    }
    private void iniciar() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(1,1));

        panel.add(panelBotonOrdenar());


        this.getContentPane().add(panel);
        this.pack();
        setTitle("Puntajes Maximos");
        setLocationRelativeTo(null);
    }
    private JPanel panelBotonOrdenar(){
        JPanel panelOrdenar = new JPanel();
        panelOrdenar.setSize(50,50);
        panelOrdenar.setBorder(BorderFactory.createEmptyBorder(27,0,0,0));
        ordenarButton = new JButton("Ordenar");
        ordenarButton.setFont(fuenteBotones);

        panelOrdenar.add(ordenarButton);
        return panelOrdenar;
    }
    public void mostrarPuntajesMaximos(ArrayList<Empleado> empleados) {
        JFrame vistaOrdenarPuntajes = new JFrame();
        vistaOrdenarPuntajes.setTitle("Puntajes Máximos");
        vistaOrdenarPuntajes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Font fuente = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
        Font fuente2 = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        JPanel panel = new JPanel(new GridLayout(empleados.size() + 1, 3, 10, 10));

        JLabel legajo = new JLabel("Legajo");
        legajo.setFont(fuente2);
        legajo.setHorizontalAlignment(SwingConstants.CENTER);
        legajo.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(legajo);

        JLabel nombre = new JLabel("Nombre");
        nombre.setFont(fuente2);
        nombre.setHorizontalAlignment(SwingConstants.CENTER);
        nombre.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(nombre);

        JLabel puntaje = new JLabel("Puntaje");
        puntaje.setFont(fuente2);
        puntaje.setHorizontalAlignment(SwingConstants.CENTER);
        puntaje.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(puntaje);

        Inscripcion inscripcion = new Inscripcion();
        HashMap<Empleado, Double> empleadoPuntaje = new HashMap<>();
        for (Empleado empleado : empleados) {
            double puntajeDouble = 0;
            ArrayList<Integer> cursosAprobados = inscripcion.cursosAprobados(empleado.getLegajo());
            ArrayList<Integer> cursosNoAprobados = inscripcion.cursosNoAprobados(empleado.getLegajo());

            for (Integer codigoCurso : cursosAprobados) {
                puntajeDouble += Curso.extraerCurso(codigoCurso).getPuntaje() * 2;
            }
            for (Integer codigoCurso : cursosNoAprobados) {
                puntajeDouble += Curso.extraerCurso(codigoCurso).getPuntaje();
            }

            empleadoPuntaje.put(empleado, puntajeDouble);
        }

        List<Map.Entry<Empleado, Double>> entries = new ArrayList<>(empleadoPuntaje.entrySet());
        entries.sort(Map.Entry.<Empleado, Double>comparingByValue().reversed());

        for (Map.Entry<Empleado, Double> entry : entries) {
            Empleado empleado = entry.getKey();
            double puntajeDouble = entry.getValue();

            JLabel legajoLabel = new JLabel(String.valueOf(empleado.getLegajo()));
            legajoLabel.setFont(fuente);
            legajoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            legajoLabel.setVerticalAlignment(SwingConstants.CENTER);
            panel.add(legajoLabel);

            JLabel nombreLabel = new JLabel(empleado.getNombre());
            nombreLabel.setFont(fuente);
            nombreLabel.setHorizontalAlignment(SwingConstants.CENTER);
            nombreLabel.setVerticalAlignment(SwingConstants.CENTER);
            panel.add(nombreLabel);

            JLabel puntajeLabel = new JLabel(String.valueOf(puntajeDouble));
            puntajeLabel.setFont(fuente);
            puntajeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            puntajeLabel.setVerticalAlignment(SwingConstants.CENTER);
            panel.add(puntajeLabel);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        vistaOrdenarPuntajes.add(scrollPane);
        vistaOrdenarPuntajes.setSize(600, 400);
        vistaOrdenarPuntajes.setLocationRelativeTo(null);
        vistaOrdenarPuntajes.setVisible(true);
    }


}
