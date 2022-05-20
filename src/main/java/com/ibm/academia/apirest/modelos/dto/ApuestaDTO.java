package com.ibm.academia.apirest.modelos.dto;

import com.ibm.academia.apirest.enums.ColorRuleta;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ApuestaDTO implements Serializable {

    private Integer id;
    private Double premio;
    private Double cantidad;
    private ColorRuleta colorApuesta;
    private ColorRuleta colorGanador;
    private Integer numeroApesta;
    private Integer numeroGanador;
    private String resutado;

    private static final long serialVersionUID = -6604485335949728926L;
}
