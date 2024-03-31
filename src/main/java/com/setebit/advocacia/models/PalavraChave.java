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
@Entity(name = "PALAVRA_CHAVE")
public class PalavraChave {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PAL_CHA")
    @SequenceGenerator(sequenceName = "S_PAL_CHA", allocationSize = 1, name = "S_PAL_CHA")
    @Id
    @Column(name = "PAL_CHA_ID")
    private Long id;

    @Column(name = "PAL_CHA_NAME", length = 50, unique = true, nullable = false)
    private String name;

}
