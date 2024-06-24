package MVC.Vistas;

import Clases.Curso;
import Clases.Empleado;
import Clases.Inscripcion;
import ConexionBD.Conexion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class VistaVerificarEligibilidad extends JFrame {

    public JComboBox empleadosComboBox;
    public JComboBox cursosComboBox;
    public JButton consultarButton;
    private final Font fuenteBotones = new Font(Font.SANS_SERIF, Font.BOLD, 19);
    private final Font fuentetextos = new Font(Font.SANS_SERIF, Font.BOLD, 14);

    public VistaVerificarEligibilidad(){
        iniciar();
    }
    private void iniciar() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new GridLayout(2, 1));

        panelPrincipal.add(panelComboBoxesElijibilidad());
        panelPrincipal.add(panelBotonConsultar());

        this.getContentPane().add(panelPrincipal);
        this.pack();
        setTitle("Verificar Eligibilidad");
        setLocationRelativeTo(null);

    }

    private JPanel panelComboBoxesElijibilidad(){
        JPanel panelComboBoxes = new JPanel(new GridLayout(2,2,20,20));
        panelComboBoxes.setBorder(BorderFactory.createEmptyBorder(25,20,20,20));
        empleadosComboBox = new JComboBox<>(opcionesEmpleadosComboBox());
        cursosComboBox = new JComboBox<>();

        empleadosComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cursosComboBox.removeAllItems();
                ArrayList<Integer> codigosCursos = Curso.extraerTodosCursos();
                for (Integer codigo : codigosCursos) {
                    String stringCurso = codigo + "-" + Curso.extraerCurso(codigo).getNombreCurso();
                    cursosComboBox.addItem(stringCurso);
                }
            }
        });

        JLabel jLabelEmpleados = new JLabel("Empleados");
        jLabelEmpleados.setFont(fuentetextos);
        panelComboBoxes.add(jLabelEmpleados);
        panelComboBoxes.add(empleadosComboBox);
        JLabel jLabelCursos = new JLabel("Cursos");
        jLabelCursos.setFont(fuentetextos);
        panelComboBoxes.add(jLabelCursos);
        panelComboBoxes.add(cursosComboBox);

        return panelComboBoxes;
    }
    private String[] opcionesEmpleadosComboBox(){
        ArrayList<Integer> legajos = Empleado.listaLegajosActivos();
        String[] opciones = new String[legajos.size()];

        int contador = 0;
        for (Integer legajo : legajos) {
            opciones[contador] = legajo+", "+Empleado.extraerEmpleado(legajo).getNombre();
            contador++;
        }

        return opciones;
    }
    private JPanel panelBotonConsultar(){
        JPanel panelConsultar = new JPanel();
        panelConsultar.setSize(50,50);
        panelConsultar.setBorder(BorderFactory.createEmptyBorder(27,0,0,0));
        consultarButton = new JButton("Consultar");
        consultarButton.setFont(fuenteBotones);

        panelConsultar.add(consultarButton);
        return panelConsultar;
    }
    public void mostrarCodicionParaCurso(Empleado empleado, Curso curso){
        JFrame vistaCondicionParaCurso = new JFrame();
        vistaCondicionParaCurso.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        vistaCondicionParaCurso.setTitle("Codicion para Cursar");
        if(empleado.isPermanente()){
            JPanel panelCondicionesParaCursos = new JPanel(new GridLayout(2,1,10,10));
            panelCondicionesParaCursos.add(datosConsulta(empleado, curso));
            panelCondicionesParaCursos.add(panelDatosCorrelatividades(empleado, curso));

            vistaCondicionParaCurso.add(panelCondicionesParaCursos);

        }else {
            Font fuente2 = new Font(Font.SANS_SERIF, Font.BOLD, 15);
            JPanel panelCondicionesParaCursos = new JPanel();
            JLabel jlabel1 = new JLabel("NO ES PLANTA PERMANENTE");
            jlabel1.setFont(fuente2);
            jlabel1.setHorizontalAlignment(SwingConstants.CENTER);
            jlabel1.setVerticalAlignment(SwingConstants.CENTER);
            panelCondicionesParaCursos.add(jlabel1);
            vistaCondicionParaCurso.add(panelCondicionesParaCursos);
        }
        vistaCondicionParaCurso.pack();
        vistaCondicionParaCurso.setLocationRelativeTo(null);
        vistaCondicionParaCurso.setVisible(true);
    }
    public JPanel datosConsulta(Empleado empleado, Curso curso){
        JPanel panelConsultar = new JPanel(new GridLayout(2, 3, 10,10));
        panelConsultar.setBorder(BorderFactory.createEmptyBorder(70,20,70,20));

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

        JLabel jLabel3 = new JLabel("Curso");
        jLabel3.setFont(fuente);
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setVerticalAlignment(SwingConstants.CENTER);

        JLabel dni = new JLabel(String.valueOf(empleado.getDni()));
        dni.setFont(fuente2);
        dni.setHorizontalAlignment(SwingConstants.CENTER);
        dni.setVerticalAlignment(SwingConstants.CENTER);

        JLabel nombre = new JLabel(empleado.getNombre());
        nombre.setFont(fuente2);
        nombre.setHorizontalAlignment(SwingConstants.CENTER);
        nombre.setVerticalAlignment(SwingConstants.CENTER);

        JLabel curso1 = new JLabel(curso.getCodigo() + " - " + curso.getNombreCurso());
        curso1.setFont(fuente2);
        curso1.setHorizontalAlignment(SwingConstants.CENTER);
        curso1.setVerticalAlignment(SwingConstants.CENTER);

        panelConsultar.add(jLabel1);
        panelConsultar.add(jLabel2);
        panelConsultar.add(jLabel3);
        panelConsultar.add(dni);
        panelConsultar.add(nombre);
        panelConsultar.add(curso1);

        return panelConsultar;
    }

    public JPanel panelDatosCorrelatividades(Empleado empleado, Curso curso) {
        Inscripcion inscripcion = new Inscripcion();
        ArrayList<Integer> listadoCursosAprobados = inscripcion.cursosAprobados(empleado.getLegajo());
        int[] cursosPrevios = curso.getCursosPrevios();
        JPanel panelDatosCorrelatividades = new JPanel();
        panelDatosCorrelatividades.setBorder(BorderFactory.createEmptyBorder(70, 20, 70, 20));
        Font fuente = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
        Font fuente2 = new Font(Font.SANS_SERIF, Font.BOLD, 15);

        if (cursosPrevios != null) {
            panelDatosCorrelatividades.setLayout(new GridLayout(cursosPrevios.length + 1, 3, 10, 10));
            JLabel jLabel1 = new JLabel("Codigo");
            jLabel1.setFont(fuente);
            jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel1.setVerticalAlignment(SwingConstants.CENTER);

            JLabel jLabel2 = new JLabel("Curso");
            jLabel2.setFont(fuente);
            jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel2.setVerticalAlignment(SwingConstants.CENTER);

            JLabel jLabel3 = new JLabel("Condicion");
            jLabel3.setFont(fuente);
            jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel3.setVerticalAlignment(SwingConstants.CENTER);

            panelDatosCorrelatividades.add(jLabel1);
            panelDatosCorrelatividades.add(jLabel2);
            panelDatosCorrelatividades.add(jLabel3);

            for (int i = 0; i < cursosPrevios.length; i++) {
                JLabel codigo = new JLabel(String.valueOf(cursosPrevios[i]));
                codigo.setFont(fuente2);
                codigo.setHorizontalAlignment(SwingConstants.CENTER);
                codigo.setVerticalAlignment(SwingConstants.CENTER);

                panelDatosCorrelatividades.add(codigo);

                JLabel curso1 = new JLabel(Curso.extraerCurso(cursosPrevios[i]).getNombreCurso());
                curso1.setFont(fuente2);
                curso1.setHorizontalAlignment(SwingConstants.CENTER);
                curso1.setVerticalAlignment(SwingConstants.CENTER);

                panelDatosCorrelatividades.add(curso1);

                if (listadoCursosAprobados.contains(cursosPrevios[i])) {
                    Date fechaFin = inscripcion.getFechaFinCurso(empleado.getLegajo(), cursosPrevios[i]);
                    int vigenciaMeses = Curso.extraerCurso(cursosPrevios[i]).getVigenciaMeses();
                    LocalDate fechaActual = LocalDate.now();

                    LocalDate fechaFinLocalDate = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate fechaExpiracion = fechaFinLocalDate.plusMonths(vigenciaMeses);

                    if (fechaActual.isBefore(fechaExpiracion)) {
                        JLabel aprobado = new JLabel("Curso Aprobado y Vigente!!");
                        aprobado.setFont(fuente2);
                        aprobado.setHorizontalAlignment(SwingConstants.CENTER);
                        aprobado.setVerticalAlignment(SwingConstants.CENTER);
                        aprobado.setForeground(Color.GREEN);

                        panelDatosCorrelatividades.add(aprobado);
                    } else {
                        JLabel aprobado = new JLabel("Curso Aprobado pero No Vigente!!");
                        aprobado.setFont(fuente2);
                        aprobado.setHorizontalAlignment(SwingConstants.CENTER);
                        aprobado.setVerticalAlignment(SwingConstants.CENTER);
                        aprobado.setForeground(Color.ORANGE);

                        panelDatosCorrelatividades.add(aprobado);
                    }
                } else {
                    JLabel aprobado = new JLabel("Curso No Aprobado!!");
                    aprobado.setFont(fuente2);
                    aprobado.setHorizontalAlignment(SwingConstants.CENTER);
                    aprobado.setVerticalAlignment(SwingConstants.CENTER);
                    aprobado.setForeground(Color.RED);

                    panelDatosCorrelatividades.add(aprobado);
                }
            }
        } else {
            JLabel jLabel = new JLabel("ESTA MATERIA NO TIENE CURSOS PREVIOS!");
            jLabel.setFont(fuente2);
            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel.setVerticalAlignment(SwingConstants.CENTER);
            panelDatosCorrelatividades.add(jLabel);
        }
        return panelDatosCorrelatividades;
    }


}