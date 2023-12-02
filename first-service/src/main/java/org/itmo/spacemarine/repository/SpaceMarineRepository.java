package org.itmo.spacemarine.repository;

import org.itmo.spacemarine.entity.SpaceMarine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpaceMarineRepository extends JpaRepository<SpaceMarine, Long> {
    @Query(value = """
        select sum(health) from space_marine
    """, nativeQuery = true)
    Long getHealthSum();
}
