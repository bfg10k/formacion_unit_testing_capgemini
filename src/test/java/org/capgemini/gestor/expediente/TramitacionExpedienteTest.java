package org.capgemini.gestor.expediente;

import org.capgemini.gestor.Expediente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TramitacionExpedienteTest {

    @Test
    public void puedoPonerEnTramitacionUnExpedienteNuevo(){
        Expediente expediente = new Expediente(1, "L9898897", LocalDate.now(), "Descripcion Valida");
        expediente.ponerEnTramitacion();

        Assertions.assertEquals("en_tramitacion", expediente.getEstado());
    }

    @Test
    public void fallaAlPonerEnTramitacionUnExpedienteEnTramitacion(){
        Expediente expediente = new Expediente(1, "L9898897", LocalDate.now(), "Descripcion Valida");
        expediente.ponerEnTramitacion();
        Assertions.assertThrows(IllegalStateException.class, expediente::ponerEnTramitacion);
    }
}
