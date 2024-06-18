package Vista.GestionDeCursos;

import javax.swing.*;
import java.awt.*;

public class VistaAgregarCurso extends JFrame {

    private JTextField nombreField;
    private JTextField cargaHorariaField;
    private JTextField puntosCursoField;
    private JTextField vigenciaAprobadoField;
    private JButton agregarButton;

    private JComboBox<CheckboxMenuItem> cursosPreviosCombobox;

    //ACA HABRIA UN METODO PARA QUE APAREZCAN TODOS LOS CURSOS DISPONIBLES
    //primero quiero que este lo basico de la base de datos para implementarlo bien

    public JTextField getNombreField() {
        return nombreField;
    }

    public JTextField getCargaHorariaField() {
        return cargaHorariaField;
    }

    public JTextField getPuntosCursoField() {
        return puntosCursoField;
    }

    public JTextField getVigenciaAprobadoField() {
        return vigenciaAprobadoField;
    }

    public JButton getAgregarButton() {
        return agregarButton;
    }

    public JComboBox<CheckboxMenuItem> getCursosPreviosCombobox() {
        return cursosPreviosCombobox;
    }

    public VistaAgregarCurso(){
        super("Agregar Curso");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200,600);

        nombreField = new JTextField();
        cargaHorariaField = new JTextField();
        puntosCursoField = new JTextField();
        vigenciaAprobadoField = new JTextField();
        cursosPreviosCombobox = new JComboBox<>();
        agregarButton = new JButton("Agregar");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,2));
        panel.add(new JLabel("Nombre: "));
        panel.add(nombreField);
        panel.add(new JLabel("Carga Horaria: "));
        panel.add(cargaHorariaField);
        panel.add(new JLabel("Puntos: "));
        panel.add(puntosCursoField);
        panel.add(new JLabel("Vigencia Aprobado: "));
        panel.add(vigenciaAprobadoField);
        panel.add(new JLabel("Curso Previous Combobox: "));
        panel.add(cursosPreviosCombobox);
        panel.add(new JLabel("yeet"));
        panel.add(agregarButton);

        add(panel);
        setVisible(true);
    }
}