package com.ibm.academia.apirest.excepciones;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }

    private static final long serialVersionUID = -6337549837309617185L;
}
