package entity;

public class Cita {
    private int id_cita;
    private String fecha_cita;
    private String hora_cita;
    private String motivo;
    private int fk_cita_paciente;
    private Paciente paciente;
    private int fk_cita_medico;
    private Medico medico;

    //Constructor

    public Cita() {

    }

    public Cita(int id_cita, String fecha_cita, String hora_cita, String motivo, int fk_cita_paciente, Paciente paciente, int fk_cita_medico, Medico medico) {
        this.id_cita = id_cita;
        this.fecha_cita = fecha_cita;
        this.hora_cita = hora_cita;
        this.motivo = motivo;
        this.fk_cita_paciente = fk_cita_paciente;
        this.paciente = paciente;
        this.fk_cita_medico = fk_cita_medico;
        this.medico = medico;
    }

    //Getter y Setter

    public int getId_cita() {
        return id_cita;
    }

    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
    }

    public String getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(String fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public String getHora_cita() {
        return hora_cita;
    }

    public void setHora_cita(String hora_cita) {
        this.hora_cita = hora_cita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getFk_cita_paciente() {
        return fk_cita_paciente;
    }

    public void setFk_cita_paciente(int fk_cita_paciente) {
        this.fk_cita_paciente = fk_cita_paciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public int getFk_cita_medico() {
        return fk_cita_medico;
    }

    public void setFk_cita_medico(int fk_cita_medico) {
        this.fk_cita_medico = fk_cita_medico;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    //toString

    @Override
    public String toString() {
        return "Cita{" +
                "id_cita=" + id_cita +
                ", fecha_cita='" + fecha_cita + '\'' +
                ", hora_cita='" + hora_cita + '\'' +
                ", motivo='" + motivo + '\'' +
                ", fk_cita_paciente=" + fk_cita_paciente +
                ", paciente=" + paciente.toString() +
                ", fk_cita_medico=" + fk_cita_medico +
                ", medico=" + medico.toString() +
                '}';
    }
}




