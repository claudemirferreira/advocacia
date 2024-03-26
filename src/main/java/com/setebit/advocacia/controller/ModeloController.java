package com.setebit.advocacia.controller;

import com.setebit.advocacia.models.Modelo;
import com.setebit.advocacia.service.ModeloService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

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

    @GetMapping(value = "/documento", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getDocumento() {
        try {
            XWPFDocument documento = new XWPFDocument();

            // Cabeçalho
            XWPFHeader header = documento.createHeader(HeaderFooterType.DEFAULT);
            XWPFParagraph headerParagraph = header.createParagraph();
            XWPFRun headerRun = headerParagraph.createRun();
            headerRun.setText("Este é o cabeçalho do documento");

            // Adicionando nota de rodapé
            XWPFFootnote footnote = documento.createFootnote();
            XWPFParagraph footnoteParagraph = footnote.createParagraph();
            XWPFRun footnoteRun = footnoteParagraph.createRun();
            footnoteRun.setText("Nota de rodapé: Esta é uma nota de rodapé.");

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            documento.write(outputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("filename", "documento_com_nota_de_rodape.docx");

            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
