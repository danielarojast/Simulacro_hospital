import controller.CitaController;
import controller.EspecialidadController;
import controller.MedicoController;
import controller.PacienteController;
import dataBase.ConfigDB;
import entity.Paciente;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String option = "";

        do {
            option= JOptionPane.showInputDialog("""
                    Selecciona una opcion: 
                    1. Especialidad
                    2. Medicos
                    3. Pacientes
                    4. Citas
                    5. Salir
                    """);

            switch (option){
                case "1":
                    String option1= "";


                    do {
                        option1= JOptionPane.showInputDialog("""
                                Selecciona una opcion: 
                                1. Crear nueva especialidad
                                2. Buscar por ID
                                3. Buscar por nombre 
                                4. Listar especialidades 
                                5. Editar o actualiza
                                6. Eliminar
                                7. Atras
                                Seleccione una opcion: 
                            """);

                        switch (option1){
                            case "1":
                                EspecialidadController.create();
                                break;
                            case "2":
                                EspecialidadController.findById();
                                break;
                            case "3":
                                EspecialidadController.findNombre();
                                break;
                            case "4":
                                EspecialidadController.getAll();
                                break;
                            case "5":
                                EspecialidadController.update();
                                break;
                            case"6":
                                EspecialidadController.delete();
                        }
                    }while (!option1.equals("7"));

                    break;
                case "2":
                    String option2= "";

                    do {
                        option2= JOptionPane.showInputDialog("""
                                Selecciona una opcion: 
                                1. Agregar nuevo medico
                                2. Buscar por ID
                                3. Listar
                                4. Editar o actualiza
                                5. Eliminar
                                6. Atras
                                 
                                Seleccione una opcion : 
                            """);
                        switch (option2){
                            case "1":
                                MedicoController.create();
                                break;
                            case"2":
                                MedicoController.findById();
                                break;
                            case"3":
                                MedicoController.getAll();
                                break;
                            case "4":
                                MedicoController.update();
                                break;
                            case"5":
                                MedicoController.delete();
                                break;
                        }

                    }while (!option2.equals("6"));
                    break;
                case "3":
                    String option3= "";

                    do {
                        option3= JOptionPane.showInputDialog("""
                                Selecciona una opcion: 
                                1. Agregar nuevo paciente
                                2. Buscar por ID
                                3. Buscar por nombre 
                                4. Listar
                                5. Editar o actualiza
                                6. Eliminar
                                7. Atras
                                Seleccione una opcion : 
                            """);

                        switch (option3){
                            case "1":
                                PacienteController.create();
                                break;
                            case"2":
                                PacienteController.findById();
                                break;
                            case"3":
                                PacienteController.findNombre();
                                break;
                            case "4":
                                PacienteController.getAll();
                                break;
                            case"5":
                                PacienteController.update();
                                break;
                            case"6":
                                PacienteController.delete();
                                break;
                        }

                    }while (!option3.equals("7"));
                    break;

                case"4":
                    String option4= "";

                    do {
                        option4= JOptionPane.showInputDialog("""
                                Selecciona una opcion: 
                                1. Agregar nueva cita
                                2. Buscar por ID
                                3. Listar
                                4. Editar o actualiza
                                5. Eliminar
                                6. Atras
                                
                                Seleccione una opcion : 
                            """);

                        switch (option4){
                            case "1":
                                CitaController.create();
                                break;
                            case"2":
                                CitaController.findById();
                                break;
                            case"3":
                                CitaController.getAll();
                                break;
                            case "4":
                                CitaController.update();
                                break;
                            case"5":
                                CitaController.delete();
                                break;
                        }
                    }while (!option4.equals("6"));
                    break;
            }

        }while(!option.equals("5"));




    }
}