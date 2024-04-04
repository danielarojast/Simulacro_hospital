package controller;

import entity.Especialidad;
import entity.Paciente;
import model.EspecialidadModel;
import model.PacienteModel;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class PacienteController {
    public static void create(){
        PacienteModel objPacienteModel= new PacienteModel();

        String nombre= JOptionPane.showInputDialog("Nombre: ");
        String apellido= JOptionPane.showInputDialog("Apellido: ");
        String fecha_nacimiento= JOptionPane.showInputDialog("fecha de nacimiento: ");
        String documiento_identidad= JOptionPane.showInputDialog("Documiento de indentidad: ");

        if(nombre != null || apellido != null || fecha_nacimiento != null|| documiento_identidad != null ){
            Paciente objPaciente = new Paciente();

            objPaciente.setNombre(nombre);
            objPaciente.setApellido(apellido);
            objPaciente.setFecha_naciento(fecha_nacimiento);
            objPaciente.setDocumento_identidad(documiento_identidad);

            objPaciente= (Paciente) objPacienteModel.insert(objPaciente);
            JOptionPane.showMessageDialog(null, objPaciente.toString());
        }

    }
    public static void getAll() {
        PacienteModel objPacienteModel = new PacienteModel();
        String listPaciente = "Lista Pacientes: \n";
        for (Object iterator : objPacienteModel.findAll()) {
            Paciente objPaciente = (Paciente) iterator;
            listPaciente += objPaciente.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listPaciente);
    }

    public static String getAllString() {
        PacienteModel objPacienteModel = new PacienteModel();
        String listPaciente = "Lista Pacientes: \n";

        Paciente objPaciente;
        for (Iterator var2 = objPacienteModel.findAll().iterator(); var2.hasNext(); listPaciente = listPaciente + objPaciente.toString() + "\n") {
            Object iterador = var2.next();
            objPaciente = (Paciente) iterador;
        }

        return listPaciente;
    }

    public static void findById(){
        PacienteModel objPacienteModel = new PacienteModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Insert id to search")) ;

        Paciente objPaciente = objPacienteModel.findById(id);
        if(objPaciente == null){
            JOptionPane.showMessageDialog(null, "No se encontro id");
        }else{
            JOptionPane.showMessageDialog(null,objPaciente.toString());
        }
    }

    public static void delete() {
        PacienteModel objPacienteModel = new PacienteModel();
        String listPaciente = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listPaciente + "\n Inserte el ID del paciente a eliminar: "));
        Paciente objPaciente = objPacienteModel.findById(idDelete);
        boolean confirm = true;
        if (objPaciente == null) {
            JOptionPane.showMessageDialog((Component) null, "Especialidad not found");
        } else {
            int confirm2 = JOptionPane.showConfirmDialog((Component) null, "Are you sure want to delete the paciente \n" + objPaciente.toString());
            if (confirm2 == 0) {
                objPacienteModel.delete(objPaciente);
            }
        }
    }

    public static void update(){
        String listPaciente = getAllString();
        PacienteModel objPacienteModel = new PacienteModel();
        String idOpcional = JOptionPane.showInputDialog(listPaciente + "\nInsert the ID of the paciente to update");
        if (idOpcional != null){
            int idUpdated = Integer.parseInt(idOpcional);
            Paciente objPaciente = objPacienteModel.findById(idUpdated);


            if (objPaciente == null){
                JOptionPane.showMessageDialog(null,"No se encontro paciente con ese ID");
            } else {
                String nombre = JOptionPane.showInputDialog(null, "Inserte el nombre",objPaciente.getNombre());
                String apellido = JOptionPane.showInputDialog(null,"Inserte el apellido",objPaciente.getApellido());
                String fecha_nacimiento = JOptionPane.showInputDialog(null,"Fecha de nacimiento: ",objPaciente.getFecha_naciento());
                String documiento_identidad = JOptionPane.showInputDialog(null,"Documento de identidad: ",objPaciente.getDocumento_identidad());

                objPaciente.setNombre(nombre);
                objPaciente.setApellido(apellido);
                objPaciente.setFecha_naciento(fecha_nacimiento);
                objPaciente.setDocumento_identidad(documiento_identidad);

                objPacienteModel.update(objPaciente);

            }
        }
    }

    public static void findNombre() {
        PacienteModel objPacienteModel = new PacienteModel();
        String nombre = JOptionPane.showInputDialog("\n Inserte el nombre del paciente que busca: ");
        String listaString = "CONICIDENCIAS: \n";

        Paciente iterador;
        for(Iterator var3 = objPacienteModel.findByNombre(nombre).iterator(); var3.hasNext(); listaString = listaString + iterador.toString() + "\n") {
            iterador = (Paciente)var3.next();
        }

        JOptionPane.showMessageDialog((Component)null, listaString);
    }

}
