package com.setebit.advocacia.service;

import com.setebit.advocacia.models.ItemModelo;
import com.setebit.advocacia.models.Modelo;
import com.setebit.advocacia.repository.ItemModeloRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Slf4j
@Service
public class ModeloService {

    @Autowired
    private ItemModeloRepository itemModeloRepository;

    public ResponseEntity<byte[]> generatedDocument(Modelo modelo) throws InvalidFormatException {
        try (XWPFDocument documento = new XWPFDocument()) {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                buildBody(documento, modelo);
                documento.write(outputStream);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDispositionFormData("filename", "documento.docx");
                return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
            } catch (IOException e) {
                System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Erro ao criar o documento: " + e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public void buildBody(XWPFDocument documento, Modelo modelo) throws IOException, InvalidFormatException {
        buildXWPFHeader(documento);
        List<ItemModelo> itens = itemModeloRepository.findByModelo(modelo);
        XWPFParagraph paragraph;
        for (ItemModelo itemModelo : itens) {
            paragraph = documento.createParagraph();
            paragraph.createRun().setText(itemModelo.getDescription());
            paragraph = documento.createParagraph();
            paragraph.createRun().setText("\n");
        }
        buildXXWPFFooter(documento);
    }

    public void buildXWPFHeader(XWPFDocument documento) throws IOException, InvalidFormatException {
        XWPFHeader header = documento.createHeader(HeaderFooterType.DEFAULT);
        String pathImage = "static/logo.png";
        Resource resource = new ClassPathResource(pathImage);
        byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());

        XWPFParagraph paragraph = header.createParagraph();
        XWPFRun run = paragraph.createRun();
        int format = XWPFDocument.PICTURE_TYPE_JPEG;
        run.addPicture(new ByteArrayInputStream(imageBytes), format, "NomeDaImagem", Units.toEMU(100), Units.toEMU(100));

        // Adicionar um texto ao cabeçalho
        XWPFParagraph paragraph2 = header.createParagraph();
        XWPFRun run2 = paragraph2.createRun();
        run2.setText("Este é um exemplo de cabeçalho com imagem.");

    }

    public void buildXXWPFFooter(XWPFDocument documento) {
        XWPFFooter footer = documento.createFooter(HeaderFooterType.DEFAULT);
        XWPFParagraph footerParagraph = footer.createParagraph();
        XWPFRun footerRun = footerParagraph.createRun();
        footerRun.setText("Este é o rodapé do documento");
    }

}
