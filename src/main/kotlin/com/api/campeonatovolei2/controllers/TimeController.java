package com.api.campeonatovolei2.controllers;

import com.api.campeonatovolei2.dtos.TimeDto;
import com.api.campeonatovolei2.interfaces.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    TimeService timeService;

    @PostMapping
    public ResponseEntity<Object> adicionarTime(@RequestBody TimeDto time){
        return ResponseEntity.status(HttpStatus.CREATED).body(timeService.adicionarTime(time));
    }

    @GetMapping
    public ResponseEntity<Object> listarTimes(){
        return ResponseEntity.status(HttpStatus.FOUND).body(timeService.listarTimes());
    }

}