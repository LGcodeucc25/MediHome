public class Paciente extends Usuario implements INotificable {
    private String direccionGeneral;
    private String telefono;

    public Paciente() {
    }

    public String getDireccionGeneral() {
        return direccionGeneral;
    }

    public void setDireccionGeneral(String direccionGeneral) {
        this.direccionGeneral = direccionGeneral;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("[Notificacion para " + getNombre() + "] " + mensaje);
    }

    public void registrar() {
        System.out.println("Paciente registrado: " + getIdentificacion() + " - " + getNombre());
    }
}
