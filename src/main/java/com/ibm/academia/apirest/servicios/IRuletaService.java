package com.ibm.academia.apirest.servicios;

import com.ibm.academia.apirest.modelos.entidades.Ruleta;

import java.util.Optional;

public interface IRuletaService {

    public Optional<Ruleta> crearRuleta();

    public Optional<Ruleta> buscarPorId(Integer id);

    public Ruleta actualizar(Ruleta ruletaEncontrada);

    public Iterable<Ruleta> buscarTodas();

    public void cerrarRuleta(Ruleta ruleta, Double total);

}
