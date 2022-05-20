package com.ibm.academia.apirest.excepciones.handler;


import com.ibm.academia.apirest.excepciones.BadRequestExeption;
import com.ibm.academia.apirest.excepciones.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RuletaRESTException {

    @ExceptionHandler(value = BadRequestExeption.class)
    public ResponseEntity<Object> formatoInvalidoException(BadRequestExeption exception){
        Map<String, Object> respuesta = new HashMap<String, Object>();
        respuesta.put("Error", exception.getMessage());
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> noExisteExcepcion(NotFoundException exception){
        Map<String, Object> respuesta = new HashMap<String, Object>();
        respuesta.put("Error", exception.getMessage());
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);

    }

}
