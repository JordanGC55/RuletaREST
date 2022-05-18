package com.ib.academia.apirest.servicios;

import com.ib.academia.apirest.modelos.entidades.Ruleta;
import com.ib.academia.apirest.repositorios.RuletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RuletaService implements IRuletaService{

    @Autowired
    private RuletaRepository ruletaRepository;

    @Override
    public Optional<Ruleta> crearRuleta() {

        Ruleta ruleta = new Ruleta();
        ruleta.setEstado(false);
        ruleta.setAutor("Jordan");
        ruleta.setTotal(0.0);
        ruletaRepository.save(ruleta);
        return ruletaRepository.findTopByOrderByIdDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Ruleta> buscarPorId(Integer id) {
        return ruletaRepository.findById(id);
    }


    @Override
    @Transactional
    public Ruleta actualizar(Ruleta ruletaEncontrada) {
        Ruleta ruletaActualizar = null;
        ruletaEncontrada.setEstado(true);
        ruletaActualizar = ruletaRepository.save(ruletaEncontrada);
        return ruletaActualizar;
    }
}
