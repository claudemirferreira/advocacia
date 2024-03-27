package com.setebit.advocacia.models;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ModeloCampoId implements Serializable {

    @EqualsAndHashCode.Include
    private Long modeloId;

    @EqualsAndHashCode.Include
    private Long campoId;

}
