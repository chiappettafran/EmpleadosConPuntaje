package Clases;

import ConexionBD.Conexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Inscripcion {
    private int codigo;
    private int legajoEmpleado;
    private int codigoMateria;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean aprobado;

    public static ArrayList<Inscripcion> cursosIniciadosEnPeriodo (int legajoEmpleado, Date fechaInicioPeriodo) {
        Date fechaaux = (Date) fechaInicioPeriodo.clone();
        fechaaux.setMonth(fechaaux.getMonth()+1);
        try {
            ResultSet cursosIniciados = Conexion.getConexion().createStatement().executeQuery("SELECT * FROM inscripciones WHERE legajo_empleado ="+legajoEmpleado+" AND fecha_inicio >= '"+Conexion.serializarFecha(fechaInicioPeriodo)+"' AND fecha_inicio < '"+Conexion.serializarFecha(fechaaux)+"'");

            if(cursosIniciados.isBeforeFirst()) {
                ArrayList<Inscripcion> inscripcionArrayList = new ArrayList<>();
                while (cursosIniciados.next()) {
                    Inscripcion inscripcion = new Inscripcion();
                    inscripcion.codigo = cursosIniciados.getInt(1);
                    inscripcion.legajoEmpleado = cursosIniciados.getInt(2);
                    inscripcion.codigoMateria = cursosIniciados.getInt(3);
                    inscripcion.fechaInicio = Conexion.deserializarFecha(cursosIniciados.getString(4));
                    if (cursosIniciados.getString(5) != null) {
                        inscripcion.fechaFin = Conexion.deserializarFecha(cursosIniciados.getString(5));
                    }
                    inscripcion.aprobado = cursosIniciados.getBoolean(6);
                    inscripcionArrayList.add(inscripcion);
                }
                return inscripcionArrayList;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<Inscripcion> cursosFinalizadosEnPeriodo (int legajoEmpleado, Date fechaInicioPeriodo) {
        Date fechaaux = (Date) fechaInicioPeriodo.clone();
        fechaaux.setMonth(fechaaux.getMonth()+1);
        try {
            ResultSet cursosFinalizados = Conexion.getConexion().createStatement().executeQuery("SELECT * FROM inscripciones WHERE legajo_empleado ="+legajoEmpleado+" AND fecha_fin >= '"+Conexion.serializarFecha(fechaInicioPeriodo)+"' AND fecha_fin < '"+Conexion.serializarFecha(fechaaux)+"'");

            if(cursosFinalizados.isBeforeFirst()) {
                ArrayList<Inscripcion> inscripcionArrayList = new ArrayList<>();
                while (cursosFinalizados.next()) {
                    Inscripcion inscripcion = new Inscripcion();
                    inscripcion.codigo = cursosFinalizados.getInt(1);
                    inscripcion.legajoEmpleado = cursosFinalizados.getInt(2);
                    inscripcion.codigoMateria = cursosFinalizados.getInt(3);
                    inscripcion.fechaInicio = Conexion.deserializarFecha(cursosFinalizados.getString(4));
                    inscripcion.fechaFin = Conexion.deserializarFecha(cursosFinalizados.getString(5));
                    inscripcion.aprobado = cursosFinalizados.getBoolean(6);
                    inscripcionArrayList.add(inscripcion);
                }
                return inscripcionArrayList;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<Integer> legajosCursadores() {
        ArrayList<Integer> legajoCursadores = new ArrayList<>();
        try {
            ResultSet legajosConCursos = Conexion.getConexion().createStatement().executeQuery("SELECT * FROM inscripciones");
            while (legajosConCursos.next()) {
                int legajo = Integer.parseInt(legajosConCursos.getString(2));
                if (!legajoCursadores.contains(legajo)) {
                    legajoCursadores.add(legajo);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error leyendo Inscripciones");
            throw new RuntimeException(e);
        }
        return legajoCursadores;
    }

    public static ArrayList<Integer> cursosAprobados(Integer legajo) {
        ArrayList<Integer> listadoCursosAprobados = new ArrayList<>();
        try {
            ResultSet cursosAprobadosBD = Conexion.getConexion().createStatement().executeQuery(
                    "SELECT * FROM inscripciones WHERE legajo_empleado = " + legajo + " AND aprobado = TRUE"
            );
            while (cursosAprobadosBD.next()) {
                listadoCursosAprobados.add(cursosAprobadosBD.getInt("codigo_materia"));
            }
        } catch (SQLException e) {
            System.out.println("Error leyendo Cursos Aprobados");
            throw new RuntimeException(e);
        }
        return listadoCursosAprobados;
    }

    public static ArrayList<Integer> cursosNoAprobados(Integer legajo) {
        ArrayList<Integer> listadoCursosNoAprobados = new ArrayList<>();
        try {
            ResultSet cursosDesaprobadosBD = Conexion.getConexion().createStatement().executeQuery(
                    "SELECT * FROM inscripciones WHERE legajo_empleado = " + legajo + " AND aprobado = FALSE"
            );
            while (cursosDesaprobadosBD.next()) {
                listadoCursosNoAprobados.add(cursosDesaprobadosBD.getInt("codigo_materia"));
            }
        } catch (SQLException e) {
            System.out.println("Error leyendo Cursos No Aprobados");
            throw new RuntimeException(e);
        }
        return listadoCursosNoAprobados;
    }




    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getLegajoEmpleado() {
        return legajoEmpleado;
    }
    public void setLegajoEmpleado(int legajoEmpleado) {
        this.legajoEmpleado = legajoEmpleado;
    }

    public int getCodigoMateria() {
        return codigoMateria;
    }
    public void setCodigoMateria(int codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isAprobado() {
        return aprobado;
    }
    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }
}
