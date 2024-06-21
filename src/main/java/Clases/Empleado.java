package Clases;

import ConexionBD.Conexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Empleado {
    private int legajo;
    private boolean permanente;
    private int dni;
    private String nombre;
    private String direccion;
    private String funcion;
    private int telefono;
    private Date fechaIngreso;
    private Date fechaVencimientoContrato;
    private double sueldoBase;


    public static ArrayList<Integer> listaLegajosActivos () {
        try {
            ResultSet resultSetLegajos = Conexion.getConexion().createStatement().executeQuery("SELECT legajo FROM EMPLEADOS");
            ArrayList<Integer> legajos = new ArrayList<>();
            while (resultSetLegajos.next()) {
                legajos.add(resultSetLegajos.getInt(1));
            }
            return legajos;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    public static boolean esPermanente(int legajo) {
        try {
            ResultSet resultSetEsPermanente = Conexion.getConexion().createStatement().executeQuery("SELECT permanente FROM EMPLEADOS WHERE legajo ="+legajo);
            resultSetEsPermanente.next();
            return resultSetEsPermanente.getBoolean(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Empleado extraerEmpleado(int legajoEmpleado) {
        try {
            ResultSet empleado = Conexion.getConexion().createStatement().executeQuery("SELECT * FROM empleados WHERE legajo = "+legajoEmpleado);
            empleado.next();
            Empleado empleadoNuevo = new Empleado();

            empleadoNuevo.legajo = empleado.getInt(1);
            empleadoNuevo.permanente = empleado.getBoolean(2);
            empleadoNuevo.dni = empleado.getInt(3);
            empleadoNuevo.direccion = empleado.getString(4);
            empleadoNuevo.funcion = empleado.getString(5);

            if (empleado.getString(6) != null) {
                empleadoNuevo.fechaVencimientoContrato = Conexion.deserializarFecha(empleado.getString(6));
            } else {
                empleadoNuevo.fechaVencimientoContrato = null;
            }

            empleadoNuevo.nombre = empleado.getString(7);
            empleadoNuevo.telefono = empleado.getInt(8);

            if (empleado.getString(9) != null) {
                empleadoNuevo.fechaIngreso = Conexion.deserializarFecha(empleado.getString(9));
            } else {
                empleadoNuevo.fechaIngreso = null;
            }

            empleadoNuevo.sueldoBase = empleado.getDouble(10);

            return empleadoNuevo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }









    public int getLegajo() {
        return legajo;
    }
    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public boolean isPermanente() {
        return permanente;
    }
    public void setPermanente(boolean permanente) {
        this.permanente = permanente;
    }

    public int getDni() {
        return dni;
    }
    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFuncion() {
        return funcion;
    }
    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public int getTelefono() {
        return telefono;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaVencimientoContrato() {
        return fechaVencimientoContrato;
    }
    public void setFechaVencimientoContrato(Date fechaVencimientoContrato) {
        this.fechaVencimientoContrato = fechaVencimientoContrato;
    }

    public double getSueldoBase() {
        return sueldoBase;
    }
    public void setSueldoBase(double sueldoBase) {
        this.sueldoBase = sueldoBase;
    }
}
