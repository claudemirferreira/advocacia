package com.setebit.advocacia.controller;

import com.setebit.advocacia.models.Campo;
import com.setebit.advocacia.models.Modelo;
import com.setebit.advocacia.service.CampoService;
import com.setebit.advocacia.service.ModeloService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/campo/")
public class CampoController {

    @Autowired
    private CampoService campoService;

    @GetMapping
    public ResponseEntity<List<Campo>> find(@RequestParam(required = false) String name) {
        return ResponseEntity.ok().body(campoService.find(name));
    }

    @PostMapping
    public ResponseEntity<Campo> save(@RequestBody Campo campo) {
        return ResponseEntity.ok().body(campoService.save(campo));
    }

    @PutMapping("/{id}")
    public Campo save(@PathVariable Long id, @RequestBody Campo campo) {
        campo.setId(id);
        return campoService.save(campo);
    }

    @GetMapping("/{id}")
    public Campo findById(@PathVariable Long id) {
        return campoService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        campoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
