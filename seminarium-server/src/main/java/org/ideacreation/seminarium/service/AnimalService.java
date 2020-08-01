package org.ideacreation.seminarium.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.ideacreation.seminarium.domain.model.AnimalEntity;
import org.ideacreation.seminarium.domain.model.DiseaseEntity;
import org.ideacreation.seminarium.domain.model.VaccinationEntity;
import org.ideacreation.seminarium.domain.model.VaccineEntity;
import org.ideacreation.seminarium.domain.repository.AnimalRepository;
import org.ideacreation.seminarium.domain.repository.DiseaseRepository;
import org.ideacreation.seminarium.domain.repository.VaccinationRepository;
import org.ideacreation.seminarium.domain.repository.VaccineRepository;
import org.ideacreation.seminarium.exception.CustomException;
import org.ideacreation.seminarium.model.AnimalDto;
import org.ideacreation.seminarium.model.DateDto;
import org.ideacreation.seminarium.model.DiseaseDto;
import org.ideacreation.seminarium.model.OwnerDto;
import org.ideacreation.seminarium.model.request.AnimalRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final VaccineRepository vaccineRepository;
    private final VaccinationRepository vaccinationRepository;
    private final EntityManagerFactory entityManagerFactory;
    private final ModelMapper modelMapper;
    private final DiseaseRepository diseaseRepository;

    public List<AnimalDto> getAll() {
        return animalRepository.findAll().stream().map(animalEntity -> modelMapper.map(animalEntity, AnimalDto.class)).collect(Collectors.toList());
    }

    public AnimalDto createAnimal(AnimalDto animalDto) {
        AnimalEntity animalEntity = modelMapper.map(animalDto, AnimalEntity.class);
        AnimalEntity saved = animalRepository.save(animalEntity);
        return modelMapper.map(saved, AnimalDto.class);
    }

    public AnimalDto getAnimal(Long id) {
        AnimalEntity animalEntity = animalRepository.findById(id).orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND));
        return modelMapper.map(animalEntity, AnimalDto.class);
    }

    public AnimalDto update(AnimalRequest animalDto) {
        AnimalEntity animalEntity = animalRepository.findById(animalDto.getId()).orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND));
        modelMapper.map(animalDto, animalEntity);
        AnimalEntity saved = animalRepository.save(animalEntity);
        return modelMapper.map(saved, AnimalDto.class);
    }

    public List<AnimalDto> history(Long id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        AuditQuery q = auditReader.createQuery().forRevisionsOfEntity(AnimalEntity.class, true, true);
        q.add(AuditEntity.id().eq(id));
        List<AnimalEntity> audit = q.getResultList();

//        AnimalEntity original = animalRepository.findById(id).orElseThrow(() -> new RuntimeException("Original record not found"));
//        audit.forEach(serviceRequestEntity -> serviceRequestEntity.setCreator(original.getCreator()));

        return audit.stream().map(animalEntity -> modelMapper.map(animalEntity, AnimalDto.class)).collect(Collectors.toList());
    }

    public AnimalDto addPhoto(Long id, String photo) {
        AnimalEntity animalEntity = animalRepository.findById(id).orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND));
        animalEntity.getPhotoIds().add(photo);
        AnimalEntity saved = animalRepository.save(animalEntity);
        return modelMapper.map(saved, AnimalDto.class);
    }

    public AnimalDto removePhoto(Long id, String photo) {
        AnimalEntity animalEntity = animalRepository.findById(id).orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND));
        animalEntity.getPhotoIds().remove(photo);
        AnimalEntity saved = animalRepository.save(animalEntity);
        return modelMapper.map(saved, AnimalDto.class);
    }

    public AnimalDto sterilize(Long id, DateDto dateDto) {
        AnimalEntity animalEntity = animalRepository.findById(id).orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND));
        animalEntity.setSterilized(true);
        animalEntity.setSterilizationDate(dateDto.getDate());
        AnimalEntity saved = animalRepository.save(animalEntity);
        return modelMapper.map(saved, AnimalDto.class);
    }

    public AnimalDto vaccinate(Long id, Long vaccineId, DateDto dateDto) {
        AnimalEntity animalEntity = animalRepository.findById(id).orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND));
        VaccineEntity vaccineEntity = vaccineRepository.findById(vaccineId).orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND));
        VaccinationEntity saved = vaccinationRepository.save(new VaccinationEntity(vaccineEntity, animalEntity, dateDto.getDate()));
        return modelMapper.map(saved.getAnimal(), AnimalDto.class);
    }

    public AnimalDto addDisease(Long id, DiseaseDto diseaseDto) {
        AnimalEntity animalEntity = animalRepository.findById(id).orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND));
        DiseaseEntity map = modelMapper.map(diseaseDto, DiseaseEntity.class);
        map.setAnimal(animalEntity);
        return modelMapper.map(diseaseRepository.save(map).getAnimal(), AnimalDto.class);
    }

    public AnimalDto addOwner(Long id, OwnerDto ownerDto) {
        AnimalEntity animalEntity = animalRepository.findById(id).orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND));
        animalEntity.getOwner().setFio(ownerDto.getFio());
        animalEntity.getOwner().setOrderDate(ownerDto.getOrderDate());
        animalEntity.getOwner().setOrderNumber(ownerDto.getOrderNumber());
        animalEntity.getOwner().setPhone(ownerDto.getPhone());
        AnimalEntity saved = animalRepository.save(animalEntity);
        return modelMapper.map(saved, AnimalDto.class);
    }
}
