package Vista.GestionDeCursos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaGestionDeCursos extends JFrame {

    private JButton consultarCursantesButton;
    private JButton agregarCursoButton;
    private JButton modificarEliminarCursoButton;

    public VistaGestionDeCursos(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Si estas leyendo esto, dejame ver que hace, que estoy toqueteando las vistas
        setSize(1200,600);

        consultarCursantesButton = new JButton("Consultar Cursantes");
        agregarCursoButton = new JButton("Agregar Curso");
        modificarEliminarCursoButton = new JButton("Modificar/Eliminar Curso");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));
        panel.add(consultarCursantesButton);
        panel.add(agregarCursoButton);
        panel.add(modificarEliminarCursoButton);

        add(panel);
        setVisible(true);
    }

    public void agregarListenerConsultarConcursantes(ActionListener listener){
        consultarCursantesButton.addActionListener(listener);
    }
    public void agregarListenerAgregarCurso(ActionListener listener){
        agregarCursoButton.addActionListener(listener);
    }
    public void agregarListenerModificarEliminarCurso(ActionListener listener){
        modificarEliminarCursoButton.addActionListener(listener);
    }
}