package com.nc.finalproject.service.impl;

import com.nc.finalproject.model.Pet;
import com.nc.finalproject.repository.PetRepository;
import com.nc.finalproject.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for operations with entity {@link Pet}.
 * Implementation of {@link PetService}.
 *
 * @author WildDed
 * @version 1.0
 */

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    PetRepository petRepository;

    @Override
    public Pet findByName(String name) {
        return petRepository.findByName(name);
    }

    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    @Override
    public <S extends Pet> S saveAndFlush(S s) {
        return petRepository.saveAndFlush(s);
    }

    @Override
    public void deleteById(Long aLong) {
        petRepository.deleteById(aLong);
    }

    @Override
    public List<Pet> findAllByUsername(String username) {
        return petRepository.findAllByUsername(username);
    }
}
