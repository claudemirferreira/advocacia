package com.setebit.advocacia.controller;

import com.setebit.advocacia.models.ItemModelo;
import com.setebit.advocacia.service.ItemModeloCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itemmodelos")
public class ItemModeloCrudController {

    @Autowired
    private ItemModeloCrudService itemModeloCrudService;

    @GetMapping
    public ResponseEntity<List<ItemModelo>> listarItensModelo() {
        List<ItemModelo> itensModelo = itemModeloCrudService.buscarTodosItensModelo();
        return ResponseEntity.ok(itensModelo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemModelo> obterItemModeloPorId(@PathVariable Long id) {
        Optional<ItemModelo> itemModelo = itemModeloCrudService.buscarItemModeloPorId(id);
        return itemModelo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemModelo> criarItemModelo(@RequestBody ItemModelo itemModelo) {
        ItemModelo novoItemModelo = itemModeloCrudService.salvarItemModelo(itemModelo);
        return ResponseEntity.ok(novoItemModelo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemModelo> atualizarItemModelo(@PathVariable Long id, @RequestBody ItemModelo itemModelo) {
        Optional<ItemModelo> itemModeloOptional = itemModeloCrudService.buscarItemModeloPorId(id);
        if (!itemModeloOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        itemModelo.setId(id); // Define o ID do item do modelo
        ItemModelo itemModeloAtualizado = itemModeloCrudService.atualizarItemModelo(itemModelo); // Atualiza o item do modelo no banco de dados
        return ResponseEntity.ok(itemModeloAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItemModelo(@PathVariable Long id) {
        if (!itemModeloCrudService.buscarItemModeloPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        itemModeloCrudService.deletarItemModeloPorId(id);
        return ResponseEntity.noContent().build();
    }
}
