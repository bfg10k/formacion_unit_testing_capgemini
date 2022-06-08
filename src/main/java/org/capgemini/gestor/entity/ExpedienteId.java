package org.capgemini.gestor.entity;

import java.util.Objects;

public class ExpedienteId {
    private String identifier;

    public ExpedienteId(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this==o) return true;
        if (o==null || getClass()!=o.getClass()) return false;
        ExpedienteId that = (ExpedienteId) o;
        return Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }
}
