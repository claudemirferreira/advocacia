package com.setebit.advocacia.controller;

import com.setebit.advocacia.models.Modelo;
import com.setebit.advocacia.service.ModeloService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modelo/")
@CrossOrigin(origins = "*")
public class ModeloController {

    @Autowired
    private ModeloService modeloService;

    @GetMapping(value = "{id}/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getFile(@PathVariable("id") Long id) throws InvalidFormatException {
        return modeloService.generatedDocument(Modelo.builder().id(id).build());
    }

}
