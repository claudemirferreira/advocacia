package com.setebit.advocacia.controller;

import com.setebit.advocacia.dto.CampoValorDTO;
import com.setebit.advocacia.dto.ModeloDocumentoDTO;
import com.setebit.advocacia.models.Campo;
import com.setebit.advocacia.models.Modelo;
import com.setebit.advocacia.service.ModeloService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/modelo/")
@CrossOrigin(origins = "*")
public class ModeloController {

    @Autowired
    private ModeloService modeloService;

    @GetMapping
    public List<Modelo> findAll() {
        return modeloService.findAll();
    }

    @PostMapping
    public Modelo save(@RequestBody Modelo entity) {
        return modeloService.save(entity);
    }

    @PutMapping("/{id}")
    public Modelo save(@PathVariable Long id, @RequestBody Modelo entity) {
        entity.setId(id);
        return modeloService.save(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        modeloService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{id}/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getFile(@PathVariable("id") Long id) {
        return modeloService.download(id);
    }

    @PostMapping("{id}/savefile")
    public ResponseEntity<Modelo> handleFileUpload(@PathVariable("id") Long id,
                                                   @RequestParam("file") MultipartFile file) throws IOException {
        Modelo modelo = modeloService.saveFile(id, file.getInputStream());
        return ResponseEntity.ok(modelo);
    }

    @GetMapping(value = "campo-valor")
    public ResponseEntity<List<CampoValorDTO>> getCampoValor() {
        return ResponseEntity.ok(modeloService.getCampoValor());
    }

    @PostMapping(value = "campo-valor")
    public ResponseEntity<byte[]> getCampoValor(@RequestBody ModeloDocumentoDTO modeloDocumentoDTO) {
        log.info("{}", modeloDocumentoDTO);
        return modeloService.createDocument(modeloDocumentoDTO);
    }

    @GetMapping("/{id}")
    public Modelo findById(@PathVariable Long id) {
        return modeloService.findById(id);
    }

}
