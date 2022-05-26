package org.capgemini.gestor.service;

import org.capgemini.gestor.entity.Expediente;

import java.io.IOException;
import java.util.List;

public interface ExpedienteRepository {
    void saveExpediente(Expediente expediente) throws IOException;
}
