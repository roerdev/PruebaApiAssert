package com.roerdev.pruebaapiassert.exceptions;

public class EntityNotFoundException extends RuntimeException {
    private static final String DESCRIPTION = "No existe el registro solicitado";

    public EntityNotFoundException(String message) {
        super(String.format("%s. %s", DESCRIPTION, message));
    }
}
