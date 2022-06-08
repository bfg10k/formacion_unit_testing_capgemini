package org.capgemini.gestor.expediente.entity;

import org.capgemini.gestor.entity.Expediente;
import org.capgemini.gestor.entity.ExpedienteId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.stream.Stream;

public class CreacionExpedienteTest {
    public static Stream<Arguments> datosValidosExpediente() {

        return Stream.of(
                Arguments.of("cb2d1a53-4ef1-4ee9-9f8c-6d35a741dafb", "L9898897", LocalDate.now(), "Descripción Válida"),
                Arguments.of("cb2d1a53-4ef1-4ee9-9f8c-6d35a741d2fb", "L9898899", LocalDate.now().minusDays(10), ""),
                Arguments.of("cb2d1a53-4ef1-4ee9-9f8c-6d35a741d2fb", "L9898899", LocalDate.now().minusDays(100), "Descripción VálidaaaDescripción VálidaaaDescripción Válidaaa"),
                Arguments.of("cb2d1a53-4ef1-4ee9-9f8c-6d35a741d2fb", "L9898899", LocalDate.now().minusDays(1000), "Descripción Válidaaa"),
                Arguments.of("cb2d1a53-4ef1-4ee9-9f8c-6d35a741d2fb", "L9898899", LocalDate.now().minusDays(2034044), "Descripción Válidaaa")
        );
    }

    @ParameterizedTest
    @MethodSource("datosValidosExpediente")
    public void puedoCrearExpedienteConDatosValidos(String expedienteId, String codigoUnidad, LocalDate fechaSesionValida, String descripcionValida) {
        Expediente expediente = new Expediente(new ExpedienteId(expedienteId), codigoUnidad, fechaSesionValida, descripcionValida);

        Assertions.assertEquals(new ExpedienteId(expedienteId), expediente.getExpedienteId());
        Assertions.assertEquals(codigoUnidad, expediente.getUnidad());
        Assertions.assertEquals(fechaSesionValida, expediente.getFechaSesion());
        Assertions.assertEquals(descripcionValida, expediente.getDescripcion());
        Assertions.assertEquals("inicial", expediente.getEstado());
    }

    @Test
    public void fallaAlCrearExpedienteConFechaFutura() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Expediente(new ExpedienteId("cb2d1a53-4ef1-4ee9-9f8c-6d35a741dafb"), "L9898897", LocalDate.now().plusDays(10), "Descripcion Valida"));
    }
}
