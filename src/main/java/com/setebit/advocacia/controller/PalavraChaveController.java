package com.setebit.advocacia.controller;

import com.setebit.advocacia.models.PalavraChave;
import com.setebit.advocacia.service.PalavraChaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/palavra-chave/")
public class PalavraChaveController {

    @Autowired
    private PalavraChaveService palavraChaveService;

    @GetMapping
    public ResponseEntity<List<PalavraChave>> find(@RequestParam(required = false) String name) {
        return ResponseEntity.ok().body(palavraChaveService.find(name));
    }

    @PostMapping
    public ResponseEntity<PalavraChave> save(@RequestBody PalavraChave campo) {
        return ResponseEntity.ok().body(palavraChaveService.save(campo));
    }

    @PutMapping("/{id}")
    public PalavraChave save(@PathVariable Long id, @RequestBody PalavraChave campo) {
        campo.setId(id);
        return palavraChaveService.save(campo);
    }

    @GetMapping("/{id}")
    public PalavraChave findById(@PathVariable Long id) {
        return palavraChaveService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        palavraChaveService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
