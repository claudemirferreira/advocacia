package com.setebit.advocacia.service;

import com.setebit.advocacia.dto.CampoValorDTO;
import com.setebit.advocacia.dto.ModeloDocumentoDTO;
import com.setebit.advocacia.models.Campo;
import com.setebit.advocacia.models.Modelo;
import com.setebit.advocacia.repository.CampoRepository;
import com.setebit.advocacia.repository.ModeloRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;
    @Autowired
    private CampoRepository campoRepository;

    @Autowired
    private FileService fileService;


    public Modelo saveFile(Long id, InputStream inputStream) throws IOException {
        Modelo modelo = findById(id);
        modelo.setFile(inputStream.readAllBytes());
        return save(modelo);
    }

    public Modelo save(Modelo entity) {
        try {
            Modelo modelo = findById(entity.getId());
            modelo.setFile(entity.getFile());
            return modeloRepository.save(modelo);
        } catch (Exception e){
            return modeloRepository.save(entity);
        }

    }

    public Modelo findById(Long id) {
        return modeloRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Modelo not found with id " + id));
    }

    public ResponseEntity<byte[]> createDocument(ModeloDocumentoDTO modeloDocumentoDTO) {
        Modelo modelo = findById(modeloDocumentoDTO.getId());
        InputStream inputStream = new ByteArrayInputStream(modelo.getFile());
        byte[] bytes = fileService.createDocument(inputStream, modeloDocumentoDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("filename", "documento.docx");
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    public ResponseEntity<byte[]> download(Long id) {
        Modelo modelo = findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("filename", "documento.docx");
        return new ResponseEntity<>(modelo.getFile(), headers, HttpStatus.OK);
    }

    public List<Modelo> findAll() {
        return modeloRepository.findAll();
    }

    public void deleteById(Long id) {
        modeloRepository.deleteById(id);
    }

    public List<CampoValorDTO> getCampoValor(){
        List<CampoValorDTO> camposValor = new ArrayList<>();
        List<Campo> campos = campoRepository.findAll();
        campos.forEach( campo -> {
            camposValor.add(new CampoValorDTO(campo.getName(), ""));
        });
        return camposValor;
    }

}
