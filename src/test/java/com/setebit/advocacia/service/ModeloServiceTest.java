package com.setebit.advocacia.service;


import com.setebit.advocacia.models.Modelo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
public class ModeloServiceTest {

    @Autowired
    ModeloService modeloService;

    @Autowired
    FileService fileService;

//    @Test
//    public void save_blob() throws IOException {
//        Modelo modelo = modeloService.findById(3l);
//        InputStream inputStream = new ByteArrayInputStream(modelo.getFile());
//        fileService.createDocument(inputStream);
//
//    }

}
