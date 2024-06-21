package Clases;

import ConexionBD.Conexion;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Sueldo {
    private int codigo;
    private int legajoEmpleado;
    private Date fechaLiquidacion;
    private double incentivoCapacitacion;
    private String periodoLiquidacion;
    private double importeBruto;


    public void liquidarSueldos () {
        Date fechaLiquidacion = new Date(2024-1900,5,1);
        if (periodoYaLiquidado(fechaLiquidacion)) {
            JOptionPane.showMessageDialog(null, "Este periodo ya fue liquidado.");
        } else {
            Date fechaLiquidacionAux = (Date) fechaLiquidacion.clone();
            fechaLiquidacionAux.setMonth(fechaLiquidacionAux.getMonth()-1);
            for (Integer legajoEmpleado : Empleado.listaLegajosActivos()) {
                Sueldo sueldo = new Sueldo();

                sueldo.legajoEmpleado = legajoEmpleado;
                sueldo.fechaLiquidacion = fechaLiquidacion;
                sueldo.incentivoCapacitacion = calcularIncentivo(legajoEmpleado, fechaLiquidacionAux);
                sueldo.periodoLiquidacion = periodoLiquidacion(fechaLiquidacion);
                sueldo.importeBruto = Empleado.extraerEmpleado(legajoEmpleado).getSueldoBase() + sueldo.incentivoCapacitacion;
                insertarBono(sueldo);
            }
            JOptionPane.showMessageDialog(null, "Sueldos correspondientes al periodo "+periodoLiquidacion(fechaLiquidacion)+" correctamente liquidados.");
        }
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

    private String periodoLiquidacion (Date fechaLiquidacion) {
        Date fechaLiquidacionAux = (Date) fechaLiquidacion.clone();
        fechaLiquidacionAux.setMonth(fechaLiquidacionAux.getMonth()-1);
        String fechaPeriodo = Conexion.serializarFecha(fechaLiquidacionAux);
        return fechaPeriodo.substring(0, fechaPeriodo.length()-2);
    }

    private boolean periodoYaLiquidado (Date fechaLiquidacion) {
        try {
            ResultSet resultSet = Conexion.getConexion().createStatement().executeQuery("SELECT codigo FROM SUELDOS WHERE periodo_liquidacion = '"+periodoLiquidacion(fechaLiquidacion)+"'");
            return resultSet.isBeforeFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
