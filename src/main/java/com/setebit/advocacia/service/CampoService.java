package com.setebit.advocacia.service;

import com.setebit.advocacia.models.Campo;
import com.setebit.advocacia.repository.CampoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CampoService {

    @Autowired
    private CampoRepository campoRepository;

    public List<Campo> findAll() {
        return campoRepository.findAll();
    }

    public Campo save(Campo entity) {
        return campoRepository.save(entity);
    }

    public Campo findById(Long id) {
        return campoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Campo not found with id " + id));
    }

    public void deleteById(Long id) {
        campoRepository.deleteById(id);
    }

}
