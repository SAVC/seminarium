package org.ideacreation.seminarium.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.ideacreation.seminarium.model.UserDto;
import org.ideacreation.seminarium.model.request.SignInRequest;
import org.ideacreation.seminarium.model.request.UserRegistrationRequest;
import org.ideacreation.seminarium.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@Api(tags = "users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public String signUp(@ApiParam("SignUp User") @RequestBody UserRegistrationRequest registrationRequest, Principal principal) {
        return userService.signUp(registrationRequest);
    }

    @PostMapping("/signIn")
    public String singIn(@ApiParam("Sing in") @RequestBody SignInRequest signInRequest) {
        return userService.signIn(signInRequest.getUsername(), signInRequest.getPassword());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/whoAmI")
    public ResponseEntity<UserDto> whoAmI(HttpServletRequest request) {
        return ResponseEntity.ok(userService.whoAmI(request));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<UserDto> getUserByUsername(@RequestParam(value = "username") String username) {
        return ResponseEntity.ok(userService.search(username));
    }

}
