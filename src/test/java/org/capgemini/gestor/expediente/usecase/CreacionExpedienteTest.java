package org.capgemini.gestor.expediente.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.capgemini.gestor.entity.Expediente;
import org.capgemini.gestor.entity.ExpedienteId;
import org.capgemini.gestor.service.ExpedienteRepository;
import org.capgemini.gestor.usecase.CreacionExpedienteUseCase;
import org.capgemini.gestor.usecase.ExpedienteDuplicado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
public class CreacionExpedienteTest {
    @Test
    public void puedeRegistrarUnExpedienteValido(@Mock ExpedienteRepository expedienteRepositoryMock) throws IOException {
        String uuid = "4c737a3f-aeab-4676-bfe4-70fac755d5ed";
        String codigoUnidad = "L9894857";
        LocalDate fechaSesion = LocalDate.parse("2022-04-08");
        String descripcion = "Acta de Ayuntamiento Molina de Segura 2022-04-08";

        Expediente expediente = new CreacionExpedienteUseCase(expedienteRepositoryMock).execute(uuid, codigoUnidad, fechaSesion, descripcion);

        Mockito.verify(expedienteRepositoryMock, times(1)).saveExpediente(any(Expediente.class));
        assertEquals(new ExpedienteId(uuid), expediente.getExpedienteId());
        assertEquals(codigoUnidad, expediente.getUnidad());
        assertEquals(fechaSesion, expediente.getFechaSesion());
        assertEquals(descripcion, expediente.getDescripcion());
        assertEquals("inicial", expediente.getEstado());
    }

    @Test
    public void noPuedeRegistrarExpedienteConFechaFutura(@Mock ExpedienteRepository expedienteRepositoryDummy) {
        String uuid = "4c737a3f-aeab-4676-bfe4-70fac755d5ed";
        String codigoUnidad = "L9894857";
        LocalDate fechaSesion = LocalDate.now().plusDays(10);
        String descripcion = "Acta de Ayuntamiento Molina de Segura 2022-04-08";

        assertThrows(
                IllegalArgumentException.class,
                () -> new CreacionExpedienteUseCase(expedienteRepositoryDummy).execute(uuid, codigoUnidad, fechaSesion, descripcion)
        );
    }


    @Test
    public void noPuedeRegistrarExpedienteDuplicado(@Mock ExpedienteRepository expedienteRepositoryStub) throws IOException {
        String uuidDuplicado = "4c737a3f-aeab-4676-bfe4-70fac755d5ed";
        Expediente expediente = ExpedienteMother.withUuid(uuidDuplicado);

        Mockito.when(expedienteRepositoryStub.find(uuidDuplicado)).thenReturn(expediente);

        assertThrows(ExpedienteDuplicado.class,
                () -> new CreacionExpedienteUseCase(expedienteRepositoryStub).execute(uuidDuplicado, expediente.getUnidad(), expediente.getFechaSesion(), expediente.getDescripcion()));

        Mockito.verify(expedienteRepositoryStub, never()).saveExpediente(any(Expediente.class));
    }

}
