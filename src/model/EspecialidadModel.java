package model;

import dataBase.CRUD;
import dataBase.ConfigDB;
import entity.Especialidad;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadModel implements CRUD {

    public Object insert(Object obj){
        //1. Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir el obj que llego a especialidad
        Especialidad objEspecialidad=(Especialidad) obj;

        try{
            //3. Escribir SQL
            String sql = "INSERT INTO especialidad(nombre, descripcion) VALUES (?,?)";

            //4. Preparar el statement, ademas agregamos la propiedad RETURN GENERATE KEYS, que hace que la sentencia SQL retorno los id generado por la base de datos.
            PreparedStatement objPrepare= objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Asignar valor a los ?,?,?
            objPrepare.setString(1,objEspecialidad.getNombre());
            objPrepare.setString(2,objEspecialidad.getDescripcion());

            //6. Ejecutar el query
            objPrepare.execute();

            //7. Obtener el resultado con los id o llaves generadas
            ResultSet objRest= objPrepare.getGeneratedKeys();

            //8. Iterar mientras  haya un registro
            while (objRest.next()){
                objEspecialidad.setId_especialidad(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Especialdiad insertion was successful");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objEspecialidad;
    }

    @Override
    public List<Object> findAll() {
        //1. Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

         //2. Crear lista para guardar lo que nos devuelve la base de datos
        List<Object> listEspecialidad = new ArrayList<>();

        try{
            //3. Escribir el codigo SQL;
            String sql= "SELECT * FROM especialidad;";

            //4. Usar el prepareStatement
            PreparedStatement objprepare= objConnection.prepareStatement(sql);

            //5. Ejecutar el query y obtener el resultado(ResueltSet)

            ResultSet objResult= objprepare.executeQuery();

            //6. Mientras haya un resultado siguiente hacer:
            while(objResult.next()){
                //6.1 Crear un coder
                Especialidad objEspecialidad= new Especialidad();

                //6.2 Llenar el objero con la informacion de la base de datos
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));
                objEspecialidad.setId_especialidad(objResult.getInt("id_especialidad"));

                //6.3 Agregamos el coder a la lista
                listEspecialidad.add(objEspecialidad);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return listEspecialidad;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Especialidad objEspecialidad = (Especialidad) obj;
        boolean isUpdated = false;
        try {
            String sql = "UPDATE especialidad SET nombre = ?, descripcion = ? WHERE id_especialidad = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objEspecialidad.getNombre());
            objPrepare.setString(2,objEspecialidad.getDescripcion());
            objPrepare.setInt(3,objEspecialidad.getId_especialidad());

            int totalRowsAffected = objPrepare.executeUpdate();

            if (totalRowsAffected>0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"The update was successful");
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Especialidad objEspecialidad = (Especialidad)obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean idDelete = false;

        try {
            String sql = "DELETE FROM especialidad WHERE id_especialidad = ?; ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objEspecialidad.getId_especialidad());
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

    public Especialidad findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Especialidad objEspecialidad = null;

        try {
            String sql = "SELECT * FROM especialidad WHERE id_especialidad = ? ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);
            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objEspecialidad = new Especialidad();
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));
                objEspecialidad.setId_especialidad(objResult.getInt("id_especialidad"));
            }
        } catch (Exception var7) {
            JOptionPane.showMessageDialog((Component)null, var7.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objEspecialidad;
    }

    public ArrayList<Especialidad> findByNombre(String nombre) {
        ArrayList<Especialidad> listEspecialidad = new ArrayList();
        Connection objConnection = ConfigDB.openConnection();
        Especialidad objEspecialidad = null;

        try {
            String sql = "SELECT * FROM especialidad WHERE nombre LIKE ? ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + nombre + "%");
            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()) {
                objEspecialidad = new Especialidad();
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));
                objEspecialidad.setId_especialidad(objResult.getInt("id_especialidad"));
                listEspecialidad.add(objEspecialidad);
            }
        } catch (Exception var8) {
            JOptionPane.showMessageDialog((Component)null, var8.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return listEspecialidad;
    }

}

