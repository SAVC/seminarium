package org.ideacreation.seminarium.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OwnerDto {
    private String fio;

    private String phone;

    private String orderNumber;

    private LocalDate orderDate;
}
