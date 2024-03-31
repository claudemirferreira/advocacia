package com.setebit.advocacia.models;

import jakarta.persistence.*;
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
@Entity(name = "CAMPO")
public class Campo {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_CAMPO")
    @SequenceGenerator(sequenceName = "S_CAMPO", allocationSize = 1, name = "S_CAMPO")
    @Id
    @Column(name = "CAM_ID")
    private Long id;

    @Column(name = "CAM_NAME", length = 50, unique = true, nullable = false)
    private String name;

}
