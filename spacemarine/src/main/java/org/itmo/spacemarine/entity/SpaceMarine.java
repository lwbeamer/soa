package org.itmo.spacemarine.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.itmo.spacemarine.model.AstartesCategory;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;


@Setter
@Getter
@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceMarine {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "coordinates_id", referencedColumnName = "id", nullable = false)
    private Coordinates coordinates;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "health", nullable = false)
    @Min(0)
    private Long health;

    @Column(name = "achievements", nullable = false)
    private String achievements;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private AstartesCategory category;

    @Column(name = "weapon_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Weapon weaponType;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "chapter_id", referencedColumnName = "id")
    private Chapter chapter;

    @ManyToOne
    @JoinColumn(name = "starship_id")
    private Starship starship;
}
