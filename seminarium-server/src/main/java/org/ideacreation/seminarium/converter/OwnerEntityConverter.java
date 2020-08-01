package org.ideacreation.seminarium.converter;

import org.ideacreation.seminarium.domain.model.OwnerEntity;
import org.ideacreation.seminarium.model.OwnerDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class OwnerEntityConverter implements Converter<OwnerEntity, OwnerDto> {
    @Override
    public OwnerDto convert(MappingContext<OwnerEntity, OwnerDto> context) {
        return new OwnerDto(
                context.getSource().getFio(),
                context.getSource().getPhone(),
                context.getSource().getOrderNumber(),
                context.getSource().getOrderDate()
        );
    }
}
