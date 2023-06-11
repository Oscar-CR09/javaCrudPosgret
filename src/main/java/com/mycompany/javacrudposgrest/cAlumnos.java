/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javacrudposgrest;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Oscar CR
 */
public class cAlumnos {
    
    int codigo;
    String nombreAlumnos;
    String apellidosAlumnos;
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreAlumnos() {
        return nombreAlumnos;
    }

    public void setNombreAlumnos(String nombreAlumnos) {
        this.nombreAlumnos = nombreAlumnos;
    }

    public String getApellidosAlumnos() {
        return apellidosAlumnos;
    }

    public void setApellidosAlumnos(String apellidosAlumnos) {
        this.apellidosAlumnos = apellidosAlumnos;
    }
    
    public  void MostrarAlumnos (JTable paramTablaTotalAlumnos){
        cConexion objeConexion = new cConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql="";
        
        modelo.addColumn("id");
        modelo.addColumn("nombre");
        modelo.addColumn("apellidos");
        
        paramTablaTotalAlumnos.setModel(modelo);
        
        sql = "select *from alumnos;";
        
        String [] datos = new String[3];
        Statement st;
        
        try {
            st= objeConexion.estableConnection().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                
                datos[0]= rs.getString(1);
                datos[1]= rs.getString(2);
                datos[2]= rs.getString(3);
                
                modelo.addRow(datos);
                
                
            }
            
            paramTablaTotalAlumnos.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error"+e.toString());
            
            
        }
    }
    
    public void insertarAlumnos(JTextField paramNombres,JTextField paramApellidos){
        
        setNombreAlumnos(paramNombres.getText());
        setApellidosAlumnos(paramApellidos.getText());
        
        cConexion objConexion =new cConexion();
        
        String consulta = "insert into alumnos (nombres,apellidos) values (?, ?);";
        
        try {
            CallableStatement cs =objConexion.estableConnection().prepareCall(consulta);
            cs.setString(1, getNombreAlumnos());
            cs.setString(2, getApellidosAlumnos());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se inserto correctamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:"+e.toString());
            
            
        }
    }
    
    public void seleccionarAlumno(JTable paramTablaAlumnos,JTextField paramCodigo,JTextField paramNombres,JTextField paramApellidos ){
        try {
            
            int fila = paramTablaAlumnos.getSelectedRow();
            if (fila>=0) {
                paramCodigo.setText(paramTablaAlumnos.getValueAt(fila,0).toString());
                paramNombres.setText(paramTablaAlumnos.getValueAt(fila,1).toString());
                paramApellidos.setText(paramTablaAlumnos.getValueAt(fila,2).toString());
                
            }else{
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
                
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:"+e.toString());
        }
    }
    
      public void modificarAlumnos(JTextField paramCodigo,JTextField paramNombres,JTextField paramApellidos){
        
         
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNombreAlumnos(paramNombres.getText());
        setApellidosAlumnos(paramApellidos.getText());
        
        cConexion objConexion =new cConexion();
        
        String consulta = "update alumnos set nombres = ?,apellidos =? where alumnos.id=?;";
        
        try {
            CallableStatement cs =objConexion.estableConnection().prepareCall(consulta);
            
            cs.setString(1, getNombreAlumnos());
            cs.setString(2, getApellidosAlumnos());
            cs.setInt(3, getCodigo());
            
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se modifico correctamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:"+e.toString());
            
            
        }
    }

      
       public void eliminarAlumnos(JTextField paramCodigo){
        
         
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        
        cConexion objConexion =new cConexion();
        
        String consulta = "delete from alumnos where alumnos.id=?;";
        
        try {
            CallableStatement cs =objConexion.estableConnection().prepareCall(consulta);
            
            cs.setInt(1, getCodigo());
            
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se Elimino correctamente  correctamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:"+e.toString());
            
            
        }
    }

}
