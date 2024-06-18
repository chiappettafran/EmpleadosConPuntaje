package Vista.GestionDeCursos;

import javax.swing.*;
import java.awt.*;

public class VistaModificarEliminarCurso extends JFrame {

    private JComboBox<String> cursosComboBox;
    private JButton consultarButton;
    private JTextField nombreField;
    private JTextField cargaHorariaField;
    private JTextField puntosField;
    private JTextField vigenciaField;
    private JButton eliminarButton;
    private JButton guardarButton;

    public JComboBox<String> getCursosComboBox() {
        return cursosComboBox;
    }

    public JButton getConsultarButton() {
        return consultarButton;
    }

    public JTextField getNombreField() {
        return nombreField;
    }

    public JTextField getCargaHorariaField() {
        return cargaHorariaField;
    }

    public JTextField getPuntosField() {
        return puntosField;
    }

    public JTextField getVigenciaField() {
        return vigenciaField;
    }

    public VistaModificarEliminarCurso(){
        super("Modificar/Eliminar Curso");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 600);

        String[] cursos = {};

        cursosComboBox = new JComboBox<>(cursos);
        consultarButton = new JButton("Consultar");
        nombreField = new JTextField();
        cargaHorariaField = new JTextField();
        puntosField = new JTextField();
        vigenciaField = new JTextField();
        eliminarButton = new JButton("Eliminar Curso");
        guardarButton = new JButton("Guardar Cambios");

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 2));
        topPanel.add(new JLabel("Seleccionar Curso"));
        topPanel.add(cursosComboBox);
        topPanel.add(new JLabel(""));
        topPanel.add(consultarButton);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(5, 2));
        middlePanel.add(new JLabel("Nombre: "));
        middlePanel.add(nombreField);
        middlePanel.add(new JLabel("Carga Horaria: "));
        middlePanel.add(cargaHorariaField);
        middlePanel.add(new JLabel("Puntos: "));
        middlePanel.add(puntosField);
        middlePanel.add(new JLabel("Vigencia para Aprobado: "));
        middlePanel.add(vigenciaField);
        middlePanel.add(new JLabel(""));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));
        bottomPanel.add(eliminarButton);
        bottomPanel.add(guardarButton);

        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);


    }

}
