package com.ibm.academia.apirest.controladores;

import com.ibm.academia.apirest.modelos.dto.ApuestaDTO;
import com.ibm.academia.apirest.modelos.dto.RuletaDTO;
import com.ibm.academia.apirest.enums.ColorRuleta;
import com.ibm.academia.apirest.excepciones.BadRequestExeption;
import com.ibm.academia.apirest.excepciones.NotFoundException;
import com.ibm.academia.apirest.modelos.entidades.Apuesta;
import com.ibm.academia.apirest.modelos.entidades.Ruleta;
import com.ibm.academia.apirest.modelos.mapper.ApuestaMapper;
import com.ibm.academia.apirest.modelos.mapper.RuletaMapper;
import com.ibm.academia.apirest.repositorios.ApuestaRepository;
import com.ibm.academia.apirest.repositorios.RuletaRepository;
import com.ibm.academia.apirest.response.Response;
import com.ibm.academia.apirest.servicios.IApuestaService;
import com.ibm.academia.apirest.servicios.IRuletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/ruletas")
public class RuletaController {

    @Autowired
    private IRuletaService ruletaService;

    @Autowired
    private ApuestaRepository apuestaRepository;

    @Autowired
    private RuletaRepository ruletaRepository;

    @Autowired
    private IApuestaService apuestaService;

    public static ColorRuleta colorGanadorDTO;
    public static Integer numeroGanador;

    /***
     * Endpoint para generar una ruleta
     * @return un id de ruleta generada
     * @author Jordan
     */

    @PostMapping
    public ResponseEntity<?> crearRuleta() {

        Optional<Ruleta> ruleta_id = ruletaService.crearRuleta();
        return new ResponseEntity<Response>(Response.builder().mensaje("Ruleta creada con Id: " + ruleta_id.get().getId()).build(), HttpStatus.OK);

    }

    /***
     * Endpoint para activar una ruleta
     * @param ruletaId recibe el id de ruleta que se activara
     * @return Respuesta exitosa si esta se activo.
     */

    @PutMapping("/{ruletaId}/activar-ruleta")
    public ResponseEntity<?> cambiarEstado(@PathVariable Integer ruletaId) {
        Optional<Ruleta> ruleta_id = ruletaService.buscarPorId(ruletaId);

        if (!ruleta_id.isPresent())
            throw new NotFoundException(String.format("La ruleta con Id: %d no existe", ruletaId));

        Ruleta ruletaActualizar = ruletaService.actualizar(ruleta_id.get());
        return new ResponseEntity<Response>(Response.builder().mensaje("Ruleta activada con Id: " + ruletaActualizar.getId()).build(), HttpStatus.OK);
    }


    /***
     * Endpoint para insertar una nueva Apuesta
     * @param ruletaId recibe el id de una ruleta
     * @param apuesta Recibe el body (Datos de una apuesta)
     * @return Respuesta de validacion si se inserto
     */
    @PostMapping("/{ruletaId}/apuestas")
    public ResponseEntity<?> insertarApuesta(@PathVariable Integer ruletaId, @Valid @RequestBody Apuesta apuesta, BindingResult result) {

        Optional<Ruleta> ruleta_id = ruletaService.buscarPorId(ruletaId);
        if (!ruleta_id.isPresent())
            throw new NotFoundException(String.format("La ruleta con Id: %d no existe", ruletaId));

        Map<String, Object> validaciones = new HashMap<String, Object>();
        if (result.hasErrors()) {
            List<String> listaErrorres = result.getFieldErrors()
                    .stream()
                    .map(errores -> "Campo: " + errores.getField() + " " + errores.getDefaultMessage())
                    .collect(Collectors.toList());
            validaciones.put("Lista de Errores", listaErrorres);

            return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
        }

        if (!ruleta_id.get().getEstado()) {
            throw new BadRequestExeption(String.format("La ruleta con Id %d no esta activa", ruletaId));
        } else {
            Ruleta ruletaVerificar = ruleta_id.get();
            apuesta.setRuleta(ruletaVerificar);
            Apuesta apuestaInsertada = apuestaRepository.save(apuesta);
        }


        return new ResponseEntity<Response>(Response.builder().mensaje("Apuesta Exitosa " +
                "$ " + apuesta.getCantidad() + " Dolares al Color: " +
                apuesta.getColorApuesta() + " y numero " + apuesta.getNumeroApuesta() + " en la Ruleta: " + ruletaId).build(), HttpStatus.OK);


    }

