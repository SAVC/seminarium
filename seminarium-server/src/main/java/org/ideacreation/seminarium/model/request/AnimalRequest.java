package org.ideacreation.seminarium.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ideacreation.seminarium.domain.model.AnimalStatus;
import org.ideacreation.seminarium.domain.model.AnimalType;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnimalRequest {
    private Long id;

    private String name;

    private Boolean gender; // false == female

    private AnimalType animalType;

    private LocalDate receivedDate;

    private LocalDate birthDate;

    private AnimalStatus animalStatus;

    private LocalDate lastDeWormingDate;

    private String description;
}
