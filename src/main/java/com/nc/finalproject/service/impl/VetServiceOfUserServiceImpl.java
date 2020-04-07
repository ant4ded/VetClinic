package com.nc.finalproject.service.impl;

import com.nc.finalproject.model.VetServiceOfUser;
import com.nc.finalproject.repository.VetServiceOfUserRepository;
import com.nc.finalproject.service.VetServiceOfUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for operations with entity {@link VetServiceOfUser}.
 * Implementation of {@link VetServiceOfUserService}.
 *
 * @author WildDed
 * @version 1.0
 */

@Service
public class VetServiceOfUserServiceImpl implements VetServiceOfUserService {
    @Autowired
    private VetServiceOfUserRepository vetServiceOfUserRepository;

    @Override
    public List<VetServiceOfUser> findAllById(Long id) {
        return vetServiceOfUserRepository.findAllById(id);
    }

    @Override
    public List<VetServiceOfUser> findAllByNameDoctorAndDate(String nameDoctor, String date) {
        return vetServiceOfUserRepository.findAllByNameDoctorAndDate(nameDoctor, date);
    }

    @Override
    public Optional<VetServiceOfUser> findById(Long aLong) {
        return vetServiceOfUserRepository.findById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        vetServiceOfUserRepository.deleteById(aLong);
    }

    @Override
    public <S extends VetServiceOfUser> S saveAndFlush(S s) {
        return vetServiceOfUserRepository.saveAndFlush(s);
    }
}