    /***
     * Endpoint para enlistar las apuestas y resultados de las mismas de una determinada ruleta
     * @param ruletaId Recibe id de la ruleta para sacar sus apuestas
     * @return Lista de apuestas con sus resultados
     */

    @GetMapping("/{ruletaId}/apuestas")
    public Iterable<Apuesta> listarApuestas(@PathVariable Integer ruletaId) {
        Optional<Ruleta> ruleta_id = ruletaService.buscarPorId(ruletaId);
        if (!ruleta_id.isPresent())
            throw new NotFoundException(String.format("La ruleta con Id: %d no existe", ruletaId));

        ColorRuleta colorGanador;
        Integer volado = (int) (Math.random() * 10) % 2;
        if (volado == 0)
            colorGanador = ColorRuleta.NEGRO;
        else
            colorGanador = ColorRuleta.ROJO;

        Iterable<Apuesta> apuestasEncontradas = apuestaService.cierreApuestas(ruletaId, colorGanador);

        return apuestasEncontradas;

    }

    /***
     * Endpoint para enlistar las apuestas y resultados de las mismas de una determinada ruleta Por DTO
     * @param ruletaId Recibe id de la ruleta para sacar sus apuestas
     * @return Lista de apuestasDTO con sus resultados
     */

    @GetMapping("/{ruletaId}/apuestasdto")
    public ResponseEntity<?> listarApuestasdto(@PathVariable Integer ruletaId) {
        Optional<Ruleta> ruleta_id = ruletaService.buscarPorId(ruletaId);
        if (!ruleta_id.isPresent())
            throw new NotFoundException(String.format("La ruleta con Id: %d no existe", ruletaId));

        Integer volado = (int) (Math.random() * 10) % 2;
        if (volado == 0)
            colorGanadorDTO = ColorRuleta.NEGRO;
        else
            colorGanadorDTO = ColorRuleta.ROJO;

        numeroGanador =  (int)(Math. random()*36+1);
        Iterable<Apuesta> apuestasEncontradas = apuestaService.cierreApuestas(ruletaId, colorGanadorDTO);

        List<ApuestaDTO> apuestasEncontradasDTO = ((List<Apuesta>) apuestasEncontradas)
                .stream()
                .map(ApuestaMapper::mapApuesta)
                .collect(Collectors.toList());


        return new ResponseEntity<List<ApuestaDTO>>(apuestasEncontradasDTO, HttpStatus.OK);

    }


    /***
     * Endpoint para enlistar las ruletas creadas con sus tesados y reultados
     * @return Lista de ruletas creadas
     */
    @GetMapping
    public List<Ruleta> listarRuletas() {

        List<Ruleta> ruletas = (List<Ruleta>) ruletaService.buscarTodas();

        if (ruletas.isEmpty()) {
            throw new BadRequestExeption("No existen carreras");
        }

        return ruletas;
    }

    /***
     *Endpoint para listar ruletas por metodo DTO
     * @return Lista de ruletas
     */

    @GetMapping("/lista/dto")
    public ResponseEntity<?> consultarTodasRuletas() {

        List<Ruleta> ruletas = (List<Ruleta>) ruletaService.buscarTodas();

        if (ruletas.isEmpty()) {
            throw new NotFoundException("No se han encontrado ruletas");
        }

        List<RuletaDTO> ruletasDTO = ruletas
                .stream()
                .map(RuletaMapper::mapRuleta)
                .collect(Collectors.toList());

        return new ResponseEntity<List<RuletaDTO>>(ruletasDTO, HttpStatus.OK);
    }


}
