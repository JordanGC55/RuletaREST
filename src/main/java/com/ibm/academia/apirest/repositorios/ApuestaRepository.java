package com.ibm.academia.apirest.repositorios;

import com.ibm.academia.apirest.modelos.entidades.Apuesta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApuestaRepository extends CrudRepository<Apuesta, Integer> {

    @Query("select a from Apuesta a join fetch a.ruleta r where r.id=?1")
    public Iterable<Apuesta> buscarApuestasPorRuleta(Integer ruletaId);
}
