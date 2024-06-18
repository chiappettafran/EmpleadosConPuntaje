package Vista.GestionDeCursos;

import javax.swing.*;
import java.awt.*;

public class VistaConsultarConcursantes extends JFrame {

    private JComboBox<String> cursosComboBox;
    private JComboBox<String> estadoCursadoComboBox;
    private JButton consultarButton;
    private JTable  resultadosTabla;

    public VistaConsultarConcursantes(){
        super("Consultar Cursantes");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200,600);

        String[] cursos = {};//Aca se agregarian los cursos
        String[] estados = {"Aprovado", "Cursando Actualmente", "Aprovado Vencido", "Todos"};

        cursosComboBox = new JComboBox<>(cursos);
        estadoCursadoComboBox = new JComboBox<>(estados);
        consultarButton = new JButton("Consultar");

        String[] nombreColumnas = {"Legajo", "Nombre", "Fecha de Inicio", "Fecha Fin"};
        Object[][] datos = {};
        resultadosTabla = new JTable(datos, nombreColumnas);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(3, 1));
        leftPanel.add(cursosComboBox);
        leftPanel.add(estadoCursadoComboBox);
        leftPanel.add(consultarButton);

        add(leftPanel, BorderLayout.WEST);
        add(new JScrollPane(resultadosTabla), BorderLayout.CENTER);

        setVisible(true);
    }

    public JComboBox<String> getCursosComboBox() {
        return cursosComboBox;
    }

    public JComboBox<String> getEstadoCursadoComboBox() {
        return estadoCursadoComboBox;
    }

    public JButton getConsultarButton() {
        return consultarButton;
    }

    public JTable getResultadosTabla() {
        return resultadosTabla;
    }
}
