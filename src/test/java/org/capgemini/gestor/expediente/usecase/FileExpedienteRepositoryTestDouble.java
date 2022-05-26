package org.capgemini.gestor.expediente.usecase;

import com.google.gson.Gson;

import org.capgemini.gestor.entity.Expediente;
import org.capgemini.gestor.service.ExpedienteRepository;
import org.capgemini.gestor.service.FileExpedienteRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileExpedienteRepositoryTestDouble implements ExpedienteRepository {
    @Override
    public void saveExpediente(Expediente expediente) throws IOException {
    }
}
