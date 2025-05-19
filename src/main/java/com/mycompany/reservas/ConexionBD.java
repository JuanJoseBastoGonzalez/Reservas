/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reservas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // Datos de conexión directos (para proyecto universitario)
    private static final String HOST = "brgjatanyoknvhwlamcf-mysql.services.clever-cloud.com";
    private static final String DB_NAME = "brgjatanyoknvhwlamcf";
    private static final String USER = "utihyfsxz2nm10bq";
    private static final String PASSWORD = "qnEdQdBbv7wEQlMP9AfF";
    private static final String PORT = "3306";
    
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME + 
                                   "?useSSL=false&allowPublicKeyRetrieval=true";

    public static Connection obtenerConexion() {
        try {
            // Registrar el driver (opcional en JDBC 4.0+)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establecer conexión
            Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("¡Conexión exitosa a Clever Cloud!");
            return conexion;
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver JDBC de MySQL");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos:");
            System.err.println("URL: " + URL);
            System.err.println("Usuario: " + USER);
            System.err.println("Mensaje de error: " + e.getMessage());
            return null;
        }
    }

    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada correctamente");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}