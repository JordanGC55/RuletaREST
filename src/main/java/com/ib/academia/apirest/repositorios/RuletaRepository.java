package com.ib.academia.apirest.repositorios;

import com.ib.academia.apirest.modelos.entidades.Ruleta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RuletaRepository extends CrudRepository<Ruleta, Integer> {

    public Optional<Ruleta> findTopByOrderByIdDesc();
}
