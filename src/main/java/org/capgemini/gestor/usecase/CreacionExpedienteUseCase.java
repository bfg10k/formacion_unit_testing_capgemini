package org.capgemini.gestor.usecase;

import org.capgemini.gestor.entity.Expediente;
import org.capgemini.gestor.service.ExpedienteRepository;
import org.capgemini.gestor.service.FileExpedienteRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class CreacionExpedienteUseCase {
    private final ExpedienteRepository expedienteRepository;

    public CreacionExpedienteUseCase(ExpedienteRepository expedienteRepository) {
        this.expedienteRepository = expedienteRepository;
    }

    public Expediente execute(String uuid, String codigoUnidad, LocalDate fechaSesion, String descripcion) throws IOException {
        Expediente expediente = new Expediente(uuid, codigoUnidad, fechaSesion, descripcion);

        expedienteRepository.saveExpediente(expediente);

        return expediente;
    }



}
