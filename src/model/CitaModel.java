package model;

import dataBase.CRUD;
import dataBase.ConfigDB;
import entity.Cita;
import entity.Especialidad;
import entity.Medico;
import entity.Paciente;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CitaModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        //1. Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir el obj que llego a especialidad
        Cita objCita=(Cita) obj;

        try{
            //3. Escribir SQL
            String sql = "INSERT INTO cita(id_paciente, id_medico, fecha_cita, hora_cita, motivo) VALUES (?,?,?,?,?);";

            //4. Preparar el statement, ademas agregamos la propiedad RETURN GENERATE KEYS, que hace que la sentencia SQL retorno los id generado por la base de datos.
            PreparedStatement objPrepare= objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Asignar valor a los ?,?,?
            objPrepare.setString(3,objCita.getFecha_cita());
            objPrepare.setString(4,objCita.getHora_cita());
            objPrepare.setString(5,objCita.getMotivo());
            objPrepare.setInt(1, objCita.getFk_cita_paciente());
            objPrepare.setInt(2, objCita.getFk_cita_medico());

            //6. Ejecutar el query
            objPrepare.execute();

            //7. Obtener el resultado con los id o llaves generadas
            ResultSet objRest= objPrepare.getGeneratedKeys();

            //8. Iterar mientras  haya un registro
            while (objRest.next()){
                objCita.setId_cita(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Cita insertion was successful");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objCita;
    }

    @Override
    public List<Object> findAll() {
        //1. Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear lista para guardar lo que nos devuelve la base de datos
        List<Object> listCita = new ArrayList<>();

        try{
            //3. Escribir el codigo SQL;
            String sql= "SELECT * FROM cita INNER JOIN paciente ON paciente.id_paciente = cita.id_paciente" +
                    " INNER JOIN medico ON medico.id_medico = cita.id_medico;";

            //4. Usar el prepareStatement
            PreparedStatement objprepare= objConnection.prepareStatement(sql);

            //5. Ejecutar el query y obtener el resultado(ResueltSet)

            ResultSet objResult= objprepare.executeQuery();

            //6. Mientras haya un resultado siguiente hacer:
            while(objResult.next()){
                //6.1 Crear un coder
                Cita objCita= new Cita();
                Paciente objPaciente= new Paciente();
                Medico objMedico= new Medico();

                //6.2 Llenar el objeto con la informacion de la base de datos
                objCita.setFecha_cita(objResult.getString("cita.fecha_cita"));
                objCita.setHora_cita(objResult.getString("cita.hora_cita"));
                objCita.setMotivo(objResult.getString("cita.motivo"));
                objCita.setFk_cita_paciente(objResult.getInt("cita.id_paciente"));
                objCita.setFk_cita_medico(objResult.getInt("cita.id_medico"));
                objCita.setId_cita(objResult.getInt("cita.id_cita"));

                objPaciente.setNombre(objResult.getString("paciente.nombre"));
                objPaciente.setApellido(objResult.getString("paciente.apellido"));
                objPaciente.setFecha_naciento(objResult.getString("paciente.fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("paciente.documiento_identidad"));
                objPaciente.setId_paciente(objResult.getInt("paciente.id_paciente"));

                objMedico.setNombre(objResult.getString("medico.nombre"));
                objMedico.setApellido(objResult.getString("medico.apellido"));
                objMedico.setId_medico(objResult.getInt("medico.id_medico"));
                objMedico.setFk_medico_especialidad(objResult.getInt("medico.id_especialidad"));

                objCita.setPaciente(objPaciente);
                objCita.setMedico(objMedico);

                listCita.add(objCita);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return listCita;
    }

    @Override
    public boolean update(Object obj) {

        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = (Cita)obj;
        boolean isUpdated = false;
        try {
            String sql = "UPDATE cita SET fecha_cita = ?, hora_cita = ?, motivo = ?, id_paciente= ? id_medico= ?  WHERE id_cita = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objCita.getFecha_cita());
            objPrepare.setString(2,objCita.getHora_cita());
            objPrepare.setString(3,objCita.getMotivo());
            objPrepare.setInt(4,objCita.getFk_cita_paciente());
            objPrepare.setInt(5, objCita.getFk_cita_medico());
            objPrepare.setInt(6, objCita.getId_cita());

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

        Cita objCita = (Cita)obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean idDelete = false;

        try {
            String sql = "DELETE FROM cita WHERE id_cita = ?; ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objCita.getId_cita());

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

    public Cita findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = null;

        try {
            String sql = "SELECT * FROM cita INNER JOIN paciente ON paciente.id_paciente = cita.id_paciente " +
                    " INNER JOIN medico ON medico.id_medico = cita.id_medico WHERE cita.id_cita = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objCita = new Cita();
                Paciente objPaciente = new Paciente();
                Medico objMedico = new Medico();

                objCita.setFecha_cita(objResult.getString("cita.fecha_cita"));
                objCita.setHora_cita(objResult.getString("cita.hora_cita"));
                objCita.setMotivo(objResult.getString("cita.motivo"));
                objCita.setFk_cita_paciente(objResult.getInt("cita.id_paciente"));
                objCita.setFk_cita_medico(objResult.getInt("cita.id_medico"));
                objCita.setId_cita(objResult.getInt("cita.id_cita"));

                objPaciente.setNombre(objResult.getString("paciente.nombre"));
                objPaciente.setApellido(objResult.getString("paciente.apellido"));
                objPaciente.setFecha_naciento(objResult.getString("paciente.fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("paciente.documiento_identidad"));
                objPaciente.setId_paciente(objResult.getInt("paciente.id_paciente"));

                objMedico.setNombre(objResult.getString("medico.nombre"));
                objMedico.setApellido(objResult.getString("medico.apellido"));
                objMedico.setId_medico(objResult.getInt("medico.id_medico"));
                objMedico.setFk_medico_especialidad(objResult.getInt("medico.id_especialidad"));

                objCita.setPaciente(objPaciente);
                objCita.setMedico(objMedico);

            }
        } catch (Exception var7) {
            JOptionPane.showMessageDialog((Component) null, var7.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return objCita;
    }
}
