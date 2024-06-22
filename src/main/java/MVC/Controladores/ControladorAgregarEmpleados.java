package MVC.Controladores;

import Clases.Empleado;
import ConexionBD.Conexion;
import Excepciones.DatoObligatorioNullException;
import Excepciones.FechaVencimientoMenorFechaIngresoException;
import Excepciones.FormatoFechaIncorrectoException;
import MVC.Modelo.Modelo;
import MVC.Vistas.VistaAgregarEmpleado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ControladorAgregarEmpleados {
    private Modelo modelo;
    private VistaAgregarEmpleado vista;

    public ControladorAgregarEmpleados() {
        modelo = new Modelo();
        vista = new VistaAgregarEmpleado();
        iniciar();
    }

    private void iniciar(){
        vista.setVisible(true);
        vista.botonAgregarEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Date fechaVencimiento = null;
                    if (vista.jTextFieldDni.getText().isBlank() || vista.jTextFieldNombre.getText().isBlank() || vista.jTextFieldFuncion.getText().isBlank() || vista.jTextFieldSueldoBase.getText().isBlank()) {
                        throw new DatoObligatorioNullException();
                    } else if (!vista.jTextFieldFechaVencimientoContrato.getText().isBlank()) {
                        try {
                            fechaVencimiento = Conexion.deserializarFecha(vista.jTextFieldFechaVencimientoContrato.getText());
                            if (fechaVencimiento.before(new Date())) {
                                throw new FechaVencimientoMenorFechaIngresoException();
                            }
                        } catch (FechaVencimientoMenorFechaIngresoException FVMFIE) {
                            JOptionPane.showMessageDialog(null, FVMFIE.getMessage());
                        } catch (Exception aux) {
                            throw new FormatoFechaIncorrectoException();
                        }
                    }

                    boolean isPermanente;
                    isPermanente = vista.jComboBoxTipoContrato.getSelectedIndex() == 0;

                    modelo.agregarEmpleado(new Empleado(isPermanente, Integer.parseInt(vista.jTextFieldDni.getText()),
                            vista.jTextFieldNombre.getText(), vista.jTextFieldDireccion.getText(), vista.jTextFieldFuncion.getText(),
                            Long.parseLong(vista.jTextFieldtelefono.getText()), new Date(), fechaVencimiento, Double.parseDouble(vista.jTextFieldSueldoBase.getText())));

                    JOptionPane.showMessageDialog(null, "Empleado añadido con éxito");

                } catch (DatoObligatorioNullException dOnE) {
                    JOptionPane.showMessageDialog(null,dOnE.getMessage());
                } catch (FormatoFechaIncorrectoException FFIE) {
                    JOptionPane.showMessageDialog(null, FFIE.getMessage());
                } catch (Exception exc) {
                    System.out.println(exc.getMessage());
                    JOptionPane.showMessageDialog(null, "ERROR, Revise los datos ingresados.");

                }
            }
        });
    }
}
