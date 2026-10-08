import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final String LINEA = "=".repeat(70);
    private static final String SEPARADOR = "-".repeat(70);

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        System.out.println(LINEA);
        System.out.println("MEDIHOME - ATENCION MEDICA DOMICILIARIA");
        System.out.println(LINEA);

        Paciente paciente = new Paciente();
        paciente.setIdentificacion("1085123456");
        paciente.setNombre("Maria Fernanda Rosero");
        paciente.setCorreo("maria.rosero@correo.com");
        paciente.setDireccionGeneral("Calle 18 # 25-40, Barrio Las Cuadras, Pasto, Narino");
        paciente.setTelefono("3104567890");
        paciente.registrar();

        ProfesionalSalud profesional = new ProfesionalSalud();
        profesional.setIdentificacion("98765432");
        profesional.setNombre("Carlos Andres Benavides");
        profesional.setCorreo("carlos.benavides@medihome.com");
        profesional.setNumeroRegistroProfesional("RM-52-04871");
        profesional.registrar();

        EquipoMedico equipo = new EquipoMedico();
        equipo.setCodigo("EQ-001");
        equipo.setNombre("Equipo Domiciliario Centro");
        equipo.setZonaCobertura("Pasto - Zona Centro");
        equipo.agregarProfesional(profesional);

        ServicioDomiciliario servicio = new ServicioDomiciliario();
        servicio.setCodigo("SD-2026-0001");
        servicio.setFechaHora(LocalDateTime.of(2026, 10, 8, 9, 0));
        servicio.setDireccionAtencion(paciente.getDireccionGeneral());
        servicio.setMotivo("Control de hipertension arterial");
        servicio.setPaciente(paciente);
        System.out.println("Servicio " + servicio.getCodigo() + " creado con estado: " + servicio.getEstado());
        servicio.setProfesional(profesional);
        servicio.programar();
        System.out.println("Estado del servicio: " + servicio.getEstado());

        servicio.setEstado("En atencion");
        System.out.println("Estado del servicio: " + servicio.getEstado());
        AtencionMedica atencion = new AtencionMedica();
        atencion.setFechaHoraInicio(LocalDateTime.of(2026, 10, 8, 9, 10));
        atencion.setObservaciones("Paciente consciente y orientada, refiere cefalea leve ocasional.");
        atencion.setRecomendaciones("Continuar tratamiento, dieta baja en sal y control en 15 dias.");
        servicio.setAtencion(atencion);
        atencion.registrar();

        MedicionSignos medicion = new MedicionSignos();
        medicion.setFechaHora(LocalDateTime.of(2026, 10, 8, 9, 15));
        medicion.setTemperatura(36.8);
        medicion.setFrecuenciaCardiaca(78);
        medicion.setPresionSistolica(135);
        medicion.setPresionDiastolica(85);
        medicion.setSaturacionOxigeno(96.0);
        atencion.agregarMedicion(medicion);
        medicion.registrar();

        atencion.setFechaHoraFinalizacion(LocalDateTime.of(2026, 10, 8, 9, 50));
        servicio.setEstado("Finalizado");
        System.out.println("Estado del servicio: " + servicio.getEstado());
        paciente.notificar("Su servicio " + servicio.getCodigo() + " ha finalizado. Gracias por usar MediHome.");

        imprimirReporte(servicio);
    }

    private static void imprimirReporte(ServicioDomiciliario servicio) {
        Paciente paciente = servicio.getPaciente();
        ProfesionalSalud profesional = servicio.getProfesional();
        AtencionMedica atencion = servicio.getAtencion();

        System.out.println();
        System.out.println(LINEA);
        System.out.println("REPORTE DE ATENCION");
        System.out.println(LINEA);

        System.out.println("DATOS DEL SERVICIO");
        System.out.println(SEPARADOR);
        System.out.printf("%-22s %s%n", "Codigo:", servicio.getCodigo());
        System.out.printf("%-22s %s%n", "Fecha y hora:", servicio.getFechaHora().format(FORMATO));
        System.out.printf("%-22s %s%n", "Direccion:", servicio.getDireccionAtencion());
        System.out.printf("%-22s %s%n", "Motivo:", servicio.getMotivo());
        System.out.printf("%-22s %s%n", "Estado:", servicio.getEstado());

        System.out.println(SEPARADOR);
        System.out.println("DATOS DEL PACIENTE");
        System.out.println(SEPARADOR);
        System.out.printf("%-22s %s%n", "Identificacion:", paciente.getIdentificacion());
        System.out.printf("%-22s %s%n", "Nombre:", paciente.getNombre());
        System.out.printf("%-22s %s%n", "Correo:", paciente.getCorreo());
        System.out.printf("%-22s %s%n", "Direccion:", paciente.getDireccionGeneral());
        System.out.printf("%-22s %s%n", "Telefono:", paciente.getTelefono());

        System.out.println(SEPARADOR);
        System.out.println("DATOS DEL PROFESIONAL");
        System.out.println(SEPARADOR);
        System.out.printf("%-22s %s%n", "Identificacion:", profesional.getIdentificacion());
        System.out.printf("%-22s %s%n", "Nombre:", profesional.getNombre());
        System.out.printf("%-22s %s%n", "Correo:", profesional.getCorreo());
        System.out.printf("%-22s %s%n", "Registro profesional:", profesional.getNumeroRegistroProfesional());

        System.out.println(SEPARADOR);
        System.out.println("DATOS DE LA ATENCION");
        System.out.println(SEPARADOR);
        System.out.printf("%-22s %s%n", "Inicio:", atencion.getFechaHoraInicio().format(FORMATO));
        System.out.printf("%-22s %s%n", "Fin:", atencion.getFechaHoraFinalizacion().format(FORMATO));
        System.out.printf("%-22s %s%n", "Observaciones:", atencion.getObservaciones());
        System.out.printf("%-22s %s%n", "Recomendaciones:", atencion.getRecomendaciones());

        System.out.println(SEPARADOR);
        System.out.println("MEDICIONES DE SIGNOS VITALES");
        System.out.println(SEPARADOR);
        System.out.printf("%-18s %-10s %-10s %-12s %-10s%n", "Fecha y hora", "Temp (C)", "FC (lpm)", "PA (mmHg)", "SpO2 (%)");
        for (MedicionSignos m : atencion.getMediciones()) {
            System.out.printf("%-18s %-10.1f %-10d %-12s %-10.1f%n",
                    m.getFechaHora().format(FORMATO),
                    m.getTemperatura(),
                    m.getFrecuenciaCardiaca(),
                    m.getPresionSistolica() + "/" + m.getPresionDiastolica(),
                    m.getSaturacionOxigeno());
        }
        System.out.println(LINEA);
    }
}
