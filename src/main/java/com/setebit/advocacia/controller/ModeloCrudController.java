package com.setebit.advocacia.controller;

import com.setebit.advocacia.models.Modelo;
import com.setebit.advocacia.service.ModeloCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/modelos")
public class ModeloCrudController {

    @Autowired
    private ModeloCrudService modeloCrudService;

    @GetMapping
    public ResponseEntity<List<Modelo>> listarModelos() {
        List<Modelo> modelos = modeloCrudService.buscarTodosModelos();
        return ResponseEntity.ok(modelos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modelo> obterModeloPorId(@PathVariable Long id) {
        Optional<Modelo> modelo = modeloCrudService.buscarModeloPorId(id);
        return modelo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Modelo> criarModelo(@RequestBody Modelo modelo) {
        Modelo novoModelo = modeloCrudService.salvarModelo(modelo);
        return ResponseEntity.ok(novoModelo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modelo> atualizarModelo(@PathVariable Long id, @RequestBody Modelo modelo) {
        if (!modeloCrudService.existeModelo(id)) {
            return ResponseEntity.notFound().build();
        }
        modelo.setId(id); // Define o ID do modelo
        Modelo modeloAtualizado = modeloCrudService.atualizarModelo(modelo); // Atualiza o modelo no banco de dados
        return ResponseEntity.ok(modeloAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarModelo(@PathVariable Long id) {
        if (!modeloCrudService.existeModelo(id)) {
            return ResponseEntity.notFound().build();
        }
        modeloCrudService.deletarModeloPorId(id);
        return ResponseEntity.noContent().build();
    }
}
