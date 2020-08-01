package org.ideacreation.seminarium.converter;

import org.ideacreation.seminarium.domain.model.UserEntity;
import org.ideacreation.seminarium.model.UserDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class UserEntityConverter implements Converter<UserEntity, UserDto> {

    @Override
    public UserDto convert(MappingContext<UserEntity, UserDto> context) {
        return new UserDto(
                context.getSource().getId(),
                context.getSource().getUsername(),
                context.getSource().getNickname(),
                context.getSource().getEmail(),
                context.getSource().getRole(),
                context.getSource().getPhoneNumber(),
                context.getSource().getFirstName(),
                context.getSource().getLastName(),
                context.getSource().getSurname()
        );
    }
}
