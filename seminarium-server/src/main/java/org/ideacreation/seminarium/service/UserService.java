package org.ideacreation.seminarium.service;

import lombok.RequiredArgsConstructor;
import org.ideacreation.seminarium.configuration.security.JwtTokenProvider;
import org.ideacreation.seminarium.domain.model.Role;
import org.ideacreation.seminarium.domain.model.UserEntity;
import org.ideacreation.seminarium.domain.repository.UserRepository;
import org.ideacreation.seminarium.exception.CustomException;
import org.ideacreation.seminarium.model.UserDto;
import org.ideacreation.seminarium.model.request.UserRegistrationRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final ModelMapper modelMapper;

    public String signIn(String username, String password) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                return jwtTokenProvider.createToken(user.get().getUsername(), modelMapper.map(user.get(), UserDto.class));
            } else {
                throw new CustomException("Not authorized", HttpStatus.UNAUTHORIZED);
            }
        } else {
            throw new CustomException("Not found", HttpStatus.NOT_FOUND);
        }
    }

    public String signUp(UserRegistrationRequest userRegistrationDto) {
        UserEntity userEntity = modelMapper.map(userRegistrationDto, UserEntity.class);
        if (!userRepository.existsByUsername(userEntity.getUsername())) {
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            userEntity.setRole(Role.HELPER);
            userRepository.save(userEntity);
            return jwtTokenProvider.createToken(userEntity.getUsername(), modelMapper.map(userEntity, UserDto.class));
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    public UserDto search(String username) {
        return modelMapper.map(userRepository.findByUsername(username).orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND)), UserDto.class);
    }

    public UserDto whoAmI(HttpServletRequest req) {
        return modelMapper.map(userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)))
                .orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND)), UserDto.class);
    }

    public String refresh(String username) {
        return jwtTokenProvider.createToken(username, modelMapper.map(userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException("Not found", HttpStatus.NOT_FOUND)), UserDto.class));
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userEntity -> modelMapper.map(userEntity, UserDto.class)).collect(Collectors.toList());
    }

}
