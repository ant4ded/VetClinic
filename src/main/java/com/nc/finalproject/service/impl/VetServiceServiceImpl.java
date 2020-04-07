package com.nc.finalproject.service.impl;

import com.nc.finalproject.model.VetService;
import com.nc.finalproject.repository.VetServiceRepository;
import com.nc.finalproject.service.VetServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for operations with entity {@link VetService}.
 * Implementation of {@link VetServiceService}.
 *
 * @author WildDed
 * @version 1.0
 */

@Service
public class VetServiceServiceImpl implements VetServiceService {
    @Autowired
    private VetServiceRepository vetServiceRepository;

    @Override
    public Optional<VetService> findById(Long id) {
        return vetServiceRepository.findById(id);
    }

    @Override
    public VetService findByName(String name) {
        return vetServiceRepository.findByName(name);
    }

    @Override
    public List<VetService> findAll() {
        return vetServiceRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        vetServiceRepository.deleteById(id);
    }

    @Override
    public <S extends VetService> S saveAndFlush(S s) {
        return vetServiceRepository.saveAndFlush(s);
    }
}
