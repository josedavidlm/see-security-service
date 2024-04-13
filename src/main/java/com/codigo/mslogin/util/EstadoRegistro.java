package com.codigo.mslogin.util;

public enum EstadoRegistro {


    REGISTRADO(1L),
    ENUSO(2l),
    ANULADO(3L);

    private final Long value;

    EstadoRegistro(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return this.value;
    }
}
