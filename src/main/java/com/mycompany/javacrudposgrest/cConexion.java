/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javacrudposgrest;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Oscar CR
 */
public class cConexion {
    Connection conectar = null ;
    
    String usuario = "postgres";
    String contrasena = "admin";
    String bd= "bdEscuela";
    String ip = "localhost";
    String puerto = "5432";
    
    String cadena = "jdbc:postgresql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            conectar=DriverManager.getConnection(cadena,usuario,contrasena);
            
           // JOptionPane.showMessageDialog(null,"Se conecto correctamente a la base de datos:");
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"No se conecto correctamente a la base de datos:"+e.toString());
        }
        
        return conectar;
    }
}
