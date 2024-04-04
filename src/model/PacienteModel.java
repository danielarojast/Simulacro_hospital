package model;

import dataBase.CRUD;
import dataBase.ConfigDB;
import entity.Especialidad;
import entity.Paciente;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteModel implements CRUD {
    @Override
    public Object insert(Object obj) {

            //1. Abrir la conexion
            Connection objConnection = ConfigDB.openConnection();

            //2. Convertir el obj que llego a especialidad
            Paciente objPaciente=(Paciente) obj;

            try{
                //3. Escribir SQL
                String sql = "INSERT INTO paciente(nombre, apellido, fecha_nacimiento, documiento_identidad) VALUES (?,?,?,?)";

                //4. Preparar el statement, ademas agregamos la propiedad RETURN GENERATE KEYS, que hace que la sentencia SQL retorno los id generado por la base de datos.
                PreparedStatement objPrepare= objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

                //5. Asignar valor a los ?,?,?
                objPrepare.setString(1,objPaciente.getNombre());
                objPrepare.setString(2,objPaciente.getApellido());
                objPrepare.setString(3,objPaciente.getFecha_naciento());
                objPrepare.setString(4,objPaciente.getDocumento_identidad());

                //6. Ejecutar el query
                objPrepare.execute();

                //7. Obtener el resultado con los id o llaves generadas
                ResultSet objRest= objPrepare.getGeneratedKeys();

                //8. Iterar mientras  haya un registro
                while (objRest.next()){
                    objPaciente.setId_paciente(objRest.getInt(1));
                }

                JOptionPane.showMessageDialog(null, "Paciente insertion was successful");
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null,e.getMessage());
            }finally {
                ConfigDB.closeConnection();
            }

            return objPaciente;
    }

    @Override
    public List<Object> findAll() {

            //1. Abrir la conexion
            Connection objConnection = ConfigDB.openConnection();

            //2. Crear lista para guardar lo que nos devuelve la base de datos
            List<Object> listPaciente = new ArrayList<>();

            try{
                //3. Escribir el codigo SQL;
                String sql= "SELECT * FROM paciente;";

                //4. Usar el prepareStatement
                PreparedStatement objprepare= objConnection.prepareStatement(sql);

                //5. Ejecutar el query y obtener el resultado(ResueltSet)

                ResultSet objResult= objprepare.executeQuery();

                //6. Mientras haya un resultado siguiente hacer:
                while(objResult.next()){
                    //6.1 Crear un coder
                    Paciente objPaciente= new Paciente();

                    //6.2 Llenar el objeto con la informacion de la base de datos
                    objPaciente.setNombre(objResult.getString("nombre"));
                    objPaciente.setApellido(objResult.getString("apellido"));
                    objPaciente.setFecha_naciento(objResult.getString("fecha_nacimiento"));
                    objPaciente.setDocumento_identidad(objResult.getString("documiento_identidad"));
                    objPaciente.setId_paciente(objResult.getInt("id_paciente"));

                    //6.3 Agregamos el coder a la lista
                    listPaciente.add(objPaciente);
                }

            }catch (SQLException error){
                JOptionPane.showMessageDialog(null, error.getMessage());
            }finally {
                ConfigDB.closeConnection();
            }

            return listPaciente;
    }
    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = (Paciente) obj;
        boolean idUpdated = false;

        try {
            String sql = "UPDATE paciente SET nombre = ?, apellido = ?, fecha_nacimiento= ?, documiento_identidad= ? WHERE id_paciente = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objPaciente.getNombre());
            objPrepare.setString(2,objPaciente.getApellido());
            objPrepare.setString(3,objPaciente.getFecha_naciento());
            objPrepare.setString(4,objPaciente.getDocumento_identidad());
            objPrepare.setInt(5,objPaciente.getId_paciente());


            int totalRowsAffected = objPrepare.executeUpdate();

            if (totalRowsAffected>0){
                idUpdated = true;
                JOptionPane.showMessageDialog(null,"The update was successful");
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return idUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Paciente objPaciente = (Paciente)obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean idDelete = false;

        try {
            String sql = "DELETE FROM paciente WHERE id_paciente = ?; ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objPaciente.getId_paciente());
            int totalAfectedRows = objPrepare.executeUpdate();
            if (totalAfectedRows > 0) {
                idDelete = true;
                JOptionPane.showMessageDialog((Component)null, "the update was successful.");
            }
        } catch (Exception var8) {
            JOptionPane.showMessageDialog((Component)null, var8.getMessage());
        }

        ConfigDB.closeConnection();
        return idDelete;
    }

    public Paciente findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = null;

        try {
            String sql = "SELECT * FROM paciente WHERE id_paciente = ? ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);
            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objPaciente = new Paciente();
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellido(objResult.getString("apellido"));
                objPaciente.setFecha_naciento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documiento_identidad"));
                objPaciente.setId_paciente(objResult.getInt("id_paciente"));

            }
        } catch (Exception var7) {
            JOptionPane.showMessageDialog((Component) null, var7.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objPaciente;
    }

    public ArrayList<Paciente> findByNombre(String nombre) {
        ArrayList<Paciente> listPaciente = new ArrayList();
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = null;

        try {
            String sql = "SELECT * FROM Paciente WHERE nombre LIKE ? ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + nombre + "%");
            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()) {
                objPaciente = new Paciente();
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellido(objResult.getString("apellido"));
                objPaciente.setFecha_naciento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documiento_identidad"));
                objPaciente.setId_paciente(objResult.getInt("id_paciente"));

                listPaciente.add(objPaciente);
            }
        } catch (Exception var8) {
            JOptionPane.showMessageDialog((Component)null, var8.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return listPaciente;
    }
}