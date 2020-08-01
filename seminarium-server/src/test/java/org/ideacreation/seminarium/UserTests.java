package org.ideacreation.seminarium;

import org.ideacreation.seminarium.domain.repository.UserRepository;
import org.ideacreation.seminarium.model.request.UserRegistrationRequest;
import org.ideacreation.seminarium.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createUsers() {
        UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest();
        userRegistrationRequest.setUsername("test");
        userRegistrationRequest.setPassword("test");
        userRegistrationRequest.setNickname("test");
        userRegistrationRequest.setPhoneNumber("+7-977-896-74-11");
        userService.signUp(userRegistrationRequest);

        Assertions.assertTrue(userRepository.existsByUsername("test"));
        Assertions.assertEquals("test", userRepository.findByUsername("test").orElseThrow(RuntimeException::new).getNickname());

        userService.delete("test");
    }

}
