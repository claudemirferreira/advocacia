package com.setebit.advocacia.repository;

import com.setebit.advocacia.models.Campo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampoRepository extends JpaRepository<Campo, Long> {

    List<Campo> findByNameContaining(String name);

}
