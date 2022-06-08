package org.capgemini.gestor.expediente.entity;

import org.capgemini.gestor.entity.Expediente;
import org.capgemini.gestor.entity.ExpedienteId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TramitacionExpedienteTest {

    @Test
    public void puedoPonerEnTramitacionUnExpedienteNuevo(){
        Expediente expediente = new Expediente(new ExpedienteId("cb2d1a53-4ef1-4ee9-9f8c-6d35a741dafb"), "L9898897", LocalDate.now(), "Descripcion Valida");
        expediente.ponerEnTramitacion();

        Assertions.assertEquals("en_tramitacion", expediente.getEstado());
    }

    @Test
    public void fallaAlPonerEnTramitacionUnExpedienteEnTramitacion(){
        Expediente expediente = new Expediente(new ExpedienteId("cb2d1a53-4ef1-4ee9-9f8c-6d35a741dafb"), "L9898897", LocalDate.now(), "Descripcion Valida");
        expediente.ponerEnTramitacion();
        Assertions.assertThrows(IllegalStateException.class, expediente::ponerEnTramitacion);
    }
}
