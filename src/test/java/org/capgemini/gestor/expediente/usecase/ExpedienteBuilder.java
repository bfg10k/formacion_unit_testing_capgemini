package org.capgemini.gestor.expediente.usecase;

import org.capgemini.gestor.entity.Expediente;

import java.time.LocalDate;

public class ExpedienteBuilder {

    private String uuid;
    private String unidad;
    private LocalDate fechaSesion;
    private String descripcion;
    private String estado;

    public ExpedienteBuilder() {
        this.uuid = "4c737a3f-aeab-4676-bfe4-70fac755d5ed";
        this.unidad = "L9898989";
        this.fechaSesion = LocalDate.now();
        this.descripcion = "Una descripción válida";
        this.estado = "inicial";
    }

    public ExpedienteBuilder withUuid(String uuid) {
         this.uuid = uuid;

         return this;
    }

    public Expediente build() {
        return new Expediente(this.uuid, this.unidad, this.fechaSesion, this.descripcion);
    }
}
