package org.ideacreation.seminarium.controller;

import lombok.RequiredArgsConstructor;
import org.ideacreation.seminarium.model.DiseaseDto;
import org.ideacreation.seminarium.service.DiseaseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HELPER')")
@RequiredArgsConstructor
@RequestMapping("/diseases")
@RestController
public class DiseaseController {

    private final DiseaseService diseaseService;

    @PutMapping("/{id}")
    public DiseaseDto updateDiseaseStatus(@PathVariable("id") Long id, @RequestBody DiseaseDto diseaseDto) {
        diseaseDto.setId(id);
        return diseaseService.updateDisease(diseaseDto);
    }
}
