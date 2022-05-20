package com.ibm.academia.apirest.servicios;

import com.ibm.academia.apirest.enums.ColorRuleta;
import com.ibm.academia.apirest.modelos.entidades.Apuesta;

public interface IApuestaService {

    public Iterable<Apuesta> cierreApuestas(Integer ruletaId, ColorRuleta resultado);
}
