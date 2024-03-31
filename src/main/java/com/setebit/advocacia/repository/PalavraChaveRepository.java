package com.setebit.advocacia.repository;

import com.setebit.advocacia.models.PalavraChave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PalavraChaveRepository extends JpaRepository<PalavraChave, Long> {

    List<PalavraChave> findByNameContaining(String name);

}
