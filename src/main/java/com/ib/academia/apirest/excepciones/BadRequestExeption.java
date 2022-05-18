package com.ib.academia.apirest.excepciones;

public class BadRequestExeption extends RuntimeException{

    public BadRequestExeption(String message) {
        super(message);
    }

    private static final long serialVersionUID = 3499679324398058430L;
}
