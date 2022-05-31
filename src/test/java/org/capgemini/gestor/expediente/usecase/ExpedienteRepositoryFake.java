package org.capgemini.gestor.expediente.usecase;

import org.capgemini.gestor.entity.Expediente;
import org.capgemini.gestor.service.ExpedienteRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExpedienteRepositoryFake implements ExpedienteRepository {
    private Map<String, Expediente> expedientes;

    public ExpedienteRepositoryFake() {
        this.expedientes = new HashMap<>();
    }

    public ExpedienteRepositoryFake(Map<String, Expediente> expedientes) {
        this.expedientes = expedientes;
    }


    @Override
    public void saveExpediente(Expediente expediente) throws IOException {
        this.expedientes.put(expediente.getExpedienteId(), expediente);
    }

    @Override
    public Expediente find(String uuid) throws IOException {
        return this.expedientes.get(uuid);
    }

    public Boolean saveWasCalled(String uuid) {
        return this.expedientes.containsKey(uuid);
    }
}
