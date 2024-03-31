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
@Entity(name = "MODELO")
public class Modelo {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_MODELO")
    @SequenceGenerator(sequenceName = "S_MODELO", allocationSize = 1, name = "S_MODELO")
    @Id
    @Column(name = "MOD_ID")
    private Long id;

    @Column(name = "MOD_NAME", unique = true, length = 100, nullable = false)
    private String name;

    @Lob
    @Column(name = "MOD_FILE")
    private byte[] file;
}
