package com.ib.academia.apirest.servicios;

import com.ib.academia.apirest.modelos.entidades.Ruleta;

import java.util.Optional;

public interface IRuletaService {

    public Optional<Ruleta> crearRuleta();

    public Optional<Ruleta> buscarPorId(Integer id);

    public Ruleta actualizar(Ruleta ruletaEncontrada);
}
