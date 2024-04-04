package controller;

import entity.Especialidad;
import model.EspecialidadModel;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class EspecialidadController {
    public static void create() {
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();

        String nombre = JOptionPane.showInputDialog("Nombre: ");
        String descripcion = JOptionPane.showInputDialog("Descripcion: ");

        if(nombre != null || descripcion != null ){
            Especialidad objEspecialidad = new Especialidad();

            objEspecialidad.setNombre(nombre);
            objEspecialidad.setDescripcion(descripcion);

            objEspecialidad = (Especialidad) objEspecialidadModel.insert(objEspecialidad);
            JOptionPane.showMessageDialog(null, objEspecialidad.toString());
        }
    }

    public static void getAll() {
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();
        String listEspecialidad = "Lista Especialidad\n";
        for (Object iterator : objEspecialidadModel.findAll()) {
            Especialidad objEspecialidad = (Especialidad) iterator;
            listEspecialidad += objEspecialidad.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listEspecialidad);
    }

    public static String getAllString() {
        EspecialidadModel objModel = new EspecialidadModel();
        String listEspecialidad = "Lista especialidad:  \n";

        Especialidad objEspecialidad;
        for (Iterator var2 = objModel.findAll().iterator(); var2.hasNext(); listEspecialidad = listEspecialidad + objEspecialidad.toString() + "\n") {
            Object iterador = var2.next();
            objEspecialidad = (Especialidad) iterador;
        }

        return listEspecialidad;
    }

    public static void delete() {
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();
        String listEspecialidad = getAllString();
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listEspecialidad + "\n Inserte el ID de la especialidad a eliminar: "));
        Especialidad objEspecialidad = objEspecialidadModel.findById(idDelete);
        boolean confirm = true;
        if (objEspecialidad == null) {
            JOptionPane.showMessageDialog((Component) null, "Especialidad not found");
        } else {
            int confirm2 = JOptionPane.showConfirmDialog((Component) null, "Are you sure want to delete the especialidad \n" + objEspecialidad.toString());
            if (confirm2 == 0) {
                objEspecialidadModel.delete(objEspecialidad);
            }
        }
    }

    public static void findNombre() {
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();
        String nombre = JOptionPane.showInputDialog("\n Inserte el nombre de la especialidad que busca: ");
        String listaString = "CONICIDENCIAS: \n";

        Especialidad iterador;
        for(Iterator var3 = objEspecialidadModel.findByNombre(nombre).iterator(); var3.hasNext(); listaString = listaString + iterador.toString() + "\n") {
            iterador = (Especialidad)var3.next();
        }

        JOptionPane.showMessageDialog((Component)null, listaString);
    }

    public static void findById(){
       EspecialidadModel objEspecialidadModel = new EspecialidadModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Insert id to search")) ;

        Especialidad objEspecilidad = objEspecialidadModel.findById(id);
        if(objEspecilidad == null){
            JOptionPane.showMessageDialog(null, "No se encontro id");
        }else{
            JOptionPane.showMessageDialog(null,objEspecilidad.toString());
        }


    }

    public static void update(){
        String listEspecialidad = getAllString();
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();
        String idOpcional = JOptionPane.showInputDialog(listEspecialidad + "\nInsert the ID of the especialidad to update");
        if (idOpcional != null){
            int idUpdated = Integer.parseInt(idOpcional);
            Especialidad objEspecialidad = objEspecialidadModel.findById(idUpdated);


            if (objEspecialidad == null){
                JOptionPane.showMessageDialog(null,"No se encontro especialidad con ese ID");
            } else {
                String nombre = JOptionPane.showInputDialog(null, "Inserte el nombre",objEspecialidad.getNombre());
                String descripcion = JOptionPane.showInputDialog(null,"Inserte la descripcion",objEspecialidad.getDescripcion());

                objEspecialidad.setNombre(nombre);
                objEspecialidad.setDescripcion(descripcion);

                objEspecialidadModel.update(objEspecialidad);

            }
        }


    }
}
