package com.api.campeonatovolei2.controllers;

import com.api.campeonatovolei2.dtos.CriarCampeonatoDto;
import com.api.campeonatovolei2.dtos.FinalizarCampeonatoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/campeonato")
public class CampeonatoController {
    @Autowired
    CampeonatoService campeonatoService;

    @PostMapping("/novo")
    public ResponseEntity<Object> adicionarTime(@RequestBody CriarCampeonatoDto model){
        return ResponseEntity.status(HttpStatus.CREATED).body(campeonatoService.criarCampeonato(model));
    }

    @GetMapping("/lista")
    public ResponseEntity<Object> listarCampeonatos(){
        return ResponseEntity.status(HttpStatus.FOUND).body(campeonatoService.listarCampeonatos());
    }

    @PostMapping("/finalizar")
    public ResponseEntity<Object>  finalizarCampeonato(@RequestBody FinalizarCampeonatoDto model)  {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(campeonatoService.finalizarCampeonato(model));

        } catch (IllegalArgumentException  e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/tabela/{id}")
    public ResponseEntity<Object> listarCampeonatos(@PathVariable(value = "id") Integer id){

        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(campeonatoService.tabela(id));

        } catch (IllegalArgumentException  e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
