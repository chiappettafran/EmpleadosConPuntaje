package Clases;

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
    private int fechaIngreso;
    private Date fechaVencimientoContrato;
    private double sueldoBase;

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

    public int getFechaIngreso() {
        return fechaIngreso;
    }
    public void setFechaIngreso(int fechaIngreso) {
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



    public boolean puedeRealizar(Curso curso) {
        return false;
    }

    public int[] maximoPuntaje(){
        return null;
    }

    public double importeBruto(Empleado empleado) {
        //aca es donde calcula los incentivos
        return 0D;
    }
}
