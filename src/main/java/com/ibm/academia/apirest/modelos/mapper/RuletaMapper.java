package com.ibm.academia.apirest.modelos.mapper;

import com.ibm.academia.apirest.modelos.dto.RuletaDTO;
import com.ibm.academia.apirest.modelos.entidades.Ruleta;

public class RuletaMapper {
    public static RuletaDTO mapRuleta(Ruleta ruleta){

        RuletaDTO ruletaDTO = new RuletaDTO();
        ruletaDTO.setId(ruleta.getId());
        ruletaDTO.setEstado(ruleta.getEstado());
        ruletaDTO.setTotal(ruleta.getTotal());
        ruletaDTO.setAutor(ruleta.getAutor());
        return ruletaDTO;
    }


}
