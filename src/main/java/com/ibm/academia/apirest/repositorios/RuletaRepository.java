package com.ibm.academia.apirest.repositorios;

import com.ibm.academia.apirest.modelos.entidades.Ruleta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RuletaRepository extends CrudRepository<Ruleta, Integer> {

    public Optional<Ruleta> findTopByOrderByIdDesc();
}
