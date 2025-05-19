/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reservas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestConexion {
    public static void main(String[] args) throws SQLException {
        // 1. Probar conexión
        Connection conn = ConexionBD.obtenerConexion();
        
        if (conn != null) {
            try {
                // 2. Crear tabla de prueba si no existe
                crearTablaPrueba(conn);
                
                // 3. Insertar datos de ejemplo
                insertarUsuarioEjemplo(conn);
                
                // 4. Consultar datos
                List<String> usuarios = listarUsuarios(conn);
                System.out.println("\nUsuarios registrados:");
                usuarios.forEach(System.out::println);
                
            } finally {
                ConexionBD.cerrarConexion(conn);
            }
        }
    }
    
    private static void crearTablaPrueba(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS prueba_conexion (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nombre VARCHAR(50), " +
                    "fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
        
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabla de prueba verificada/creada");
        }
    }
    
    private static void insertarUsuarioEjemplo(Connection conn) throws SQLException {
        String sql = "INSERT INTO prueba_conexion (nombre) VALUES (?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "Estudiante Universitario");
            int filas = pstmt.executeUpdate();
            System.out.println(filas + " fila(s) insertada(s)");
        }
    }
    
    private static List<String> listarUsuarios(Connection conn) throws SQLException {
        List<String> usuarios = new ArrayList<>();
        String sql = "SELECT id, nombre, fecha_creacion FROM prueba_conexion";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String usuario = String.format("ID: %d, Nombre: %s, Fecha: %s",
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getTimestamp("fecha_creacion"));
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
}