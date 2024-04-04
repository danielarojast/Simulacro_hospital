package entity;

public class Paciente {
    private int id_paciente;
    private String nombre;
    private String apellido;
    private String fecha_naciento;
    private String documento_identidad;

    //Constructor

    public Paciente() {

    }

    public Paciente(int id_paciente, String nombre, String apellido, String fecha_naciento, String documento_identidad) {
        this.id_paciente = id_paciente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_naciento = fecha_naciento;
        this.documento_identidad = documento_identidad;
    }

    //getter y setter

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFecha_naciento() {
        return fecha_naciento;
    }

    public void setFecha_naciento(String fecha_naciento) {
        this.fecha_naciento = fecha_naciento;
    }

    public String getDocumento_identidad() {
        return documento_identidad;
    }

    public void setDocumento_identidad(String documento_identidad) {
        this.documento_identidad = documento_identidad;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id_paciente=" + id_paciente +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fecha_naciento='" + fecha_naciento + '\'' +
                ", documento_identidad='" + documento_identidad + '\'' +
                '}';
    }
}


