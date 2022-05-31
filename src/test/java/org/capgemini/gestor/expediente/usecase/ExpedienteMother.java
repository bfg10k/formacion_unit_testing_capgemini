package org.capgemini.gestor.expediente.usecase;

import org.capgemini.gestor.entity.Expediente;

public class ExpedienteMother {

    public static Expediente withUuid(String uuidDuplicado) {
        return new ExpedienteBuilder().withUuid(uuidDuplicado).build();
    }
}
