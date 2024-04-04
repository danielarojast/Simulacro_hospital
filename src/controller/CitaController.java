package controller;

import entity.Cita;
import entity.Especialidad;
import entity.Medico;
import model.CitaModel;
import model.EspecialidadModel;
import model.PacienteModel;
import model.MedicoModel;

import javax.swing.*;
import java.awt.*;

public class CitaController {
    public static void create(){
        CitaModel objCitaModel = new CitaModel();
        //instanciar los modelos para utilizar findById
        PacienteModel objPacienteModel= new PacienteModel();
        MedicoModel objMedicoModel= new MedicoModel();

        String fecha_cita = JOptionPane.showInputDialog("Fecha de la cita: ");
        String hora_cita = JOptionPane.showInputDialog("Hora de la cita: ");
        String motivo= JOptionPane.showInputDialog("Motivo de la cita: ");
        String idOptionPaciente= JOptionPane.showInputDialog(PacienteController.getAllString()+"Insert ID del paciente: ");
        String idOptionMedico= JOptionPane.showInputDialog(MedicoController.getAllString()+ "Insert ID del medico que la va atender: ");

        if(idOptionPaciente!= null && idOptionMedico != null){
            int fk_cita_paciente = Integer.parseInt(idOptionPaciente);
            int fk_cita_medico = Integer.parseInt(idOptionMedico);

            Cita objCita = new Cita();

            objCita.setFecha_cita(fecha_cita);
            objCita.setHora_cita(hora_cita);
            objCita.setMotivo(motivo);
            objCita.setFk_cita_paciente(fk_cita_paciente);
            objCita.setFk_cita_medico(fk_cita_medico);

            objCita = (Cita) objCitaModel.insert(objCita);

            //Se utiliza el findById para asignar el paciente y el medico al objeto cita.
            objCita.setMedico(objMedicoModel.findById(fk_cita_medico));
            objCita.setPaciente(objPacienteModel.findById(fk_cita_paciente));

            JOptionPane.showMessageDialog(null,objCita.toString());

        }else{
            JOptionPane.showMessageDialog(null, "El ID no fue encontrado, no se puede agregar Cita.");
        }

    }

    public static void getAll(){
        CitaModel objCitaModel = new CitaModel();
        String listCita = "Lista de Citas\n";

        for (Object iterator : objCitaModel.findAll()){
            Cita objCita = (Cita) iterator;
            listCita += objCita.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null,listCita);
    }

    public static String getAllString(){
        CitaModel objCitaModel = new CitaModel();

        String listCita = "Lista de Citas\n";

        for (Object iterator : objCitaModel.findAll()){
            Cita objCita = (Cita) iterator;
            listCita += objCita.toString() + "\n";
        }
        return listCita;
    }

    public static void findById(){
        CitaModel objCitaModel = new CitaModel();
        int id= Integer.parseInt(JOptionPane.showInputDialog("Inserte el id a buscar: "));
        Cita objCita= objCitaModel.findById(id);

        if(objCita == null){
            JOptionPane.showMessageDialog(null, "No se encontro id");
        }else{
            JOptionPane.showMessageDialog(null, objCita.toString());
        }
    }

    public static void delete() {
        CitaModel objCitaModel = new CitaModel();
        String listCita = getAllString();
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listCita + "\n Inserte el ID de la cita a eliminar: "));
        Cita objCita = objCitaModel.findById(idDelete);
        boolean confirm = true;

        if (objCita == null) {
            JOptionPane.showMessageDialog((Component) null, "Cita not found");
        } else {
            int confirm2 = JOptionPane.showConfirmDialog((Component) null, "Are you sure want to delete the cita \n" + objCita.toString());
            if (confirm2 == 0) {
                objCitaModel.delete(objCita);
            }
        }
    }

    public static void update(){
        String listCita = getAllString();
        CitaModel objCitaModel = new CitaModel();
        String idOpcional = JOptionPane.showInputDialog(listCita + "\nInsert the ID of the Cita to update");
        if (idOpcional != null){
            int idUpdated = Integer.parseInt(idOpcional);
            Cita objCita = objCitaModel.findById(idUpdated);

            if (objCita == null){
                JOptionPane.showMessageDialog(null,"No se encontro la cita con ese ID");
            } else {
                String fecha_cita = JOptionPane.showInputDialog(null, "Inserte la fecha de la cita", objCita.getFecha_cita());
                String hora_cita = JOptionPane.showInputDialog(null,"Inserte la hora ", objCita.getHora_cita());
                String motivo = JOptionPane.showInputDialog(null,"Inserte el motivo", objCita.getMotivo());
                int id_paciente= Integer.parseInt(JOptionPane.showInputDialog(null,"Inserte el id del paciente", objCita.getFk_cita_paciente()));
                int id_medico= Integer.parseInt(JOptionPane.showInputDialog(null,"Inserte el id del medico", objCita.getFk_cita_medico()));

                objCita.setFecha_cita(fecha_cita);
                objCita.setHora_cita(hora_cita);
                objCita.setMotivo(motivo);
                objCita.setFk_cita_paciente(id_paciente);
                objCita.setFk_cita_medico(id_medico);

                objCitaModel.update(objCita);

            }
        }


    }



    }

