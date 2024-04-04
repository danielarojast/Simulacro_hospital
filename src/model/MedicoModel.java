package model;

import dataBase.CRUD;
import dataBase.ConfigDB;
import entity.Especialidad;
import entity.Medico;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        //1. Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir el obj que llego a especialidad
        Medico objMedico=(Medico) obj;

        try{
            //3. Escribir SQL
            String sql = "INSERT INTO medico(id_especialidad, nombre, apellido) VALUES (?,?,?)";

            //4. Preparar el statement, ademas agregamos la propiedad RETURN GENERATE KEYS, que hace que la sentencia SQL retorno los id generado por la base de datos.
            PreparedStatement objPrepare= objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Asignar valor a los ?,?,?
            objPrepare.setInt(1, objMedico.getFk_medico_especialidad());
            objPrepare.setString(2,objMedico.getNombre());
            objPrepare.setString(3,objMedico.getApellido());

            //6. Ejecutar el query
            objPrepare.execute();

            //7. Obtener el resultado con los id o llaves generadas
            ResultSet objRest= objPrepare.getGeneratedKeys();

            //8. Iterar mientras  haya un registro
            while (objRest.next()){
                objMedico.setId_medico(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Medico insertion was successful");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objMedico;
    }

    @Override
    public List<Object> findAll() {
        //1. Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear lista para guardar lo que nos devuelve la base de datos
        List<Object> listMedico = new ArrayList<>();

        try{
            //3. Escribir el codigo SQL;
            String sql= "SELECT * FROM medico INNER JOIN especialidad ON especialidad.id_especialidad = medico.id_especialidad;";

            //4. Usar el prepareStatement
            PreparedStatement objprepare= objConnection.prepareStatement(sql);

            //5. Ejecutar el query y obtener el resultado(ResueltSet)

            ResultSet objResult= objprepare.executeQuery();

            //6. Mientras haya un resultado siguiente hacer:
            while(objResult.next()){
                //6.1 Crear un objmedico
                Medico objMedico= new Medico();
                Especialidad objEspe = new Especialidad();

                //6.2 Llenar el objeto con la informacion de la base de datos de medico
                objMedico.setNombre(objResult.getString("medico.nombre"));
                objMedico.setApellido(objResult.getString("medico.apellido"));
                objMedico.setFk_medico_especialidad(objResult.getInt("medico.id_especialidad"));
                objMedico.setId_medico(objResult.getInt("medico.id_medico"));

                //6.3 Llenar el objeto con la informacion de la base de datos de especialidad
                objEspe.setId_especialidad(objResult.getInt("especialidad.id_especialidad"));
                objEspe.setNombre(objResult.getString("especialidad.nombre"));
                objEspe.setDescripcion(objResult.getString("especialidad.descripcion"));

                objMedico.setEspecialidad(objEspe);
                //6.3 Agregamos el coder a la lista
                listMedico.add(objMedico);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return listMedico;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Medico objMedico = (Medico)obj;
        boolean isUpdated = false;
        try {
            String sql = "UPDATE medico SET nombre = ?, apellido = ?, id_especialidad = ?  WHERE id_medico = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objMedico.getNombre());
            objPrepare.setString(2,objMedico.getApellido());
            objPrepare.setInt(3,objMedico.getFk_medico_especialidad());
            objPrepare.setInt(4,objMedico.getId_medico());

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
        Medico objMedico = (Medico)obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean idDelete = false;

        try {
            String sql = "DELETE FROM medico WHERE id_medico = ?; ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objMedico.getId_medico());
            int totalAfectedRows = objPrepare.executeUpdate();
            if (totalAfectedRows > 0) {
                idDelete = true;
                JOptionPane.showMessageDialog((Component) null, "the update was successful.");
            }
        } catch (Exception var8) {
            JOptionPane.showMessageDialog((Component)null, var8.getMessage());
        }

        ConfigDB.closeConnection();
        return idDelete;
    }

    public Medico findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Medico objMedico = null;

        try {
            String sql = "SELECT * FROM medico INNER JOIN especialidad ON especialidad.id_especialidad = medico.id_especialidad WHERE medico.id_medico = ?; " ;
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);
            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objMedico= new Medico();
                Especialidad objEspecialidad= new Especialidad();

                objMedico.setNombre(objResult.getString("medico.nombre"));
                objMedico.setApellido(objResult.getString("medico.apellido"));
                objMedico.setFk_medico_especialidad(objResult.getInt("medico.id_especialidad"));
                objMedico.setId_medico(objResult.getInt("medico.id_medico"));

                objEspecialidad.setId_especialidad(objResult.getInt("especialidad.id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("especialidad.nombre"));
                objEspecialidad.setDescripcion(objResult.getString("especialidad.descripcion"));

                objMedico.setEspecialidad(objEspecialidad);

            }
        } catch (Exception var7) {
            JOptionPane.showMessageDialog((Component)null, var7.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objMedico;
    }
}

