package com.setebit.advocacia.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ModeloCampoId.class)
@Table
@Entity(name = "MODELO_CAMPO")
public class ModeloCampo {

    @Id
    @Column(name = "MOD_CAM_ID")
    @EqualsAndHashCode.Include
    private Long modeloId;

    @Id
    @Column(name = "MOD_CAM_ID")
    @EqualsAndHashCode.Include
    private Long campoId;

}
