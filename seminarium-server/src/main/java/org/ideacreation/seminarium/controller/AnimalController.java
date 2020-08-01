package org.ideacreation.seminarium.controller;

import lombok.RequiredArgsConstructor;
import org.ideacreation.seminarium.model.AnimalDto;
import org.ideacreation.seminarium.model.DateDto;
import org.ideacreation.seminarium.model.DiseaseDto;
import org.ideacreation.seminarium.model.OwnerDto;
import org.ideacreation.seminarium.model.request.AnimalRequest;
import org.ideacreation.seminarium.service.AnimalService;
import org.ideacreation.seminarium.service.PhotoStorageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/animals")
@RestController
public class AnimalController {

    private final AnimalService animalService;
    private final PhotoStorageService photoStorageService;

    @GetMapping
    public List<AnimalDto> getAll() {
        return animalService.getAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HELPER')")
    @PostMapping
    public AnimalDto createAnimal(AnimalDto animalDto) {
        return animalService.createAnimal(animalDto);
    }

    @GetMapping("/{id}")
    public AnimalDto getById(@PathVariable("id") Long id) {
        return animalService.getAnimal(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HELPER')")
    @PutMapping("/{id}")
    public AnimalDto getById(@PathVariable("id") Long id, @RequestBody AnimalRequest animalDto) {
        animalDto.setId(id);
        return animalService.update(animalDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HELPER')")
    @PutMapping("/{id}/sterilize")
    public AnimalDto sterilize(@PathVariable("id") Long id, @RequestBody DateDto dateDto) {
        return animalService.sterilize(id, dateDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HELPER')")
    @PutMapping("/{id}/vaccinate/{vacсineId}")
    public AnimalDto vaccinate(@PathVariable("id") Long id, @PathVariable("vacсineId") Long vaccineId, @RequestBody DateDto dateDto) {
        return animalService.vaccinate(id, vaccineId, dateDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HELPER')")
    @PostMapping("/{id}/photo")
    public AnimalDto addPhoto(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        String fileName = photoStorageService.storeFile(file);
        return animalService.addPhoto(id, fileName);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HELPER')")
    @DeleteMapping("/{id}/photo/{photo:.+}}")
    public AnimalDto removePhoto(@PathVariable("id") Long id, @PathVariable("photo") String photo) {
        photoStorageService.delete(photo);
        return animalService.removePhoto(id, photo);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HELPER')")
    @PostMapping("/{id}/disease")
    public AnimalDto addDisease(@PathVariable("id") Long id, @RequestBody DiseaseDto diseaseDto) {
        return animalService.addDisease(id, diseaseDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HELPER')")
    @PutMapping("/{id}/owner")
    public AnimalDto changeOwner(@PathVariable("id") Long id, @RequestBody OwnerDto ownerDto) {
        return animalService.addOwner(id, ownerDto);
    }
}
