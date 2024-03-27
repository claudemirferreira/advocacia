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

@RestController
@RequestMapping("/campo/")
@CrossOrigin
public class CampoController {

    @Autowired
    private CampoService campoService;

    @GetMapping
    public List<Campo> findAll() {
        List<Campo> list = campoService.findAll();
        return list;
    }

    @PostMapping
    public Campo save(@RequestBody Campo Campo) {
        return campoService.save(Campo);
    }

    @PutMapping("/{id}")
    public Campo save(@PathVariable Long id, @RequestBody Campo campo) {
        campo.setId(id);
        return campoService.save(campo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        campoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
