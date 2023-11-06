package org.itmo.spacemarine.repository;

import org.itmo.spacemarine.entity.SpaceMarine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SpaceMarineRepository extends JpaRepository<SpaceMarine, Long> {


}
