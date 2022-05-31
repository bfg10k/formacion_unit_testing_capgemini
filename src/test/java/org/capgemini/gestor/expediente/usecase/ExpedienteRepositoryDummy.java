package org.capgemini.gestor.expediente.usecase;

import org.capgemini.gestor.entity.Expediente;
import org.capgemini.gestor.service.ExpedienteRepository;

import java.io.IOException;

public class ExpedienteRepositoryDummy implements ExpedienteRepository {
    @Override
    public void saveExpediente(Expediente expediente) throws IOException {
        throw new RuntimeException();
    }

    @Override
    public Expediente find(String uuid) {
        throw new RuntimeException();
    }
}
