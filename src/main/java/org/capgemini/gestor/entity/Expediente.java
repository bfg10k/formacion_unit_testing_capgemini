package org.capgemini.gestor.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Expediente {
    private String expedienteId;
    private String unidad;
    private LocalDate fechaSesion;
    private String descripcion;
    private String estado;

    public Expediente(String expedienteId, String unidad, LocalDate fechaSesion, String descripcion) {
        if (!unidad.matches("[A-Z][0-9]{7}")) {
            throw new IllegalArgumentException("Código de unidad no válido: "+unidad);
        }

        if (fechaSesion.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Fecha de sesión no válida: "+fechaSesion.toString());
        }

        if (descripcion.length() > 100) {
            throw new IllegalArgumentException("Descripción no válida: "+descripcion);
        }

        this.expedienteId = expedienteId;
        this.unidad = unidad;
        this.fechaSesion = fechaSesion;
        this.descripcion = descripcion;
        this.estado = "inicial";
    }

    public String getExpedienteId() {
        return expedienteId;
    }

    public String getUnidad() {
        return unidad;
    }

    public LocalDate getFechaSesion() {
        return fechaSesion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public String ponerEnTramitacion(){
        if(!this.estado.equals("inicial")){
            throw new IllegalStateException("Estado no valido");
        }
        this.estado = "en_tramitacion";
        return this.estado;
    }

    @Override
    public String toString() {
        return "Expediente{" +
                "expedienteId=" + expedienteId +
                ", unidad='" + unidad + '\'' +
                ", fechaSesion=" + fechaSesion +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this==o) return true;
        if (o==null || getClass()!=o.getClass()) return false;
        Expediente that = (Expediente) o;
        return Objects.equals(getExpedienteId(), that.getExpedienteId()) && Objects.equals(getUnidad(), that.getUnidad()) && Objects.equals(getFechaSesion(), that.getFechaSesion()) && Objects.equals(getDescripcion(), that.getDescripcion()) && Objects.equals(getEstado(), that.getEstado());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExpedienteId(), getUnidad(), getFechaSesion(), getDescripcion(), getEstado());
    }
}
