package com.ib.academia.apirest.controladores;

import com.ib.academia.apirest.excepciones.NotFoundException;
import com.ib.academia.apirest.modelos.entidades.Apuesta;
import com.ib.academia.apirest.modelos.entidades.Ruleta;
import com.ib.academia.apirest.repositorios.ApuestaRepository;
import com.ib.academia.apirest.servicios.IRuletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ruleta")
public class RuletaController {

    @Autowired
    private IRuletaService ruletaService;

    @Autowired
    private ApuestaRepository apuestaRepository;

    /***
     * Endpoint para generar una ruleta
     * @return un id de ruleta generada
     * @author Jordan
     */

    @PostMapping("/insert")
    public ResponseEntity<?> crearRuleta(){

        Optional<Ruleta> ruleta_id = ruletaService.crearRuleta();
        return new ResponseEntity<>("Ruleta creada con Id: " + ruleta_id.get().getId(), HttpStatus.OK);

    }

    /***
     * Endpoint para activar una ruleta
     * @param ruletaId recibe el id de ruleta que se activara
     * @return Respuesta exitosa si esta se activo.
     */

    @PutMapping("/cambiar_estado/{ruletaId}")
    public ResponseEntity<?> cambiarEstado(@PathVariable Integer ruletaId){
        Optional<Ruleta> ruleta_id = ruletaService.buscarPorId(ruletaId);

        if(!ruleta_id.isPresent())
            throw new NotFoundException(String.format("La ruleta con Id: %d no existe", ruletaId));

        Ruleta ruletaActualizar = ruletaService.actualizar(ruleta_id.get());
        return new ResponseEntity<>("Ruleta activada con Id: " + ruletaActualizar.getId(), HttpStatus.OK);
    }


    @PostMapping("/insert-apuesta/{ruletaId}")
    public ResponseEntity<?> insertarApuesta(@PathVariable Integer ruletaId, @Valid @RequestBody Apuesta apuesta, BindingResult result){

        Optional<Ruleta> ruleta_id = ruletaService.buscarPorId(ruletaId);
        if(!ruleta_id.isPresent())
            throw new NotFoundException(String.format("La ruleta con Id: %d no existe", ruletaId));

        Map<String, Object> validaciones = new HashMap<String, Object>();
        if(result.hasErrors()){
            List<String> listaErrorres = result.getFieldErrors()
                    .stream()
                    .map( errores -> "Campo: " + errores.getField() + " " +errores.getDefaultMessage())
                    .collect(Collectors.toList());
            validaciones.put("Lista de Errores", listaErrorres);



            return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);

        }

        //Ruleta ruletaVerificar = ruletaService.actualizar(ruleta_id.get());
        apuesta.setRuleta(ruleta_id.get());
        Apuesta apuestaInsertada = apuestaRepository.save(apuesta);
        return new ResponseEntity<Apuesta>(apuestaInsertada, HttpStatus.OK);
    }




}
