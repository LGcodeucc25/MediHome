# MediHome - Atencion medica domiciliaria

## Participantes

- Juan Luis Jamioy Guerrero

## Descripcion

MediHome es un sistema de consola en Java para gestionar servicios de atencion medica domiciliaria.
Permite registrar pacientes y profesionales de salud, programar servicios a domicilio, registrar la
atencion medica con sus mediciones de signos vitales y notificar a los involucrados.

## Clases del modelo

- `INotificable` (interface)
- `Usuario`
- `Paciente`
- `ProfesionalSalud`
- `EquipoMedico`
- `ServicioDomiciliario`
- `AtencionMedica`
- `MedicionSignos`
- `Main`

## Diagrama de clases

La imagen del diagrama de clases y el archivo fuente de Visual Paradigm (`.vpp`) se encuentran en este repositorio.

## Compilar y ejecutar

Desde la raiz del proyecto:

```
javac -d out src/*.java
java -cp out Main
```
