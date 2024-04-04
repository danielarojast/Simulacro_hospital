package controller;

import entity.Especialidad;
import entity.Medico;
import model.EspecialidadModel;
import model.MedicoModel;

import javax.swing.*;
import java.awt.*;

public class MedicoController {
    public static void create(){
        MedicoModel objMedicoModel = new MedicoModel();

        String nombre = JOptionPane.showInputDialog("Nombre del medico: ");
        String apellido = JOptionPane.showInputDialog("Apellido: ");
        String idOpcional= JOptionPane.showInputDialog(EspecialidadController.getAllString()+"Insert ID de la especialidad a la que pertenece");


        if(idOpcional != null){
            int fk_medico_especialidad = Integer.parseInt(idOpcional);
            EspecialidadModel objModel = new EspecialidadModel();
            Especialidad objE = objModel.findById(fk_medico_especialidad);
            if (objE != null){
                Medico objMedico = new Medico();

                objMedico.setNombre(nombre);
                objMedico.setApellido(apellido);
                objMedico.setFk_medico_especialidad(fk_medico_especialidad);

                objMedico = (Medico) objMedicoModel.insert(objMedico);

                JOptionPane.showMessageDialog(null,objMedico.toString());
            }else{
                JOptionPane.showMessageDialog(null, "El ID no fue encontrado, no se puede agregar medico sin especialidad.");
            }
        }
    }

    public static void getAll(){
        MedicoModel objMedicoModel = new MedicoModel();
        String listMedico = "Lista de medicos\n";

        for (Object iterator : objMedicoModel.findAll()){
            Medico objMedico = (Medico) iterator;
            listMedico += objMedico.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null,listMedico);
    }

    public static String getAllString(){
        MedicoModel objMedicoModel = new MedicoModel();

        String listMedico = "Lista de medicos\n";

        for (Object iterator : objMedicoModel.findAll()){
            Medico objMedico = (Medico) iterator;
            listMedico += objMedico.toString() + "\n";
        }
        return listMedico;
    }

    public static void delete() {
        MedicoModel objMedicoModel = new MedicoModel();
        String listMedico = getAllString();
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listMedico + "\n Inserte el ID del medico a eliminar: "));
        Medico objMedico = objMedicoModel.findById(idDelete);
        boolean confirm = true;
        if (objMedico == null) {
            JOptionPane.showMessageDialog((Component) null, "Medico not found");
        } else {
            int confirm2 = JOptionPane.showConfirmDialog((Component) null, "Are you sure want to delete the Medico \n" + objMedico.toString());
            if (confirm2 == 0) {
                objMedicoModel.delete(objMedico);
            }
        }
    }
    public static void findById(){
        MedicoModel objMedicoModel = new MedicoModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Insert id to search")) ;

        Medico objMedico = objMedicoModel.findById(id);
        if(objMedico == null){
            JOptionPane.showMessageDialog(null, "No se encontro id");
        }else{
            JOptionPane.showMessageDialog(null,objMedico.toString());
        }
    }
    public static void update(){
        String listMedico = getAllString();
        MedicoModel objMedicoModel = new MedicoModel();
        String idOpcional = JOptionPane.showInputDialog(listMedico + "\nInsert the ID of the Medico to update");
        if (idOpcional != null){
            int idUpdated = Integer.parseInt(idOpcional);
            Medico objMedico = objMedicoModel.findById(idUpdated);


            if (objMedico == null){
                JOptionPane.showMessageDialog(null,"No se encontro Medico con ese ID");
            } else {
                String nombre = JOptionPane.showInputDialog(null, "Inserte el nombre",objMedico.getNombre());
                String apellido = JOptionPane.showInputDialog(null,"Inserte el apellido",objMedico.getApellido());
                int id_especialidad= Integer.parseInt(JOptionPane.showInputDialog(null,"Inserte la especialidad a la que pertenece",objMedico.getFk_medico_especialidad()));

                objMedico.setNombre(nombre);
                objMedico.setApellido(apellido);
                objMedico.setFk_medico_especialidad(id_especialidad);

                objMedicoModel.update(objMedico);

            }
        }


    }
}
