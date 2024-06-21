package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static final Connection conexion = setConexion();

    private static Connection setConexion() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:./src/main/java/BaseDeDatos/BaseObjetosSA", "ObjetosSA", "serializable");
            System.out.println("Conexión exitosa!");
            return connection;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static Connection getConexion() {
        return conexion;
    }


}
