package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Esta clase se encarga de hacer y cerrar la conexion con la base de datos
public class ConfigDB {
    //1. Atributo para mirar el estado de la conexion
    public static Connection objConnection = null;

    //2. Metodo para conectar
    public static Connection openConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url= "jdbc:mysql://localhost:3306/hospital";
            String user= "root";
            String password = "admin";

            //Establecer la conexion
            objConnection= (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Me conecte perfectamente");

        }catch(ClassNotFoundException error){
            System.out.println("ERROR >> Driver no Instalado "+ error.getMessage() );
        }catch(SQLException error){
            System.out.println("ERROR >> error al conectar la base de datos" + error.getMessage());
        };

        return objConnection;
    }

    //3.Metodo para cerrar la conexion
    public static void closeConnection(){
        try{
            //si hay una coneccion activa entonces la cierra
            if(objConnection != null ) objConnection.close();
            System.out.println("Se finalizo la conexion con exito");
        }catch (SQLException e){
            System.out.println("Error: "+ e.getMessage());
        }
    }
}

