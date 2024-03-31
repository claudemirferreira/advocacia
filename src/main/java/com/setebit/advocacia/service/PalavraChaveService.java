package com.setebit.advocacia.service;

import com.setebit.advocacia.models.PalavraChave;
import com.setebit.advocacia.repository.PalavraChaveRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class PalavraChaveService {

    @Autowired
    private PalavraChaveRepository palavraRepository;

    public List<PalavraChave> find(String name) {
        if (Objects.nonNull(name)){
            return findByNameContaining(name);
        }
        return palavraRepository.findAll();
    }

    public List<PalavraChave> findAll() {
        return palavraRepository.findAll();
    }

    public List<PalavraChave> findByNameContaining(String name) {
        return palavraRepository.findByNameContaining(name);
    }

    public PalavraChave save(PalavraChave entity) {
        return palavraRepository.save(entity);
    }

    public PalavraChave findById(Long id) {
        return palavraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Campo not found with id " + id));
    }

    public void deleteById(Long id) {
        palavraRepository.deleteById(id);
    }

}
