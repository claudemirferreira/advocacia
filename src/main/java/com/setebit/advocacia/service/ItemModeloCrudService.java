package com.setebit.advocacia.service;

import com.setebit.advocacia.models.ItemModelo;
import com.setebit.advocacia.repository.ItemModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ItemModeloCrudService {

    @Autowired
    private ItemModeloRepository itemModeloRepository;

    public ItemModelo salvarItemModelo(ItemModelo itemModelo) {
        return itemModeloRepository.save(itemModelo);
    }

    public List<ItemModelo> buscarTodosItensModelo() {
        return itemModeloRepository.findAll();
    }

    public Optional<ItemModelo> buscarItemModeloPorId(Long id) {
        return itemModeloRepository.findById(id);
    }

    public void deletarItemModeloPorId(Long id) {
        itemModeloRepository.deleteById(id);
    }

    public ItemModelo atualizarItemModelo(ItemModelo itemModelo) {
        return itemModeloRepository.save(itemModelo);
    }
}
