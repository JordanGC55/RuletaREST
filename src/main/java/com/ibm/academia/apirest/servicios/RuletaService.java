package com.ibm.academia.apirest.servicios;

import com.ibm.academia.apirest.modelos.entidades.Ruleta;
import com.ibm.academia.apirest.repositorios.RuletaRepository;
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

    @Override
    public Iterable<Ruleta> buscarTodas() {
        return ruletaRepository.findAll();
    }

    @Override
    public void cerrarRuleta(Ruleta ruleta, Double total) {
        ruleta.setTotal(total);
        ruleta.setEstado(false);
        Ruleta rulAct = ruletaRepository.save(ruleta);
        System.out.println(rulAct);
    }
}
