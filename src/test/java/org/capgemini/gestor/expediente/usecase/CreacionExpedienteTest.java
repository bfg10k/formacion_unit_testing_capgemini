package org.capgemini.gestor.expediente.usecase;

import org.capgemini.gestor.entity.Expediente;
import org.capgemini.gestor.usecase.CreacionExpedienteUseCase;
import org.capgemini.gestor.usecase.ExpedienteDuplicado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

public class CreacionExpedienteTest {
    @Test
    public void puedeRegistrarUnExpedienteValido() throws IOException {
        String uuid = "4c737a3f-aeab-4676-bfe4-70fac755d5ed";
        String codigoUnidad = "L9894857";
        LocalDate fechaSesion = LocalDate.parse("2022-04-08");
        String descripcion = "Acta de Ayuntamiento Molina de Segura 2022-04-08";
        ExpedienteRepositoryFake expedienteRepository = new ExpedienteRepositoryFake();

        Expediente expediente = new CreacionExpedienteUseCase(expedienteRepository).execute(uuid, codigoUnidad, fechaSesion, descripcion);

        Assertions.assertTrue(expedienteRepository.saveWasCalled(uuid));

        Assertions.assertEquals(uuid, expediente.getExpedienteId());
        Assertions.assertEquals(codigoUnidad, expediente.getUnidad());
        Assertions.assertEquals(fechaSesion, expediente.getFechaSesion());
        Assertions.assertEquals(descripcion, expediente.getDescripcion());
        Assertions.assertEquals("inicial", expediente.getEstado());
    }

    @Test
    public void noPuedeRegistrarExpedienteConFechaFutura() {
        String uuid = "4c737a3f-aeab-4676-bfe4-70fac755d5ed";
        String codigoUnidad = "L9894857";
        LocalDate fechaSesion = LocalDate.now().plusDays(10);
        String descripcion = "Acta de Ayuntamiento Molina de Segura 2022-04-08";

        Assertions.assertThrows(IllegalArgumentException.class, () -> new CreacionExpedienteUseCase(new ExpedienteRepositoryDummy()).execute(uuid, codigoUnidad, fechaSesion, descripcion));
    }


    @Test
    public void noPuedeRegistrarExpedienteDuplicado() throws IOException {
        String uuidDuplicado = "4c737a3f-aeab-4676-bfe4-70fac755d5ed";

        HashMap<String, Expediente> conjuntoExpedientes = new HashMap<>();

        Expediente expediente = ExpedienteMother.withUuid(uuidDuplicado);

        conjuntoExpedientes.put(uuidDuplicado, expediente);

        ExpedienteRepositoryFake expedienteRepository = new ExpedienteRepositoryFake(conjuntoExpedientes);

        Assertions.assertThrows(ExpedienteDuplicado.class,
                () -> new CreacionExpedienteUseCase(expedienteRepository).execute(uuidDuplicado, expediente.getUnidad(), expediente.getFechaSesion(), expediente.getDescripcion()));
    }

}
