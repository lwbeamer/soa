package org.itmo.spacemarine.repository;

import org.itmo.spacemarine.entity.Starship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarshipRepository extends JpaRepository<Starship, Long> {


}
