package entity;

public class Medico {
    private int id_medico;
    private String nombre;
    private String apellido;
    private int fk_medico_especialidad;
    private Especialidad especialidad;

    //Constructores
    public Medico() {

    }

    public Medico(int id_medico, String nombre, String apellido, int fk_medico_especialidad, Especialidad especialidad) {
        this.id_medico = id_medico;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fk_medico_especialidad = fk_medico_especialidad;
        this.especialidad = especialidad;
    }

    //getter y setter
    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
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

    public int getFk_medico_especialidad() {
        return fk_medico_especialidad;
    }

    public void setFk_medico_especialidad(int fk_medico_especialidad) {
        this.fk_medico_especialidad = fk_medico_especialidad;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    //toString

    @Override
    public String toString() {
        if (especialidad != null) {
            return "Medico{" +
                    "id_medico=" + id_medico +
                    ", nombre='" + nombre + '\'' +
                    ", apellido='" + apellido + '\'' +
                    ", fk_medico_especialidad=" + fk_medico_especialidad +
                    ", especialidad=" + especialidad.toString() +
                    '}';
        } else {
            return "Medico{" +
                    "id_medico=" + id_medico +
                    ", nombre='" + nombre + '\'' +
                    ", apellido='" + apellido + '\'' +
                    ", fk_medico_especialidad=" + fk_medico_especialidad +
                    '}';
        }

    }
}
