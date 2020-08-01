package org.ideacreation.seminarium.converter;

import lombok.RequiredArgsConstructor;
import org.ideacreation.seminarium.domain.model.VaccinationEntity;
import org.ideacreation.seminarium.model.VaccinationDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class VaccinationEntityConverter implements Converter<VaccinationEntity, VaccinationDto> {
    @Override
    public VaccinationDto convert(MappingContext<VaccinationEntity, VaccinationDto> context) {
        return new VaccinationDto(
                context.getSource().getVaccine().getVaccineName(),
                context.getSource().getVaccine().getDescription(),
                context.getSource().getVaccinationDate()
        );
    }
}
