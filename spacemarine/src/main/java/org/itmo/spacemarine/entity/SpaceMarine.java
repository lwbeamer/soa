package org.itmo.spacemarine.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.itmo.spacemarine.model.AstartesCategory;
import org.itmo.spacemarine.model.Weapon;

import java.time.OffsetDateTime;

@Entity
@Table(name="space_marine")
@Data
public class SpaceMarine {
    @Id
    private Long id;
    private String name;
    private Double x;
    private Double y;
    private OffsetDateTime creationDate;
    private Long health;
    private String achievements;
    private AstartesCategory category ;
    private Weapon weaponType;
    private String chapterName;
    private String chapterWorld;
}
