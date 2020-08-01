package org.ideacreation.seminarium.converter;

import lombok.RequiredArgsConstructor;
import org.ideacreation.seminarium.domain.model.AnimalEntity;
import org.ideacreation.seminarium.model.request.AnimalRequest;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Component
public class AnimalRequestConverter implements Converter<AnimalRequest, AnimalEntity> {

    @Override
    public AnimalEntity convert(MappingContext<AnimalRequest, AnimalEntity> context) {
        Optional.ofNullable(context.getSource().getName()).ifPresent(name -> context.getDestination().setName(name));
        Optional.ofNullable(context.getSource().getBirthDate()).ifPresent(localDate -> context.getDestination().setBirthDate(localDate));
        Optional.ofNullable(context.getSource().getReceivedDate()).ifPresent(localDate -> context.getDestination().setReceivedDate(localDate));
        Optional.ofNullable(context.getSource().getLastDeWormingDate()).ifPresent(localDate -> context.getDestination().setLastDeWormingDate(localDate));
        Optional.ofNullable(context.getSource().getAnimalStatus()).ifPresent(status -> context.getDestination().setAnimalStatus(status));
        Optional.ofNullable(context.getSource().getDescription()).ifPresent(description -> context.getDestination().setDescription(description));
        return context.getDestination();
    }
}
