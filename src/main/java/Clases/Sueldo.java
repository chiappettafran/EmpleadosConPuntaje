package Clases;

import ConexionBD.Conexion;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Sueldo {
    private int codigo;
    private int legajoEmpleado;
    private Date fechaLiquidacion;
    private double sueldoBase;
    private double incentivoCapacitacion;
    private String periodoLiquidacion;
    private double importeBruto;



    public void liquidarSueldos () {
        Date fechaLiquidacion = new Date();
        if (periodoYaLiquidado(fechaLiquidacion)) {
            JOptionPane.showMessageDialog(null, "Este periodo ya fue liquidado.");
        } else {
            Date fechaLiquidacionAux = (Date) fechaLiquidacion.clone();
            fechaLiquidacionAux.setMonth(fechaLiquidacionAux.getMonth()-1);

            Date fechaLiquidacionAux2 = (Date) fechaLiquidacion.clone();
            fechaLiquidacionAux2.setDate(1);
            for (Integer legajoEmpleado : Empleado.listaLegajosActivos()) {
                if (Empleado.extraerEmpleado(legajoEmpleado).getFechaIngreso().before(fechaLiquidacionAux2)) {
                    Sueldo sueldo = new Sueldo();

                    sueldo.legajoEmpleado = legajoEmpleado;
                    sueldo.fechaLiquidacion = fechaLiquidacion;
                    sueldo.incentivoCapacitacion = calcularIncentivo(legajoEmpleado, fechaLiquidacionAux);
                    sueldo.periodoLiquidacion = periodoLiquidacion(fechaLiquidacion);
                    sueldo.importeBruto = Empleado.extraerEmpleado(legajoEmpleado).getSueldoBase() + sueldo.incentivoCapacitacion;
                    insertarBono(sueldo);
                }
            }
            JOptionPane.showMessageDialog(null, "Sueldos correspondientes al periodo "+periodoLiquidacion(fechaLiquidacion)+" liquidados con exito.");
        }
    }
    public static ArrayList<String> getPeriodosDeLiquidacionLegajo(int legajo) {
        try {
            ResultSet periodosLiquidacionResultSet = Conexion.getConexion().createStatement().executeQuery("SELECT periodo_liquidacion FROM sueldos WHERE legajo_empleado ="+legajo);
            ArrayList<String> periodosLiquidacion = new ArrayList<>();
            while (periodosLiquidacionResultSet.next()) {
                periodosLiquidacion.add(periodosLiquidacionResultSet.getString(1));
            }
            return periodosLiquidacion;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Sueldo extraerBono (int legajo, String periodo) {
        try {
            Sueldo bonoDeSueldo = new Sueldo();
            ResultSet bonoSueldoResultSet = Conexion.getConexion().createStatement().executeQuery("SELECT * FROM sueldos WHERE legajo_empleado = "+legajo+" AND periodo_liquidacion = '"+periodo+"'");
            bonoSueldoResultSet.next();
            bonoDeSueldo.codigo = bonoSueldoResultSet.getInt(1);
            bonoDeSueldo.legajoEmpleado = bonoSueldoResultSet.getInt(2);
            bonoDeSueldo.fechaLiquidacion = Conexion.deserializarFecha(bonoSueldoResultSet.getString(3));
            bonoDeSueldo.sueldoBase = bonoSueldoResultSet.getDouble(4);
            bonoDeSueldo.incentivoCapacitacion = bonoSueldoResultSet.getDouble(5);
            bonoDeSueldo.periodoLiquidacion = bonoSueldoResultSet.getString(6);
            bonoDeSueldo.importeBruto = bonoSueldoResultSet.getDouble(7);

            return bonoDeSueldo;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public static String periodoLiquidacion (Date fechaLiquidacion) {
        Date fechaLiquidacionAux = (Date) fechaLiquidacion.clone();
        fechaLiquidacionAux.setMonth(fechaLiquidacionAux.getMonth()-1);
        String fechaPeriodo = Conexion.serializarFecha(fechaLiquidacionAux);
        return fechaPeriodo.substring(0, fechaPeriodo.length()-3);
    }
    private void insertarBono (Sueldo bono) {
        try {
            Conexion.getConexion().createStatement().execute("INSERT INTO sueldos (legajo_empleado, fecha_liquidacion, sueldo_base, incentivos_capacitaciones, periodo_liquidacion, importe_bruto) " +
                    "VALUES ("+bono.legajoEmpleado+" , '"+Conexion.serializarFecha(bono.fechaLiquidacion)+"', "+(bono.importeBruto - bono.incentivoCapacitacion)+", "+bono.incentivoCapacitacion+", '"+bono.periodoLiquidacion+"', "+bono.importeBruto+")");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    private double calcularIncentivo(Integer legajoEmpleado, Date fechaLiquidacion) {
        double incentivo = 0;

        if (!Empleado.esPermanente(legajoEmpleado)) {
            return 0;
        } else {

            ArrayList<Inscripcion> cursosIniciados = Inscripcion.cursosIniciadosEnPeriodo(legajoEmpleado, fechaLiquidacion);
            ArrayList<Inscripcion> cursosFinalizados = Inscripcion.cursosFinalizadosEnPeriodo(legajoEmpleado, fechaLiquidacion);

            if (cursosIniciados != null) {
                for (Inscripcion cursoIniciado : cursosIniciados) {
                    incentivo += Empleado.extraerEmpleado(legajoEmpleado).getSueldoBase() * Curso.extraerCurso(cursoIniciado.getCodigoMateria()).getPuntaje();
                }
            }

            if (cursosFinalizados != null) {
                for (Inscripcion cursoFinalizado : cursosFinalizados) {
                    incentivo += Empleado.extraerEmpleado(legajoEmpleado).getSueldoBase() * Curso.extraerCurso(cursoFinalizado.getCodigoMateria()).getPuntaje();
                }
            }
        }

        return incentivo;
    }
    private boolean periodoYaLiquidado (Date fechaLiquidacion) {
        try {
            ResultSet resultSet = Conexion.getConexion().createStatement().executeQuery("SELECT codigo FROM SUELDOS WHERE periodo_liquidacion = '"+periodoLiquidacion(fechaLiquidacion)+"'");
            return resultSet.isBeforeFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public Date getFechaLiquidacion() {
        return fechaLiquidacion;
    }
    public void setFechaLiquidacion(Date fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    public double getSueldoBase() {
        return sueldoBase;
    }
    public void setSueldoBase(double sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public double getIncentivoCapacitacion() {
        return incentivoCapacitacion;
    }
    public void setIncentivoCapacitacion(double incentivoCapacitacion) {
        this.incentivoCapacitacion = incentivoCapacitacion;
    }

    public String getPeriodoLiquidacion() {
        return periodoLiquidacion;
    }
    public void setPeriodoLiquidacion(String periodoLiquidacion) {
        this.periodoLiquidacion = periodoLiquidacion;
    }

    public double getImporteBruto() {
        return importeBruto;
    }
    public void setImporteBruto(double importeBruto) {
        this.importeBruto = importeBruto;
    }
}
