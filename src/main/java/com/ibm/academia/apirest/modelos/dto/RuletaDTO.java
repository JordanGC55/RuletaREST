package com.ibm.academia.apirest.modelos.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Setter
@Getter
public class RuletaDTO implements Serializable {

    private Integer id;
    private Boolean estado;
    private Double total;
    private String autor;

    private static final long serialVersionUID = -324778431958412608L;
}
