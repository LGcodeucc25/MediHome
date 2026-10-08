import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServicioDomiciliario {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private String codigo;
    private LocalDateTime fechaHora;
    private String direccionAtencion;
    private String motivo;
    private String estado;
    private Paciente paciente;
    private ProfesionalSalud profesional;
    private AtencionMedica atencion;

    public ServicioDomiciliario() {
        this.estado = "Solicitado";
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getDireccionAtencion() {
        return direccionAtencion;
    }

    public void setDireccionAtencion(String direccionAtencion) {
        this.direccionAtencion = direccionAtencion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public ProfesionalSalud getProfesional() {
        return profesional;
    }

    public void setProfesional(ProfesionalSalud profesional) {
        this.profesional = profesional;
    }

    public AtencionMedica getAtencion() {
        return atencion;
    }

    public void setAtencion(AtencionMedica atencion) {
        this.atencion = atencion;
    }

    public void programar() {
        if (profesional == null) {
            System.out.println("Aviso: el servicio " + codigo + " no tiene profesional asignado, no se puede programar.");
            return;
        }
        estado = "Programado";
        String mensaje = "Servicio " + codigo + " programado para el " + fechaHora.format(FORMATO)
                + " en " + direccionAtencion + ".";
        if (paciente != null) {
            paciente.notificar(mensaje);
        }
        profesional.notificar(mensaje);
    }
}
