package org.capgemini.gestor.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.capgemini.gestor.entity.Expediente;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileExpedienteRepository implements ExpedienteRepository {
    private final Gson gson;

    public FileExpedienteRepository(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void saveExpediente(Expediente expediente) throws IOException {
        List<Expediente> persistedExpedientes = this.getExpedientes();

        persistedExpedientes.add(expediente);
        Files.writeString(Paths.get("C:\\Users\\marcos\\gestor-documentos\\expedientes.json"), gson.toJson(persistedExpedientes));
    }

    @Override
    public Expediente find(String uuid) throws IOException {
        return getExpedientes().stream().filter(expediente -> expediente.getExpedienteId().equals(uuid)).findFirst().orElse(null);
    }

    private List<Expediente> getExpedientes() throws IOException {
        String text = Files.readString(Paths.get("C:\\Users\\marcos\\gestor-documentos\\expedientes.json"));
        Type persistedExpedientesType = new TypeToken<ArrayList<Expediente>>() {
        }.getType();

        return gson.fromJson(!text.equals("") ? text:"[]", persistedExpedientesType);
    }
}
