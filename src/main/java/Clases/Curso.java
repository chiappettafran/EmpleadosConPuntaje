package Clases;

import ConexionBD.Conexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Curso {
    private int codigo;
    private String nombreCurso;
    private int cargaHoraria;
    private double puntaje;
    private int vigenciaMeses;
    private int[] cursosPrevios;


    public static Curso extraerCurso(int codigoCurso) {
        try {
            ResultSet curso = Conexion.getConexion().createStatement().executeQuery("SELECT * FROM cursos WHERE codigo = "+codigoCurso);
            curso.next();

            Curso cursoNuevo = new Curso();
            cursoNuevo.codigo = curso.getInt(1);
            cursoNuevo.nombreCurso = curso.getString(2);
            cursoNuevo.cargaHoraria = curso.getInt(3);
            cursoNuevo.puntaje = curso.getDouble(6);
            cursoNuevo.vigenciaMeses = curso.getInt(4);

            if (curso.getString(5) != null) {
                String[] auxCursosPrevios = curso.getString(5).split(",");
                cursoNuevo.cursosPrevios = new int[auxCursosPrevios.length];
                for (int i = 0; i < auxCursosPrevios.length; i++) {
                    cursoNuevo.cursosPrevios[i] = Integer.parseInt(auxCursosPrevios[i]);
                }
            } else {
                cursoNuevo.cursosPrevios = null;
            }
            return cursoNuevo;
        } catch (SQLException e) {
            System.out.println("Error leyendo el curso 1");
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<Integer> extraerTodosCursos() {
        ArrayList<Integer> codigoCursos = new ArrayList<>();
        try {
            String sql = "SELECT codigo FROM cursos;";
            ResultSet resultSet = Conexion.getConexion().createStatement().executeQuery(sql);
            while (resultSet.next()){
                int codigoCurso = resultSet.getInt("codigo");
                codigoCursos.add(codigoCurso);
            }
        }catch (SQLException e){
            System.out.println("Error leyendo el curso 2");
            throw new RuntimeException(e);
        }
        return codigoCursos;
    }


    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }
    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public double getPuntaje() {
        return puntaje;
    }
    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }

    public int getVigenciaMeses() {
        return vigenciaMeses;
    }
    public void setVigenciaMeses(int vigenciaMeses) {
        this.vigenciaMeses = vigenciaMeses;
    }

    public int[] getCursosPrevios() {
        return cursosPrevios;
    }
    public void setCursosPrevios(int[] cursosPrevios) {
        this.cursosPrevios = cursosPrevios;
    }
}
