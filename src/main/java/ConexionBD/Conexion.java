package ConexionBD;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;

public class Conexion {
    private static final Connection conexion = setConexion();

    private static Connection setConexion() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:./src/main/java/BaseDeDatos/BaseObjetosSA",
                    "ObjetosSA", "serializable");
            System.out.println("Conexión exitosa!");
            return connection;
        } catch (Exception e) {
            int seleccion = JOptionPane.showConfirmDialog(null,
                    "Se produjo un error al conectar con la base de datos. Cierre la consola de administración. Reintentar?",
                    "Error de conexion", JOptionPane.OK_CANCEL_OPTION);

            switch (seleccion) {
                case 0:
                    return setConexion();
                case 2:
                    System.exit(0);
                    return null;
                default:
                    return null;
            }
        }
    }
    public static Connection getConexion() {
        return conexion;
    }

    public static String serializarFecha(Date date) {
        if (date.getMonth() < 9) {
            return "" + (date.getYear() + 1900) + "-0" + (date.getMonth() + 1) + "-" + date.getDate();
        } else {
            return (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate();
        }
    }
    public static Date deserializarFecha(String fecha) {
        String[] fechaArray = fecha.split("-");
        return new Date(Integer.parseInt(fechaArray[0]) - 1900, Integer.parseInt(fechaArray[1]) - 1, Integer.parseInt(fechaArray[2]));
    }
}
