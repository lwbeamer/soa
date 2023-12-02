package org.itmo.spacemarine.service;

import lombok.RequiredArgsConstructor;
import org.itmo.spacemarine.dto.StarshipCreateRequestDto;
import org.itmo.spacemarine.entity.Starship;
import org.itmo.spacemarine.exception.BusinessException;
import org.itmo.spacemarine.exception.ExceptionCode;
import org.itmo.spacemarine.repository.StarshipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StarshipService {

    private final StarshipRepository starshipRepository;

    public Starship getById(Long id) {
        return starshipRepository.findById(id).orElseThrow(() -> new BusinessException(ExceptionCode.StarshipNotFound, "Starship с таким id не найден!"));
    }

    public void addStarship(StarshipCreateRequestDto requestDto) {
        Starship starship = Starship.builder()
                .width(requestDto.getWidth())
                .height(requestDto.getHeight())
                .name(requestDto.getName())
                .build();

        starshipRepository.save(starship);
    }

    public void deleteStarship(long longId) {
        Optional<Starship> starshipOptional = starshipRepository.findById(longId);
        if (starshipOptional.isEmpty()) throw new BusinessException(ExceptionCode.SpaceMarineNotFound, "Starship с таким id не найден!");
        starshipRepository.delete(starshipOptional.get());
    }


    public List<Starship> getAll() {
        return starshipRepository.findAll();
    }
}
