package org.capgemini.gestor.expediente.usecase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.capgemini.gestor.entity.Expediente;
import org.capgemini.gestor.usecase.CreacionExpedienteUseCase;
import org.capgemini.gestor.util.json.LocalDateDeserializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

public class CreacionExpedienteTest {
    @Test
    public void puedeRegistrarUnExpedienteValido() throws IOException {
        String uuid = "4c737a3f-aeab-4676-bfe4-70fac755d5ed";
        String codigoUnidad = "L9894857";
        LocalDate fechaSesion = LocalDate.parse("2022-04-08");
        String descripcion = "Acta de Ayuntamiento Molina de Segura 2022-04-08";

        Expediente expediente = new CreacionExpedienteUseCase(new FileExpedienteRepositoryTestDouble()).execute(uuid, codigoUnidad, fechaSesion, descripcion);

        Assertions.assertEquals(uuid, expediente.getExpedienteId());
        Assertions.assertEquals(codigoUnidad, expediente.getUnidad());
        Assertions.assertEquals(fechaSesion, expediente.getFechaSesion());
        Assertions.assertEquals(descripcion, expediente.getDescripcion());
        Assertions.assertEquals("inicial", expediente.getEstado());
    }
}
