package com.setebit.advocacia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModeloDocumentoDTO {
    private Long id;
    private List<CampoValorDTO> camposValor;
}
