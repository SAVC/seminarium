package org.ideacreation.seminarium.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Audited
@Entity
public class DiseaseEntity extends BaseEntity<Long> {
    @Column
    private String description;

    @Column
    private String receipt;

    @Column
    private LocalDate localDate;

    @Enumerated(EnumType.STRING)
    private DiseaseStatus diseaseStatus;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private AnimalEntity animal;
}
