package org.capgemini.gestor.expediente.entity;

import org.capgemini.gestor.entity.Expediente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CreacionExpedienteTest {
    @Test
    public void puedoCrearExpedienteConDatosValidos() {
        String descripcionValida = "Descripcion valida";
        LocalDate fechaSesionValida = LocalDate.now();
        String codigoUnidad = "L9898897";
        String expedienteId = "cb2d1a53-4ef1-4ee9-9f8c-6d35a741dafb";

        Expediente expediente = new Expediente(expedienteId, codigoUnidad, fechaSesionValida, descripcionValida);

        Assertions.assertEquals(expedienteId, expediente.getExpedienteId());
        Assertions.assertEquals(codigoUnidad, expediente.getUnidad());
        Assertions.assertEquals(fechaSesionValida, expediente.getFechaSesion());
        Assertions.assertEquals(descripcionValida, expediente.getDescripcion());
        Assertions.assertEquals("inicial", expediente.getEstado());
    }

    @Test
    public void fallaAlCrearExpedienteConFechaFutura() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Expediente("cb2d1a53-4ef1-4ee9-9f8c-6d35a741dafb", "L9898897", LocalDate.now().plusDays(10), "Descripcion Valida"));
    }
}
