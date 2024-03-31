package com.setebit.advocacia.service;

import com.setebit.advocacia.dto.PalavraChaveValorDTO;
import com.setebit.advocacia.dto.ModeloDocumentoDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import java.io.*;

@Slf4j
@Service
public class FileService {

    public byte[] createDocument(InputStream inputStream, ModeloDocumentoDTO modeloDocumentoDTO) {
        byte[] bytes = null;
        try {
            OutputStream os;
            try (XWPFDocument docx = new XWPFDocument(inputStream)) {
                for (XWPFParagraph p : docx.getParagraphs()) {
                    for (XWPFRun r : p.getRuns()) {
                        String text = r.getText(0);
                        replaceText( text, modeloDocumentoDTO, r);
                    }
                }
                os = new FileOutputStream("novooutput.docx");
                docx.write(os);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                docx.write(byteArrayOutputStream);
                bytes = byteArrayOutputStream.toByteArray();
            }
            inputStream.close();
            os.close();
        } catch (IOException e) {
            log.error("{}", e.getMessage());
            e.printStackTrace();
        }
        return bytes;
    }

    public void replaceText(String text, ModeloDocumentoDTO modeloDocumentoDTO, XWPFRun r){
        for (PalavraChaveValorDTO campoValorDTO: modeloDocumentoDTO.getCamposValor()){
            if (text != null && text.contains(campoValorDTO.getNome())) {
                text = text.replace(campoValorDTO.getNome(), campoValorDTO.getValor());
                r.setText(text, 0);
            }
        }
    }

}
