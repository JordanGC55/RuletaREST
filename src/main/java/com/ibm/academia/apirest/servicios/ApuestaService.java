package com.ibm.academia.apirest.servicios;

import com.ibm.academia.apirest.enums.ColorRuleta;
import com.ibm.academia.apirest.modelos.entidades.Apuesta;
import com.ibm.academia.apirest.modelos.entidades.Ruleta;
import com.ibm.academia.apirest.repositorios.ApuestaRepository;
import com.ibm.academia.apirest.repositorios.RuletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ApuestaService implements IApuestaService {

    @Autowired
    private ApuestaRepository apuestaRepository;

    @Autowired
    private IRuletaService ruletaService;

    @Autowired
    private RuletaRepository ruletaRepository;

    @Override
    @Transactional
    public Iterable<Apuesta> cierreApuestas(Integer ruletaId, ColorRuleta resultado) {

        Optional<Ruleta> ruleta_id = ruletaService.buscarPorId(ruletaId);
        Iterable<Apuesta> apuestasCalculos = apuestaRepository.buscarApuestasPorRuleta(ruletaId);
        Double totalApuestas = 0.0;
        for(Apuesta apuesta : apuestasCalculos){
            if(apuesta.getColorApuesta() == resultado){
                apuesta.setPremio(apuesta.getCantidad()*1.4);
            }
            else{
                apuesta.setPremio(apuesta.getCantidad()/4);
            }
           totalApuestas += apuesta.getPremio();
        }

        //Ruleta ruletaTotal = ruleta_id.get();
        //ruletaTotal.setTotal(totalApuestas);
        //ruletaTotal.setEstado(false);
        //System.out.println(ruletaTotal);
        apuestasCalculos.forEach(apuesta -> apuestaRepository.save(apuesta));
        ruletaService.cerrarRuleta(ruleta_id.get(), totalApuestas);
        //ruletaRepository.save(ruletaTotal);
        return apuestasCalculos;
    }
}
