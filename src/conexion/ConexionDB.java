package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    // Datos de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/venta_vehiculos"; // Cambia el puerto y nombre de la base de datos si es necesario
    private static final String USER = "root"; // Usuario de MySQL
    private static final String PASSWORD = "Miclave.1"; // Contraseña de MySQL

    private static Connection conexion;

    // Método para conectar a la base de datos
    public static Connection getConexion() {
        try {
            // Si la conexión es nula o está cerrada, se crea una nueva conexión
            if (conexion == null || conexion.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conexion;
    }

    // Método que cierra la conexión a la base de datos
    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;
                System.out.println("Conexión cerrada exitosamente");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
