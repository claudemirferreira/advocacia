// ModeloCrudService
package com.setebit.advocacia.service;

import com.setebit.advocacia.models.Modelo;
import com.setebit.advocacia.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeloCrudService {

    @Autowired
    private ModeloRepository modeloRepository;

        public Modelo salvarModelo(Modelo modelo) {
        return modeloRepository.save(modelo);
    }

    public List<Modelo> buscarTodosModelos() {
        return modeloRepository.findAll();
    }

    public Optional<Modelo> buscarModeloPorId(Long id) {
        return modeloRepository.findById(id);
    }

    public void deletarModeloPorId(Long id) {
        modeloRepository.deleteById(id);
    }

    public boolean existeModelo(Long id) {
        return modeloRepository.existsById(id);
    }

    public Modelo atualizarModelo(Modelo modelo) {
        return modeloRepository.save(modelo);
    }
}
