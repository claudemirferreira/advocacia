package com.setebit.advocacia.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

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

    @NotBlank(message = "O nome do modelo não pode estar em branco")
    @Size(min = 1, max = 100, message = "O nome do modelo deve ter entre 1 e 100 caracteres")
    @Column(name = "MOD_NAME", length = 100, nullable = false)
    private String name;

    @OneToMany
    private List<ItemModelo> itens;
}
