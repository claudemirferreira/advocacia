package com.setebit.advocacia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "ITE_MODELO")
public class ItemModelo {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ITE_MOD")
    @SequenceGenerator(sequenceName = "S_ITE_MOD", allocationSize = 1, name = "S_ITE_MOD")
    @Id
    @Column(name = "ITE_MOD_ID")
    private Long id;

    @Column(name = "ITE_MOD_DESCRIPTION", columnDefinition = "text", nullable = false)
    private String description;

    @Column(name = "ITE_MOD_SEQUENCE", nullable = false)
    private Integer sequence;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MOD_ID")
    private Modelo modelo;

}
