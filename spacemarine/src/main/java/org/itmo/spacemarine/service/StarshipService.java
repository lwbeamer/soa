package org.itmo.spacemarine.service;


import org.itmo.spacemarine.entity.Starship;
import org.itmo.spacemarine.exception.BusinessException;
import org.itmo.spacemarine.exception.ExceptionCode;
import org.itmo.spacemarine.repository.StarshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StarshipService {


    @Autowired
    private StarshipRepository repository;


    public Starship getStarship(Long id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException(ExceptionCode.StarshipNotFound, "Starship с таким id не найден!"));
    }

}
