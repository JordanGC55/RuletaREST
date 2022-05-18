package com.ib.academia.apirest.repositorios;

import com.ib.academia.apirest.modelos.entidades.Apuesta;
import org.springframework.data.repository.CrudRepository;

public interface ApuestaRepository extends CrudRepository<Apuesta, Integer> {
}
