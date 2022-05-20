package com.ibm.academia.apirest.modelos.mapper;

import com.ibm.academia.apirest.controladores.RuletaController;
import com.ibm.academia.apirest.modelos.dto.ApuestaDTO;
import com.ibm.academia.apirest.modelos.entidades.Apuesta;

public class ApuestaMapper {

    public static ApuestaDTO mapApuesta(Apuesta apuesta){
        ApuestaDTO apuestaDTO = new ApuestaDTO();
        apuestaDTO.setId(apuesta.getId());
        apuestaDTO.setPremio(apuesta.getPremio());
        apuestaDTO.setCantidad(apuesta.getCantidad());
        apuestaDTO.setColorApuesta(apuesta.getColorApuesta());
        apuestaDTO.setNumeroApesta(apuesta.getNumeroApuesta());
        apuestaDTO.setColorGanador(RuletaController.colorGanadorDTO);
        apuestaDTO.setNumeroGanador(RuletaController.numeroGanador);
        if(apuesta.getColorApuesta() == RuletaController.colorGanadorDTO || apuesta.getNumeroApuesta() == RuletaController.numeroGanador)
            apuestaDTO.setResutado("Ganaste");
        else
            apuestaDTO.setResutado("Perdiste");


        return apuestaDTO;

    }
}
