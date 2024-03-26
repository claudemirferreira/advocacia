package com.setebit.advocacia.repository;

import com.setebit.advocacia.models.ItemModelo;
import com.setebit.advocacia.models.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemModeloRepository extends JpaRepository<ItemModelo, Long> {

    List<ItemModelo> findByModelo(Modelo modelo);
}
