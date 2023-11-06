package org.itmo.spacemarine.service;


import org.itmo.spacemarine.entity.Starship;
import org.itmo.spacemarine.repository.StarshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StarshipService {


    @Autowired
    private StarshipRepository repository;

    public Starship createStarship(){
        return null;

    }

    public Starship getStarship(Long id){
        return null;
    }




}
