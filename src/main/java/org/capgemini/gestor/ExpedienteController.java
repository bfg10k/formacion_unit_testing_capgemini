package org.capgemini.gestor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.capgemini.gestor.entity.Expediente;
import org.capgemini.gestor.service.FileExpedienteRepository;
import org.capgemini.gestor.usecase.CreacionExpedienteUseCase;
import org.capgemini.gestor.util.json.LocalDateDeserializer;
import org.cefire.library.util.json.serializer.LocalDateSerializer;

import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ExpedienteController {
    public static String register(Request request, Response response) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer()).setPrettyPrinting().create();

        String uuid = request.queryParamOrDefault("uuid", "");
        String codigoUnidad = request.queryParamOrDefault("codigoUnidad", "");
        String strFechaSesion = request.queryParamOrDefault("fechaSesion", "");
        String descripcion = request.queryParamOrDefault("descripcion", "");

        try {
            LocalDate fechaSesion = LocalDate.parse(strFechaSesion, DateTimeFormatter.ISO_DATE);
            Expediente expediente = new CreacionExpedienteUseCase(new FileExpedienteRepository(gson)).execute(uuid, codigoUnidad, fechaSesion, descripcion);

            Map<String, Expediente> successContent = new HashMap<>();
            successContent.put("expediente", expediente);
            return gson.toJson(successContent);
        } catch (IllegalArgumentException exception) {
            response.status(422);
            Map<String, String> errorContent = new HashMap<>();
            errorContent.put("error", "1001");
            errorContent.put("message", "Parámetros no válidos..");
            return new Gson().toJson(errorContent);
        } catch (Exception exception) {
            response.status(500);
            Map<String, String> errorContent = new HashMap<>();
            errorContent.put("error", "2001");
            errorContent.put("message", "No se pudo guardar el Expediente. Pruebe más tarde.");
            return new Gson().toJson(errorContent);
        }
    }

}