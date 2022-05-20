package com.ibm.academia.apirest;

import com.ibm.academia.apirest.servicios.IApuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Comandos implements CommandLineRunner {


    @Autowired
    private IApuestaService apuestaService;

    @Override
    public void run(String... args) throws Exception {

        //Iterable<Apuesta> apuestasEncontradas = apuestaService.cierreApuestas(2, ColorRuleta.NEGRO);
        //apuestasEncontradas.forEach(System.out::println);



    }
}
